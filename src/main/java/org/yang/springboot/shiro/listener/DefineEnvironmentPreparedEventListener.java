package org.yang.springboot.shiro.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.context.ApplicationListener;

/**
 * org.yang.springboot.shiro.listener.DefineEnvironmentPreparedEventListener
 *
 * @author eleven
 * @date 2019/05/27
 */
@Slf4j
public class DefineEnvironmentPreparedEventListener implements ApplicationListener<ApplicationEnvironmentPreparedEvent> {

    @Override
    public void onApplicationEvent(ApplicationEnvironmentPreparedEvent applicationEnvironmentPreparedEvent) {
        if (log.isInfoEnabled()) {
            log.info("The process's running status is environment prepared ...");
        }
    }

}
