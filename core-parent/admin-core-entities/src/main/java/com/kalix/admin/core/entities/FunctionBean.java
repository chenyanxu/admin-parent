package com.kalix.admin.core.entities;

import com.kalix.framework.core.api.persistence.PersistentEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 功能实体类
 * @author majian <br/>
 *         date:2015-7-21
 * @version 1.0.0
 */
@Entity
@Table(name = "sys_function")
public class FunctionBean extends PersistentEntity {
    private String name; //功能名称
    private String code; //功能代码
    private String remark; //备注
    private Long isLeaf; //是否是叶子节点
    private String parentId; //父功能
    private String applicationId;  // 归属应用
    private String permission; //权限
    private Boolean dataPermission; // 数据权限是否生效
    private String dataPermissionKey; //数据权限key

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


    public Long getIsLeaf() {
        return isLeaf;
    }

    public void setIsLeaf(Long isLeaf) {
        this.isLeaf = isLeaf;
    }

    public Boolean getDataPermission() {
        return dataPermission;
    }

    public void setDataPermission(Boolean dataPermission) {
        this.dataPermission = dataPermission;
    }

    public String getDataPermissionKey() {
        return dataPermissionKey;
    }

    public void setDataPermissionKey(String dataPermissionKey) {
        this.dataPermissionKey = dataPermissionKey;
    }
}
