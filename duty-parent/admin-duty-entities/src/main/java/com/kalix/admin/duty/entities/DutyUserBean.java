package com.kalix.admin.duty.entities;

import com.kalix.framework.core.api.persistence.PersistentEntity;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

/**
 * 职位与用户关联实体类
 *
 * @author zangyanming <br/>
 *         date:2016-3-15
 * @version 1.0.0
 */
@Entity
@Table(name = "sys_duty_user")
@Inheritance(strategy = InheritanceType.JOINED)
//@XmlRootElement
public class DutyUserBean extends PersistentEntity {
    /**
     * 用户.
     */
    private long userId;
    /**
     * 职位.
     */
    private long dutyId;
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

    public long getDutyId() {
        return dutyId;
    }

    public void setDutyId(long dutyId) {
        this.dutyId = dutyId;
    }

    public long getDepId() {
        return depId;
    }

    public void setDepId(long depId) {
        this.depId = depId;
    }
}
