package org.yang.springboot.shiro.service.user;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.yang.springboot.shiro.model.domain.user.UserInfoDO;

/**
 * org.yang.springboot.shiro.service.user.UserInfoServiceTest
 *
 * @author eleven
 * @date 2019/05/27
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserInfoServiceTest {

    @Autowired
    private UserInfoService userInfoService;

    @Test
    public void findUserInfoDOByLoginAccount() {
        UserInfoDO loginAccount = userInfoService.findUserInfoDOByLoginAccount("loginAccount");

        log.info(loginAccount.toString());

        loginAccount = userInfoService.findUserInfoDOByLoginAccount("LOGINACCOUNT");

        log.info(loginAccount.toString());
    }
}