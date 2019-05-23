package org.yang.springboot.shiro.service;

import org.springframework.stereotype.Service;
import org.yang.springboot.shiro.model.dto.PermissionDTO;
import org.yang.springboot.shiro.model.dto.RoleDTO;
import org.yang.springboot.shiro.model.dto.UserDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * org.yang.springboot.shiro.service.UserService
 *
 * @author eleven
 * @date 2019/05/23
 */
@Service
public class UserService {

    public UserDTO findUserByName(String username) {
        UserDTO user = null;

        if ("1".equals(username)) {
            user = getAdminRole();
        } else {
            user = getSystemRole();
        }

        user.setUsername(username);

        return user;
    }

    private UserDTO getAdminRole() {
        PermissionDTO permissionDTO1 = new PermissionDTO();

        permissionDTO1.setId(1);
        permissionDTO1.setPermissionDetail("permission:admin");
        permissionDTO1.setPermissionGroup("超级管理权限");

        PermissionDTO permissionDTO2 = new PermissionDTO();

        permissionDTO2.setId(2);
        permissionDTO2.setPermissionDetail("permission:system");
        permissionDTO2.setPermissionGroup("系统管理权限");

        List<PermissionDTO> adminPermissionList = new ArrayList<>(2);

        adminPermissionList.add(permissionDTO1);
        adminPermissionList.add(permissionDTO2);

        RoleDTO roleDTO = new RoleDTO();

        roleDTO.setId(1);
        roleDTO.setRoleName("admin");
        roleDTO.setRoleDesc("超级管理员");
        roleDTO.setPermissionList(adminPermissionList);

        List<RoleDTO> roleDTOList = new ArrayList<>(1);

        roleDTOList.add(roleDTO);

        UserDTO userDTO = new UserDTO();

        userDTO.setId(1);
        userDTO.setRoleList(roleDTOList);
        userDTO.setUsername("admin");
        userDTO.setPassword("123456");

        return userDTO;
    }

    private UserDTO getSystemRole() {

        PermissionDTO permissionDTO = new PermissionDTO();

        permissionDTO.setId(2);
        permissionDTO.setPermissionDetail("permission:system");
        permissionDTO.setPermissionGroup("系统管理权限");

        List<PermissionDTO> adminPermissionList = new ArrayList<>(1);

        adminPermissionList.add(permissionDTO);

        RoleDTO roleDTO = new RoleDTO();

        roleDTO.setId(2);
        roleDTO.setRoleName("system");
        roleDTO.setRoleDesc("系统管理员");
        roleDTO.setPermissionList(adminPermissionList);

        List<RoleDTO> roleDTOList = new ArrayList<>(1);

        roleDTOList.add(roleDTO);

        UserDTO userDTO = new UserDTO();

        userDTO.setId(2);
        userDTO.setRoleList(roleDTOList);
        userDTO.setUsername("system");
        userDTO.setPassword("123456");

        return userDTO;
    }
}
