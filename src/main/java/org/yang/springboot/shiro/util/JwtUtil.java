package org.yang.springboot.shiro.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import org.yang.springboot.shiro.common.ShiroConst;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * org.yang.springboot.shiro.util.JwtUtil
 *
 * @author eleven
 * @date 2019/05/23
 */
public class JwtUtil {

    public static void main(String[] args) {
        JwtUtil jwtUtil = new JwtUtil();

        DefineClaim defineClaim = jwtUtil.new DefineClaim("username", "test");
        DefineClaim defineClaim1 = jwtUtil.new DefineClaim("username1", "test1");

        ArrayList<DefineClaim> defineClaims = Lists.newArrayList(defineClaim, defineClaim1);

        String token = createToken(defineClaims, "123456", ShiroConst.TOKEN_EXPIRE_TIME);

        System.out.println(token);

        String value = getClaimValueFromToken(ShiroConst.TOKEN_CLAIM_KEY, token);

        System.out.println(value);

        value = getClaimValueFromToken("username1", token);

        System.out.println(value);

        boolean result = verifyToken(token, "123456");

        System.out.println(result);
    }

    /**
     * 校验token
     *
     * @param token
     * @param secret
     * @return
     */
    public static boolean verifyToken(String token, String secret) {
        Algorithm algorithm = algorithmHmac256(secret);

        JWTVerifier verifier = JWT
                .require(algorithm)
                .build();

        try {
            verifier.verify(token);

            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    /**
     * 从token 中获取claim字段具体值，不安全
     *
     * @param claimKey
     * @param token
     * @return
     */
    public static String getClaimValueFromToken(String claimKey, String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);

            return jwt.getClaim(claimKey).asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    /**
     * 生成 token
     *
     * @param defineClaims
     * @param secret
     * @param expireTime
     * @return
     */
    public static String createToken(List<DefineClaim> defineClaims, String secret, long expireTime) {
        Instant instant = Instant.now().plusSeconds(expireTime);
        Date date = Date.from(instant);

        Algorithm algorithm = algorithmHmac256(secret);

        JWTCreator.Builder builder = JWT.create();

        for (DefineClaim defineClaim : defineClaims) {
            builder.withClaim(defineClaim.key, defineClaim.value);
        }

        return builder
                .withExpiresAt(date)
                .sign(algorithm);
    }

    /**
     * 加密算法
     *
     * @param secret
     * @return
     */
    private static Algorithm algorithmHmac256(String secret) {
        return Algorithm.HMAC256(secret);
    }

    @AllArgsConstructor
    public class DefineClaim {
        private String key;

        private String value;
    }
}
