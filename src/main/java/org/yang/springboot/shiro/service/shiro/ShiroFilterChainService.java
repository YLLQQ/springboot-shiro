package org.yang.springboot.shiro.service.shiro;

import org.apache.shiro.util.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.yang.springboot.shiro.mapper.shiro.ShiroFilterChainRepository;
import org.yang.springboot.shiro.model.domain.shiro.ShiroFilterChainDO;
import org.yang.springboot.shiro.service.base.BaseService;

import java.util.LinkedHashMap;
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

    public LinkedHashMap<String, String> shiroFilterChainDO2FilterChainMap(List<ShiroFilterChainDO> filterChainDOList) {
        Assert.notEmpty(filterChainDOList, "cannot init illegal shiro chain filter list to Map");

        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>(filterChainDOList.size());

        for (ShiroFilterChainDO shiroFilterChainDO : filterChainDOList) {
            linkedHashMap.put(shiroFilterChainDO.getUrl(), shiroFilterChainDO.getPermission());
        }

        return linkedHashMap;
    }

    public List<ShiroFilterChainDO> findAllFilterChain() {
        Sort orders = new Sort(Sort.Direction.ASC, "sortId");

        return super.findAll("cannot get shiro filter chain config from database", orders);
    }
}
