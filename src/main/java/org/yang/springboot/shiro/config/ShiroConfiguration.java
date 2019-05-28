package org.yang.springboot.shiro.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.yang.springboot.shiro.common.error.ShiroRouteConst;
import org.yang.springboot.shiro.model.domain.shiro.ShiroFilterChainDO;
import org.yang.springboot.shiro.model.domain.shiro.ShiroFilterDO;
import org.yang.springboot.shiro.realm.DefineUserRealm;
import org.yang.springboot.shiro.service.shiro.ShiroFilterChainService;
import org.yang.springboot.shiro.service.shiro.ShiroFilterService;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * org.yang.springboot.shiro.config.ShiroConfiguration
 *
 * @author eleven
 * @date 2019/05/23
 */
@Slf4j
@Configuration
public class ShiroConfiguration {

    /**
     * Shiro的Web过滤器
     *
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilter(
            ShiroFilterService shiroFilterService,
            ShiroFilterChainService shiroFilterChainService
    ) {
        /**
         * 获取过滤器
         */
        List<ShiroFilterDO> allShiroFilter = shiroFilterService.findAllShiroFilter();
        Map<String, Filter> filterMap = shiroFilterService.shiroFilterDO2FilterMap(allShiroFilter);

        if (log.isInfoEnabled()) {
            log.info("Filter config map is {}", filterMap);
        }

        /**
         * 获取过滤链
         */
        List<ShiroFilterChainDO> allFilterChain = shiroFilterChainService.findAllFilterChain();
        LinkedHashMap<String, String> linkedHashMap = shiroFilterChainService.shiroFilterChainDO2FilterChainMap(allFilterChain);

        if (log.isInfoEnabled()) {
            log.info("Filter chain config map is {}", linkedHashMap);
        }

        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        shiroFilterFactoryBean.setSecurityManager(securityManager());

        shiroFilterFactoryBean.setFilters(filterMap);
        shiroFilterFactoryBean.setFilterChainDefinitionMap(linkedHashMap);
        shiroFilterFactoryBean.setUnauthorizedUrl(ShiroRouteConst.DEFAULT_UNAUTHORIZED_REDIRECT_URL);

        return shiroFilterFactoryBean;
    }

    /**
     * 具体Realm实现
     *
     * @return
     */
    @Bean
    public DefineUserRealm defineUserRealm() {
        return new DefineUserRealm();
    }

    /**
     * 安全管理器
     *
     * @return
     */
    @Bean
    public DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();

        securityManager.setRealm(defineUserRealm());

        DefaultSessionStorageEvaluator defaultSessionStorageEvaluator = new DefaultSessionStorageEvaluator();

        defaultSessionStorageEvaluator.setSessionStorageEnabled(false);

        DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();

        subjectDAO.setSessionStorageEvaluator(defaultSessionStorageEvaluator);

        securityManager.setSubjectDAO(subjectDAO);

        return securityManager;
    }

    /**
     * 下面的代码是添加注解支持
     */
    @Bean
    @DependsOn("lifecycleBeanPostProcessor")
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        // 强制使用cglib，防止重复代理和可能引起代理出错的问题
        // https://zhuanlan.zhihu.com/p/29161098
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);

        return defaultAdvisorAutoProxyCreator;
    }

    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor() {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();

        advisor.setSecurityManager(securityManager());

        return advisor;
    }

}
