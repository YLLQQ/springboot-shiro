package org.yang.springboot.shiro.controller;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.yang.springboot.shiro.common.ShiroConst;
import org.yang.springboot.shiro.model.dto.UserDTO;
import org.yang.springboot.shiro.model.shiro.DefineClaim;
import org.yang.springboot.shiro.service.UserService;
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
    private UserService userService;

    @PostMapping("/login")
    public Object login(@RequestBody String username) {

        UserDTO userDTO = userService.findUserByName(username);

        ArrayList<DefineClaim> defineClaims = Lists.newArrayList(new DefineClaim(ShiroConst.TOKEN_CLAIM_KEY, userDTO.getId() + ""));

        String token = JwtUtil.createToken(defineClaims, userDTO.getPassword(), ShiroConst.TOKEN_EXPIRE_TIME);

        return token;
    }

}
