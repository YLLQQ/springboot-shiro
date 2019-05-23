package org.yang.springboot.shiro.model.dto;

import lombok.Data;

import java.util.List;

/**
 * org.yang.springboot.shiro.model.dto.UserDTO
 *
 * @author eleven
 * @date 2019/05/23
 */
@Data
public class UserDTO {

    private Integer id;

    private String username;

    private String password;

    /**
     * 用户的角色 一对多关系
     */
    private List<RoleDTO> roleList;
}
