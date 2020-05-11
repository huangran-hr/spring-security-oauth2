package com.example.oauth2.sso.server.domain;

import java.util.Date;
import javax.persistence.*;
import lombok.Data;

/**
 * @Auther: HR
 * @Date: 2020/4/14 11:10
 * @Description:
 */
/**
    * 权限表
    */
@Data
@Table(name = "tb_permission")
public class TbPermission {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     * 父权限
     */
    @Column(name = "parent_id")
    private Long parentId;

    /**
     * 权限名称
     */
    @Column(name = "`name`")
    private String name;

    /**
     * 权限英文名称
     */
    @Column(name = "enname")
    private String enname;

    /**
     * 权限路径
     */
    @Column(name = "url")
    private String url;

    /**
     * 备注
     */
    @Column(name = "description")
    private String description;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;
}
