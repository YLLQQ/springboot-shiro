package org.yang.springboot.shiro.mapper.shiro;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.yang.springboot.shiro.model.domain.shiro.ShiroFilterChainDO;

/**
 * org.yang.springboot.shiro.mapper.shiro.ShiroFilterChainRepository
 *
 * @author eleven
 * @date 2019/05/27
 */
@Repository
public interface ShiroFilterChainRepository extends JpaRepository<ShiroFilterChainDO, Integer> {
}
