package org.yang.springboot.shiro.service.shiro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.yang.springboot.shiro.mapper.shiro.ShiroFilterChainRepository;
import org.yang.springboot.shiro.model.domain.shiro.ShiroFilterChainDO;
import org.yang.springboot.shiro.service.base.BaseService;

import java.util.List;

/**
 * org.yang.springboot.shiro.service.shiro.ShiroFilterChainService
 *
 * @author eleven
 * @date 2019/05/27
 */
@Service
public class ShiroFilterChainService extends BaseService {

    @Autowired
    private ShiroFilterChainRepository shiroFilterChainRepository;

    @Override
    public JpaRepository getJpaRepository() {
        return shiroFilterChainRepository;
    }

    public List<ShiroFilterChainDO> findAllFilterChain() {
        Sort orders = new Sort(Sort.Direction.ASC, "sortId");

        return super.findAll("cannot get shiro filter chain config from database", orders);
    }
}
