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
@Table(name = "user_info")
public class UserInfoDO implements Serializable {

    /**
     * 用户编号
     */
    @Id
    @Column(name = "id")
    private Integer id;

    /**
     * 用户登录账号
     */
    @Column(name = "login_account")
    private String loginAccount;

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
