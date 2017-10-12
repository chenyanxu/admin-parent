package com.kalix.admin.duty.entities;

import com.kalix.framework.core.api.persistence.PersistentEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @类描述：数据权限管理
 * @创建人：hqj
 * @创建时间：2017-10-12
 * @修改人：
 * @修改时间：
 * @修改备注：
 */
//todo 修改模型定义
@Entity
@Table(name = "sys_data_auth")
public class DataAuthBean extends PersistentEntity {
    private String name;    //数据权限名称
    private String comment; //数据权限描述

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
