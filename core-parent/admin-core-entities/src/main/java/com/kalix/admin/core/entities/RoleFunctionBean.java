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
@Inheritance(strategy = InheritanceType.JOINED)
//@XmlRootElement
public class RoleFunctionBean extends PersistentEntity {
    /**
     * 功能.
     */
    private long functionId;
    /**
     * 角色.
     */
    private long roleId;


    public long getFunctionId() {
        return functionId;
    }

    public void setFunctionId(long functionId) {
        this.functionId = functionId;
    }

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }
}
