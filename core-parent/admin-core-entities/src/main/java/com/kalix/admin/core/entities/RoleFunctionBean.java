package com.kalix.admin.core.entities;

import com.kalix.framework.core.api.persistence.PersistentEntity;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

/**
 * 角色功能关联实体类
 * @author majian <br/>
 *         date:2015-8-4
 * @version 1.0.0
 */
@Entity
@Table(name = "sys_role_function")
public class RoleFunctionBean extends PersistentEntity {
    /**
     * 功能.
     */
    private String functionId;
    /**
     * 角色.
     */
    private String roleId;


    public String getFunctionId() {
        return functionId;
    }

    public void setFunctionId(String functionId) {
        this.functionId = functionId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
}
