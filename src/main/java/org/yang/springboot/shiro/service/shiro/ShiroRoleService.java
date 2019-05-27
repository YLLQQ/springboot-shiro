package org.yang.springboot.shiro.service.shiro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.yang.springboot.shiro.mapper.shiro.ShiroRoleRepository;
import org.yang.springboot.shiro.model.domain.shiro.ShiroRoleDO;
import org.yang.springboot.shiro.service.base.BaseService;

/**
 * org.yang.springboot.shiro.service.shiro.ShiroRoleService
 *
 * @author eleven
 * @date 2019/05/27
 */
@Service
public class ShiroRoleService extends BaseService {
    @Autowired
    private ShiroRoleRepository shiroRoleRepository;

    @Override
    protected JpaRepository getJpaRepository() {
        return shiroRoleRepository;
    }

    public ShiroRoleDO findShiroRoleById(Integer id) {
        return super.findById(id, "cannot get role info by id=" + id);
    }
}
