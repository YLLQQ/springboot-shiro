package org.yang.springboot.shiro.service.shiro;

import org.apache.shiro.util.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.yang.springboot.shiro.mapper.shiro.ShiroFilterRepository;
import org.yang.springboot.shiro.model.domain.shiro.ShiroFilterDO;
import org.yang.springboot.shiro.service.base.BaseService;
import org.yang.springboot.shiro.util.ClassUtil;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public Map<String, Filter> shiroFilterDO2FilterMap(List<ShiroFilterDO> filterDOList) {
        Assert.notEmpty(filterDOList, "cannot init illegal shiro filter list to Map");

        Map<String, Filter> filterMap = new HashMap<>(filterDOList.size());

        for (ShiroFilterDO shiroFilterDO : filterDOList) {
            filterMap.put(shiroFilterDO.getFilterShortening(), ClassUtil.initByClassPath(shiroFilterDO.getFilterClassPath()));
        }

        return filterMap;
    }


}
