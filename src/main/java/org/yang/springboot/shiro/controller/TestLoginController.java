package org.yang.springboot.shiro.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.yang.springboot.shiro.model.dto.UserDTO;
import org.yang.springboot.shiro.service.UserService;

/**
 * org.yang.springboot.shiro.controller.TestLoginController
 *
 * @author eleven
 * @date 2019/05/23
 */
@RestController
public class TestLoginController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Object login(@RequestBody String username) {

        System.out.println(username);

        UserDTO userDTO = userService.findUserByName(username);

        System.out.println(userDTO);

        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken(userDTO.getUsername(), userDTO.getPassword());

        subject.login(token);

        return token;
    }

}
