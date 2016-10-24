package com.kalix.admin.duty.entities;

import com.kalix.framework.core.api.annotation.KalixCascade;
import com.kalix.framework.core.api.annotation.TableCascade;
import com.kalix.framework.core.api.persistence.PersistentEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 职位与用户关联实体类
 *
 * @author zangyanming <br/>
 *         date:2016-3-15
 *
 * @修改人： p
 * @修改时间： 2016-07-05
 * @修改备注： depid修改为orgid
 */
@Entity
@Table(name = "sys_duty_user")
@TableCascade(kalixCascades = {
        @KalixCascade(beans = "com.kalix.admin.core.entities.UserBean", deletable = true, foreignKey = "userId"),
        @KalixCascade(beans = "com.kalix.admin.core.entities.OrganizationBean", deletable = true, foreignKey = "orgid"),
        @KalixCascade(beans = "com.kalix.admin.core.entities.DutyBean", deletable = true, foreignKey = "dutyId")
})
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
     * 机构.
     */
    private long orgId;

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

    public long getOrgId() {
        return orgId;
    }

    public void setOrgId(long orgId) {
        this.orgId = orgId;
    }
}
