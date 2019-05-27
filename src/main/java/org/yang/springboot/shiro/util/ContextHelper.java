package org.yang.springboot.shiro.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * org.yang.springboot.shiro.util.ContextHelper
 *
 * @author eleven
 * @date 2019/05/27
 */
@Slf4j
@Component
public class ContextHelper implements ApplicationContextAware {

    private static ApplicationContext CONTEXT;

    public static ApplicationContext getContext() {
        return CONTEXT;
    }

    public static <T> T getBean(Class<T> cl) {
        return getContext().getBean(cl);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (log.isInfoEnabled()) {
            log.info("Init ContextHelper");
        }

        CONTEXT = applicationContext;
    }
}
