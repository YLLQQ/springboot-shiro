package org.yang.springboot.shiro.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.yang.springboot.shiro.model.dto.RoleDTO;
import org.yang.springboot.shiro.model.dto.UserDTO;
import org.yang.springboot.shiro.service.UserService;

import java.util.HashSet;
import java.util.Set;

/**
 * org.yang.springboot.shiro.realm.DefineUserRealm
 *
 * @author eleven
 * @date 2019/05/23
 */
public class DefineUserRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        UserDTO userInfo = (UserDTO) principals.getPrimaryPrincipal();

        Set<String> roleSet = new HashSet<>();

        for (RoleDTO roleDTO : userInfo.getRoleList()) {
            roleSet.add(roleDTO.getRoleName());
        }

        authorizationInfo.setRoles(roleSet);

        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        String username = upToken.getUsername();

        // Null username is invalid
        if (username == null) {
            throw new AccountException("Null username are not allowed by this realm.");
        }

        UserDTO userDTO = userService.findUserByName(username);

        if (userDTO == null) {
            throw new UnknownAccountException("No account found for admin [" + username + "]");
        }

        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(userDTO, userDTO.getPassword(), getName());

        return info;
    }
}
