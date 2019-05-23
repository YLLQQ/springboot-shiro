package org.yang.springboot.shiro.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * org.yang.springboot.shiro.controller.TestUserController
 *
 * @author eleven
 * @date 2019/05/23
 */
@RestController
public class TestUserController {

    @GetMapping("/user/admin")
    public String testAdmin() {
        return "admin";
    }

    @GetMapping("/user/system")
    public String testSystem() {
        return "system";
    }
}
