package com.kalix.admin.duty.entities;


import com.kalix.framework.core.api.persistence.PersistentEntity;
import com.kalix.framework.core.util.KalixCascade;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @类描述：职位管理
 * @创建人：zangyanming
 * @创建时间：2016-01-07
 *
 * @修改人： p
 * @修改时间： 2016-07-05
 * @修改备注： depid修改为orgid
 */
 //todo 修改模型定义
@Entity
@Table(name = "sys_duty")
public class DutyBean extends PersistentEntity {
    private String name;//职位名称
    @KalixCascade(beans = "com.kalix.admin.core.entities.OrganizationBean", deletable = true, foreignKey = "orgid")
    private long orgid;   //所在机构
    private String comment; //职位描述

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getOrgid() {
        return orgid;
    }

    public void setOrgid(long orgid) {
        this.orgid = orgid;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
