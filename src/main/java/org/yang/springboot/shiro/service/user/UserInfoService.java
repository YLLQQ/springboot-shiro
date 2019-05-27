package org.yang.springboot.shiro.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.yang.springboot.shiro.mapper.user.UserInfoRepository;
import org.yang.springboot.shiro.model.domain.user.UserInfoDO;
import org.yang.springboot.shiro.service.base.BaseService;

/**
 * org.yang.springboot.shiro.service.user.UserInfoService
 *
 * @author eleven
 * @date 2019/05/27
 */
@Service
public class UserInfoService extends BaseService {
    @Autowired
    private UserInfoRepository userInfoRepository;

    @Override
    protected JpaRepository getJpaRepository() {
        return userInfoRepository;
    }

    public UserInfoDO findUserInfoDOByLoginAccount(String loginAccount) {
        UserInfoDO userInfoDO = new UserInfoDO();

        userInfoDO.setLoginAccount(loginAccount);

        return super.findOne(
                userInfoDO,
                ExampleMatcher.matching().withMatcher("loginAccount", ExampleMatcher.GenericPropertyMatchers.caseSensitive()),
                "cannot get user info by loginAccount=" + loginAccount);
    }
}
