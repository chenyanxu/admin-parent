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
    private String userId;
    /**
     * 机构.
     */
    private String orgId;

    public OrganizationUserBean(){

    }

    public OrganizationUserBean(String userId, String orgId, String createBy, String updateBy)  {
        this.userId = userId;
        this.orgId = orgId;
        this.setCreateBy(createBy);
        this.setUpdateBy(updateBy);
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }
}
