package com.kalix.admin.core.entities;

import com.kalix.framework.core.api.persistence.PersistentEntity;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

/**
 * 角色应用关联实体类
 * @author majian <br/>
 *         date:2015-8-4
 * @version 1.0.0
 */
@Entity
@Table(name = "sys_role_application")
public class RoleApplicationBean extends PersistentEntity {
    /**
     * 应用.
     */
    private String applicationId;
    /**
     * 角色.
     */
    private String roleId;


    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
}
