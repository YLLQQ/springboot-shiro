package org.yang.springboot.shiro.mapper.shiro;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.yang.springboot.shiro.model.domain.shiro.ShiroPermissionDO;

/**
 * org.yang.springboot.shiro.mapper.shiro.ShiroPermissionRepository
 *
 * @author eleven
 * @date 2019/05/28
 */
@Repository
public interface ShiroPermissionRepository extends JpaRepository<ShiroPermissionDO, Integer> {
}
