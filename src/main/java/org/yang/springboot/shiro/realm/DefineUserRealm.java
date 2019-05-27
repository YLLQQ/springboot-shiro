package org.yang.springboot.shiro.realm;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.yang.springboot.shiro.common.ShiroConst;
import org.yang.springboot.shiro.model.domain.user.UserInfoDO;
import org.yang.springboot.shiro.model.shiro.JwtToken;
import org.yang.springboot.shiro.service.user.UserInfoService;
import org.yang.springboot.shiro.service.user.UserRoleRelationService;
import org.yang.springboot.shiro.util.JwtUtil;

import java.util.Set;

/**
 * org.yang.springboot.shiro.realm.DefineUserRealm
 *
 * @author eleven
 * @date 2019/05/23
 */
@Slf4j
public class DefineUserRealm extends AuthorizingRealm {
    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private UserRoleRelationService userRoleRelationService;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        UserInfoDO userInfoDO = (UserInfoDO) principals.getPrimaryPrincipal();

        Set<String> roleSet = userRoleRelationService.findRoleNameSetByUserId(userInfoDO.getId());

        authorizationInfo.setRoles(roleSet);

        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String jwtToken = (String) token.getCredentials();

        if (log.isInfoEnabled()) {
            log.info("current jwtToken is {}", jwtToken);
        }

        String claimValue = JwtUtil.getClaimValueFromToken(ShiroConst.TOKEN_CLAIM_KEY, jwtToken);

        // Null username is invalid
        if (claimValue == null) {
            throw new AccountException("Null username are not allowed by this realm.");
        }

        UserInfoDO userInfoDO = userInfoService.findUserInfoDOByLoginAccount(claimValue);

        if (userInfoDO == null) {
            throw new UnknownAccountException("No account found for [" + claimValue + "]");
        }

        if (log.isInfoEnabled()) {
            log.info("current secret is {}", userInfoDO.getLoginAccount());
        }

        if (!JwtUtil.verifyToken(jwtToken, userInfoDO.getLoginAccount())) {
            throw new AuthenticationException("Username or password error");
        }

        return new SimpleAuthenticationInfo(userInfoDO, jwtToken, getName());
    }
}
