package org.yang.springboot.shiro.mapper.shiro;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.yang.springboot.shiro.model.domain.shiro.ShiroRoleDO;

/**
 * org.yang.springboot.shiro.mapper.shiro.ShiroRoleRepository
 *
 * @author eleven
 * @date 2019/05/27
 */
@Repository
public interface ShiroRoleRepository extends JpaRepository<ShiroRoleDO, Integer> {
}
