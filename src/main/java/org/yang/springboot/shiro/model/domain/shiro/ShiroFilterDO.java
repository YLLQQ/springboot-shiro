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
@Table(name = "shiro_filter")
public class ShiroFilterDO implements Serializable {

    /**
     * 自增编号
     */
    @Id
    @Column(name = "id")
    private Integer id;

    /**
     * 过滤器简称
     */
    @Column(name = "filter_shortening")
    private String filterShortening;

    /**
     * 过滤器路径
     */
    @Column(name = "filter_class_path")
    private String filterClassPath;

    /**
     * 过滤器描述
     */
    @Column(name = "filter_description")
    private String filterDescription;

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
