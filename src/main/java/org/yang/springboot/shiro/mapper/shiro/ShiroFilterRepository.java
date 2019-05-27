package org.yang.springboot.shiro.mapper.shiro;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.yang.springboot.shiro.model.domain.shiro.ShiroFilterDO;

/**
 * org.yang.springboot.shiro.mapper.shiro.ShiroFilterRepository
 *
 * @author eleven
 * @date 2019/05/27
 */
@Repository
public interface ShiroFilterRepository extends JpaRepository<ShiroFilterDO, Integer> {

}
