package org.yang.springboot.shiro.model.domain.user;

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
@Table(name = "user_role_relation")
public class UserRoleRelationDO implements Serializable {

    /**
     * 自增编号
     */
    @Id
    @Column(name = "id")
    private Integer id;

    /**
     * 用户编号
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 角色编号
     */
    @Column(name = "role_id")
    private Integer roleId;

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
