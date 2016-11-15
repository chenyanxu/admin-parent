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
@Table(name = "sys_organization_user")
public class OrganizationUserBean extends PersistentEntity {
    /**
     * 用户.
     */
    private Long userId;
    /**
     * 机构.
     */
    private Long orgId;

    public OrganizationUserBean(){

    }

    public OrganizationUserBean(Long userId, Long orgId, String createBy, String updateBy)  {
        this.userId = userId;
        this.orgId = orgId;
        this.setCreateBy(createBy);
        this.setUpdateBy(updateBy);
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }
}
