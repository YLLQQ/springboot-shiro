package org.yang.springboot.shiro.service.user;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;
import org.yang.springboot.shiro.model.domain.user.UserRoleRelationDO;

import java.util.List;
import java.util.Set;

/**
 * org.yang.springboot.shiro.service.user.UserRoleRelationServiceTest
 *
 * @author eleven
 * @date 2019/05/27
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRoleRelationServiceTest {

    @Autowired
    private UserRoleRelationService userRoleRelationService;


    @Test
    public void findAllRoleByUserId() {
        List<UserRoleRelationDO> allRoleByUserId = userRoleRelationService.findAllRoleByUserId(1);

        log.info("result is {}", allRoleByUserId);

    }

    @Test
    public void findRoleIdListByUserId() {
        Set<Integer> roleIdList = userRoleRelationService.findRoleIdSetByUserId(1);

        log.info("result is {}", roleIdList);
    }

    @Test
    public void findRoleWithPageByUserId() {
        Page<UserRoleRelationDO> roleWithPageByUserId = userRoleRelationService.findRoleWithPageByUserId(1);

        log.info("page result is {}", roleWithPageByUserId);
    }
}