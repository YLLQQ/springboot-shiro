package org.yang.springboot.shiro.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.yang.springboot.shiro.model.domain.shiro.ShiroFilterChainDO;
import org.yang.springboot.shiro.model.dto.user.UserHasRoleAndPermissionDTO;
import org.yang.springboot.shiro.service.role.RolePermissionRelationService;
import org.yang.springboot.shiro.service.shiro.ShiroFilterChainService;
import org.yang.springboot.shiro.service.user.UserRoleRelationService;
import org.yang.springboot.shiro.util.ContextHelper;

import java.time.Instant;
import java.util.*;

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

    @Autowired
    private UserRoleRelationService userRoleRelationService;

    @Autowired
    private ShiroFilterFactoryBean shiroFilterFactoryBean;

    @Autowired
    private RolePermissionRelationService rolePermissionRelationService;

    @GetMapping("/sys/authorization/info/{userId}")
    public UserHasRoleAndPermissionDTO getUserHasRoleAndPermissionDTO(@PathVariable("userId") Integer userId) {
        UserHasRoleAndPermissionDTO userHasRoleAndPermissionDTO = new UserHasRoleAndPermissionDTO();

        userHasRoleAndPermissionDTO.setUserId(userId);

        Set<String> roleNameSet = userRoleRelationService.findRoleNameSetByUserId(userId);

        userHasRoleAndPermissionDTO.setRoleSet(roleNameSet);

        Set<String> permissionSet = rolePermissionRelationService.findPermissionSetByUserId(userId);

        userHasRoleAndPermissionDTO.setPermissionSet(permissionSet);

        Instant instant = Instant.now();
        Date nowDate = Date.from(instant);

        userHasRoleAndPermissionDTO.setVisitTime(nowDate);

        return userHasRoleAndPermissionDTO;
    }

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
