package org.yang.springboot.shiro.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yang.springboot.shiro.model.domain.shiro.ShiroFilterChainDO;
import org.yang.springboot.shiro.service.shiro.ShiroFilterChainService;
import org.yang.springboot.shiro.util.ContextHelper;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * org.yang.springboot.shiro.controller.TestLoginController
 *
 * @author eleven
 * @date 2019/05/23
 */
@Slf4j
@RestController
public class TestSysController {

    @Autowired
    private ShiroFilterChainService shiroFilterChainService;

    @GetMapping("/sys/shiro/info")
    public Object shiro() {
        ShiroFilterFactoryBean bean = ContextHelper.getBean(ShiroFilterFactoryBean.class);

        Map<String, String> filterChainDefinitionMap = bean.getFilterChainDefinitionMap();

        return filterChainDefinitionMap;
    }

    @GetMapping("/sys/shiro/update")
    public Boolean update() {
        List<ShiroFilterChainDO> allFilterChain = shiroFilterChainService.findAllFilterChain();
        LinkedHashMap<String, String> linkedHashMap = shiroFilterChainService.shiroFilterChainDO2FilterChainMap(allFilterChain);

        ShiroFilterFactoryBean shiroFilterFactoryBean = ContextHelper.getBean(ShiroFilterFactoryBean.class);

        if (log.isInfoEnabled()) {
            log.info("old shiro filter bean is {}", shiroFilterFactoryBean);
        }

        shiroFilterFactoryBean.getFilterChainDefinitionMap().clear();
        shiroFilterFactoryBean.setFilterChainDefinitionMap(linkedHashMap);

        try {
            AbstractShiroFilter abstractShiroFilter = (AbstractShiroFilter) shiroFilterFactoryBean.getObject();
            PathMatchingFilterChainResolver pathMatchingFilterChainResolver = (PathMatchingFilterChainResolver) abstractShiroFilter.getFilterChainResolver();
            DefaultFilterChainManager defaultFilterChainManager = (DefaultFilterChainManager) pathMatchingFilterChainResolver.getFilterChainManager();

            defaultFilterChainManager.getFilterChains().clear();

            for (ShiroFilterChainDO shiroFilterChainDO : allFilterChain) {
                defaultFilterChainManager.createChain(shiroFilterChainDO.getUrl(), shiroFilterChainDO.getPermission());
            }

            if (log.isInfoEnabled()) {
                log.info("new shiro filter bean is {}", shiroFilterFactoryBean);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return true;
    }


}
