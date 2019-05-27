package org.yang.springboot.shiro.service.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.yang.springboot.shiro.mapper.user.UserRoleRelationRepository;
import org.yang.springboot.shiro.model.domain.shiro.ShiroRoleDO;
import org.yang.springboot.shiro.model.domain.user.UserRoleRelationDO;
import org.yang.springboot.shiro.service.base.BaseService;
import org.yang.springboot.shiro.service.shiro.ShiroRoleService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * org.yang.springboot.shiro.service.user.UserRoleRelationService
 *
 * @author eleven
 * @date 2019/05/27
 */
@Slf4j
@Service
public class UserRoleRelationService extends BaseService {
    @Autowired
    private UserRoleRelationRepository userRoleRelationRepository;

    @Autowired
    private ShiroRoleService shiroRoleService;

    @Override
    protected JpaRepository getJpaRepository() {
        return userRoleRelationRepository;
    }

    public Set<String> findRoleNameSetByUserId(Integer userId) {
        Set<Integer> roleIdSet = findRoleIdSetByUserId(userId);
        Set<String> roleNameSet = new HashSet<>(roleIdSet.size());

        if (log.isInfoEnabled()) {
            log.info("role id set is {}", roleIdSet);
        }

        for (Integer roleId : roleIdSet) {
            ShiroRoleDO shiroRoleById = shiroRoleService.findShiroRoleById(roleId);

            if (log.isInfoEnabled()) {
                log.info("role info is {}", shiroRoleById);
            }

            roleNameSet.add(shiroRoleById.getRoleName());
        }

        return roleNameSet;
    }

    public Set<Integer> findRoleIdSetByUserId(Integer userId) {
        Set<Integer> roleIdSetByUserId = userRoleRelationRepository.findRoleIdSetByUserId(userId);

        Assert.notEmpty(roleIdSetByUserId, "cannot get role id by userId=" + userId);

        return roleIdSetByUserId;
    }

    public Page<UserRoleRelationDO> findRoleWithPageByUserId(Integer userId) {

        UserRoleRelationDO userRoleRelationDO = new UserRoleRelationDO();

        userRoleRelationDO.setUserId(userId);

        return super.findListWithPage(userRoleRelationDO, ExampleMatcher.matching().withIgnoreCase(), 0, 10);
    }

    public List<UserRoleRelationDO> findAllRoleByUserId(Integer userId) {

        UserRoleRelationDO userRoleRelationDO = new UserRoleRelationDO();

        userRoleRelationDO.setUserId(userId);

        return super.findAll(userRoleRelationDO, ExampleMatcher.matching().withIgnoreCase(), "cannot get role info by userId=" + userId);
    }
}
