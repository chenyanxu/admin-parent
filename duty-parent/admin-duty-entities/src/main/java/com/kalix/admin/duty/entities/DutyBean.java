package com.kalix.admin.duty.entities;


import com.kalix.framework.core.api.annotation.KalixCascade;
import com.kalix.framework.core.api.annotation.TableCascade;
import com.kalix.framework.core.api.persistence.PersistentEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @类描述：职位管理
 * @创建人：zangyanming
 * @创建时间：2016-01-07
 * @修改人： p
 * @修改时间： 2016-07-05
 * @修改备注： depid修改为orgid
 */
//todo 修改模型定义
@Entity
@Table(name = "sys_duty")
@TableCascade(kalixCascades = {
        @KalixCascade(beans = "com.kalix.admin.core.entities.OrganizationBean", deletable = true, foreignKey = "orgid")
})
public class DutyBean extends PersistentEntity {
    private String name;//职位名称
    private Long orgid;   //所在机构
    private String comment; //职位描述

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getOrgid() {
        return orgid;
    }

    public void setOrgid(Long orgid) {
        this.orgid = orgid;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
