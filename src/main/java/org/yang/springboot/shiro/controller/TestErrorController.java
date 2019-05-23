package org.yang.springboot.shiro.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yang.springboot.shiro.common.error.ShiroRouteConst;

/**
 * org.yang.springboot.shiro.controller.TestErrorController
 *
 * @author eleven
 * @date 2019/05/23
 */
@RestController
public class TestErrorController {

    @GetMapping(ShiroRouteConst.DEFAULT_ERROR_REDIRECT_URL)
    public String error() {
        return "500";
    }

    @GetMapping(ShiroRouteConst.DEFAULT_UNAUTHORIZED_REDIRECT_URL)
    public String unauthorized() {
        return "403";
    }
}
