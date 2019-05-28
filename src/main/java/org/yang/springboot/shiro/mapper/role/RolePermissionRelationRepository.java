package org.yang.springboot.shiro.mapper.role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.yang.springboot.shiro.model.domain.role.RolePermissionRelationDO;

import java.util.List;

/**
 * org.yang.springboot.shiro.mapper.role.RolePermissionRelationRepository
 *
 * @author eleven
 * @date 2019/05/28
 */
@Repository
public interface RolePermissionRelationRepository extends JpaRepository<RolePermissionRelationDO, Integer> {
    /**
     * 通过角色编号获取权限编号
     *
     * @param roleId
     * @return
     */
    @Query(value = "select permission_id from role_permission_relation where role_id=?1", nativeQuery = true)
    List<Integer> findPermissionIdListByRoleId(Integer roleId);
}
