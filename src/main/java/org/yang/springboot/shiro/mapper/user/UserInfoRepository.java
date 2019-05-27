package org.yang.springboot.shiro.mapper.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.yang.springboot.shiro.model.domain.user.UserInfoDO;

/**
 * org.yang.springboot.shiro.mapper.user.UserInfoRepository
 *
 * @author eleven
 * @date 2019/05/27
 */
@Repository
public interface UserInfoRepository extends JpaRepository<UserInfoDO, Integer> {
}
