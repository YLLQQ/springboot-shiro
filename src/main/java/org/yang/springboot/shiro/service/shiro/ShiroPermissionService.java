package org.yang.springboot.shiro.service.shiro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.yang.springboot.shiro.mapper.shiro.ShiroPermissionRepository;
import org.yang.springboot.shiro.model.domain.shiro.ShiroPermissionDO;
import org.yang.springboot.shiro.service.base.BaseService;

/**
 * org.yang.springboot.shiro.service.shiro.ShiroPermissionService
 *
 * @author eleven
 * @date 2019/05/28
 */
@Service
public class ShiroPermissionService extends BaseService {

    @Autowired
    private ShiroPermissionRepository shiroPermissionRepository;

    @Override
    protected JpaRepository getJpaRepository() {
        return shiroPermissionRepository;
    }

    public ShiroPermissionDO findShiroPermissionById(Integer id) {
        return super.findById(id, "cannot get shiro permission by id=" + id);
    }
}
