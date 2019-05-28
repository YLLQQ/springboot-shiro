package org.yang.springboot.shiro.service.role;

import com.google.common.collect.Multimap;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * org.yang.springboot.shiro.service.role.RolePermissionRelationServiceTest
 *
 * @author eleven
 * @date 2019/05/28
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class RolePermissionRelationServiceTest {

    @Autowired
    private RolePermissionRelationService rolePermissionRelationService;

    @Test
    public void findRolePermissionMapByRoleId() {
        Multimap<String, String> rolePermissionMapByRoleId = rolePermissionRelationService.findRolePermissionMapByRoleId(1);

        Collection<Map.Entry<String, String>> entries = rolePermissionMapByRoleId.entries();

        Iterator<Map.Entry<String, String>> iterator = entries.iterator();

        while (iterator.hasNext()) {
            Map.Entry<String, String> next = iterator.next();

            log.info("{} -- {}", next.getKey(), next.getValue());
        }

        Set<String> collect = entries.stream().map(x -> x.getKey() + ":" + x.getValue()).collect(Collectors.toSet());

        log.info(collect.toString());
    }

    @Test
    public void getPermissionSetByUserId() {
        Set<String> permissionSetByUserId = rolePermissionRelationService.findPermissionSetByUserId(1);

        log.info("permission set is {}", permissionSetByUserId);
    }
}