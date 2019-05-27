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
@Table(name = "shiro_filter_chain")
public class ShiroFilterChainDO implements Serializable {

    /**
     * 自增编号
     */
    @Id
    @Column(name = "id")
    private Integer id;

    /**
     * 过滤url
     */
    @Column(name = "url")
    private String url;

    /**
     * url匹配权限
     */
    @Column(name = "permission")
    private String permission;

    /**
     * 排序因子
     */
    @Column(name = "sort_id")
    private Integer sortId;

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
