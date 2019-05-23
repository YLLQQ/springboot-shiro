package org.yang.springboot.shiro.model.dto;

import lombok.Data;

import java.util.List;

/**
 * org.yang.springboot.shiro.model.dto.RoleDTO
 *
 * @author eleven
 * @date 2019/05/23
 */
@Data
public class RoleDTO {

    /**
     * 编号
     */
    private Integer id;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色描述
     */
    private String roleDesc;

    /**
     * 角色权限关系
     */
    private List<PermissionDTO> permissionList;

}
