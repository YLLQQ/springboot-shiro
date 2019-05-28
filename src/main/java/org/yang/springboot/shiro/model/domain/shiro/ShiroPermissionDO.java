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
@Table(name = "shiro_permission")
public class ShiroPermissionDO implements Serializable {

    /**
     * 权限编号
     */
    @Id
    @Column(name = "id")
    private Integer id;

    /**
     * 权限组
     */
    @Column(name = "permission_group")
    private String permissionGroup;

    /**
     * 权限详情
     */
    @Column(name = "permission_detail")
    private String permissionDetail;

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
