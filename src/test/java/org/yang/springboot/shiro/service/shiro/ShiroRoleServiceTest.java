package org.yang.springboot.shiro.service.shiro;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.yang.springboot.shiro.model.domain.shiro.ShiroRoleDO;

/**
 * org.yang.springboot.shiro.service.shiro.ShiroRoleServiceTest
 *
 * @author eleven
 * @date 2019/05/27
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class ShiroRoleServiceTest {

    @Autowired
    private ShiroRoleService shiroRoleService;

    @Test
    public void findShiroRoleById() {

        ShiroRoleDO shiroRoleById = shiroRoleService.findShiroRoleById(1);

        log.info(shiroRoleById.toString());
    }
}