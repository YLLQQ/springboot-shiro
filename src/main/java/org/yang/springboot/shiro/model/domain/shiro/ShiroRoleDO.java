package org.yang.springboot.shiro.model.domain.shiro;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * @author eleven
 */
@Data
@Entity
@Table(name = "shiro_role")
public class ShiroRoleDO implements Serializable {

    /**
     * 角色编号
     */
    @Id
    @Column(name = "id")
    private Integer id;

    /**
     * 角色
     */
    @Column(name = "role_name")
    private String roleName;

    /**
     * 角色备注
     */
    @Column(name = "role_desc")
    private String roleDesc;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 最近一次更新时间
     */
    @Column(name = "last_update_time")
    private Date lastUpdateTime;

}
