package org.yang.springboot.shiro.model.shiro;

import lombok.AllArgsConstructor;
import lombok.ToString;
import org.apache.shiro.authc.AuthenticationToken;

/**
 * org.yang.springboot.shiro.model.shiro.JwtToken
 *
 * @author eleven
 * @date 2019/05/23
 */
@ToString
@AllArgsConstructor
public class JwtToken implements AuthenticationToken {

    private String token;

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
