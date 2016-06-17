package com.kalix.admin.duty.entities;


import com.kalix.framework.core.api.persistence.PersistentEntity;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @类描述：职位管理
 * @创建人：zangyanming
 * @创建时间：2016-01-07
 * @修改人：
 * @修改时间：
 * @修改备注：
 */
 //todo 修改模型定义
@Entity
@Table(name = "sys_duty")
public class DutyBean extends PersistentEntity {
    private String name;//职位名称
    private long depid;   //所在部门
    private String comment; //职位描述

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getDepid() {
        return depid;
    }

    public void setDepid(long depid) {
        this.depid = depid;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
