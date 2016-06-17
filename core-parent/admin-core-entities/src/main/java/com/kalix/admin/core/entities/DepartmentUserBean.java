package com.kalix.admin.core.entities;

import com.kalix.framework.core.api.persistence.PersistentEntity;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

/**
 * 部门用户关联实体类
 * @author majian <br/>
 *         date:2015-7-29
 * @version 1.0.0
 */
@Entity
@Table(name = "sys_department_user")
@Inheritance(strategy = InheritanceType.JOINED)
//@XmlRootElement
public class DepartmentUserBean extends PersistentEntity {
    /**
     * 用户.
     */
    private long userId;
    /**
     * 部门.
     */
    private long depId;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getDepId() {
        return depId;
    }

    public void setDepId(long depId) {
        this.depId = depId;
    }
}
