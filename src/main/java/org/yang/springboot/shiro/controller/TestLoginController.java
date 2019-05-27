package org.yang.springboot.shiro.controller;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.yang.springboot.shiro.common.ShiroConst;
import org.yang.springboot.shiro.model.domain.user.UserInfoDO;
import org.yang.springboot.shiro.model.shiro.DefineClaim;
import org.yang.springboot.shiro.service.user.UserInfoService;
import org.yang.springboot.shiro.util.JwtUtil;

import java.util.ArrayList;

/**
 * org.yang.springboot.shiro.controller.TestLoginController
 *
 * @author eleven
 * @date 2019/05/23
 */
@Slf4j
@RestController
public class TestLoginController {

    @Autowired
    private UserInfoService userInfoService;

    @PostMapping("/login")
    public Object login(@RequestBody String loginAccount) {

        UserInfoDO userInfoDO = userInfoService.findUserInfoDOByLoginAccount(loginAccount);

        ArrayList<DefineClaim> defineClaims = Lists.newArrayList(new DefineClaim(ShiroConst.TOKEN_CLAIM_KEY, userInfoDO.getLoginAccount()));

        String token = JwtUtil.createToken(defineClaims, userInfoDO.getLoginAccount(), ShiroConst.TOKEN_EXPIRE_TIME);

        return token;
    }

}
