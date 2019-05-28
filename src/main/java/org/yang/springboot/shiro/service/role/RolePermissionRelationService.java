package org.yang.springboot.shiro.service.role;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.yang.springboot.shiro.mapper.role.RolePermissionRelationRepository;
import org.yang.springboot.shiro.model.domain.shiro.ShiroPermissionDO;
import org.yang.springboot.shiro.service.base.BaseService;
import org.yang.springboot.shiro.service.shiro.ShiroPermissionService;
import org.yang.springboot.shiro.service.user.UserRoleRelationService;

import java.util.*;
import java.util.stream.Collectors;

/**
 * org.yang.springboot.shiro.service.role.RolePermissionRelationService
 *
 * @author eleven
 * @date 2019/05/28
 */
@Slf4j
@Service
public class RolePermissionRelationService extends BaseService {
    @Autowired
    private RolePermissionRelationRepository rolePermissionRelationRepository;

    @Autowired
    private ShiroPermissionService shiroPermissionService;

    @Autowired
    private UserRoleRelationService userRoleRelationService;

    @Override
    protected JpaRepository getJpaRepository() {
        return rolePermissionRelationRepository;
    }

    public Set<String> findPermissionSetByUserId(Integer userId) {
        Set<Integer> roleIdSetByUserId = userRoleRelationService.findRoleIdSetByUserId(userId);

        if (CollectionUtils.isEmpty(roleIdSetByUserId)) {
            return null;
        }

        HashSet<String> permissionSet = Sets.newHashSet();

        for (Integer integer : roleIdSetByUserId) {
            permissionSet.addAll(findPermissionSetByRoleId(integer));
        }

        return permissionSet;
    }

    public Set<String> findPermissionSetByRoleId(Integer roleId) {
        Multimap<String, String> rolePermissionMapByRoleId = findRolePermissionMapByRoleId(roleId);

        if (null == rolePermissionMapByRoleId) {
            return null;
        }

        Collection<Map.Entry<String, String>> permissionCollection = rolePermissionMapByRoleId.entries();

        return permissionCollection.stream().map(x -> x.getKey() + ":" + x.getValue()).collect(Collectors.toSet());
    }

    public Multimap<String, String> findRolePermissionMapByRoleId(Integer roleId) {
        List<Integer> permissionIdListByRoleId = rolePermissionRelationRepository.findPermissionIdListByRoleId(roleId);

        if (CollectionUtils.isEmpty(permissionIdListByRoleId)) {
            if (log.isInfoEnabled()) {
                log.info("roleId={} cannot config permission", roleId);
            }

            return null;
        }

        Multimap<String, String> multimap = HashMultimap.create();

        for (Integer integer : permissionIdListByRoleId) {
            ShiroPermissionDO shiroPermissionById = shiroPermissionService.findShiroPermissionById(integer);

            multimap.put(shiroPermissionById.getPermissionGroup(), shiroPermissionById.getPermissionDetail());
        }

        return multimap;
    }


}
