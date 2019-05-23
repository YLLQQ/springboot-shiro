package org.yang.springboot.shiro.model.dto;

import lombok.Data;

/**
 * org.yang.springboot.shiro.model.dto.PermissionDTO
 *
 * @author eleven
 * @date 2019/05/23
 */
@Data
public class PermissionDTO {

    /**
     * 编号
     */
    private Integer id;

    /**
     * 权限详情
     */
    private String permissionDetail;

    /**
     * 权限组
     */
    private String permissionGroup;
}