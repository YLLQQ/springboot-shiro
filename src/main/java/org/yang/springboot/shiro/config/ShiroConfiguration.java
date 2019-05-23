package org.yang.springboot.shiro.config;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.yang.springboot.shiro.common.ShiroConst;
import org.yang.springboot.shiro.realm.DefineUserRealm;

/**
 * org.yang.springboot.shiro.config.ShiroConfiguration
 *
 * @author eleven
 * @date 2019/05/23
 */
@Configuration
public class ShiroConfiguration {

    @Bean
    public ShiroFilterFactoryBean shiroFilter() {
        System.out.println("ShiroConfiguration.shirFilter()");
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        shiroFilterFactoryBean.setSecurityManager(securityManager());

        shiroFilterFactoryBean.setFilterChainDefinitionMap(ShiroConst.ROUTE_FILTER);

        shiroFilterFactoryBean.setLoginUrl("/login");
        shiroFilterFactoryBean.setSuccessUrl("/index");
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");

        return shiroFilterFactoryBean;
    }

    @Bean
    public DefineUserRealm defineUserRealm() {
        return new DefineUserRealm();
    }

    @Bean
    public DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();

        securityManager.setRealm(defineUserRealm());

        return securityManager;
    }

}
