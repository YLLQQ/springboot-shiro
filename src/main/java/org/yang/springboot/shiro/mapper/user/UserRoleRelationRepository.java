package org.yang.springboot.shiro.mapper.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.yang.springboot.shiro.model.domain.user.UserRoleRelationDO;

import java.util.Set;

/**
 * org.yang.springboot.shiro.mapper.user.UserRoleRelationRepository
 *
 * @author eleven
 * @date 2019/05/27
 */
@Repository
public interface UserRoleRelationRepository extends JpaRepository<UserRoleRelationDO, Integer> {

    /**
     * 通过用户编号获取角色编号
     *
     * @param userId
     * @return
     */
    @Query(value = "select role_id from user_role_relation where user_id=?1", nativeQuery = true)
    Set<Integer> findRoleIdSetByUserId(Integer userId);
}
