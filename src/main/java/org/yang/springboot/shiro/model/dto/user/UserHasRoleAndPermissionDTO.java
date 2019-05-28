package org.yang.springboot.shiro.model.dto.user;

import lombok.Data;

import java.time.Instant;
import java.util.Set;

/**
 * org.yang.springboot.shiro.model.dto.user.UserHasRoleAndPermissionDTO
 *
 * @author eleven
 * @date 2019/05/28
 */
@Data
public class UserHasRoleAndPermissionDTO {

    private Integer userId;

    private Set<String> roleSet;

    private Set<String> permissionSet;

    private Instant visitTime;
}
