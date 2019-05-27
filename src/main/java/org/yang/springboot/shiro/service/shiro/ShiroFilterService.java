package org.yang.springboot.shiro.service.shiro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.yang.springboot.shiro.mapper.shiro.ShiroFilterRepository;
import org.yang.springboot.shiro.model.domain.shiro.ShiroFilterDO;
import org.yang.springboot.shiro.service.base.BaseService;

import java.util.List;

/**
 * org.yang.springboot.shiro.service.shiro.ShiroFilterService
 *
 * @author eleven
 * @date 2019/05/27
 */
@Service
public class ShiroFilterService extends BaseService {

    @Autowired
    private ShiroFilterRepository shiroFilterRepository;

    @Override
    protected JpaRepository getJpaRepository() {
        return shiroFilterRepository;
    }

    public List<ShiroFilterDO> findAllShiroFilter() {
        return super.findAll("cannot get shiro filter config from database");
    }


}
