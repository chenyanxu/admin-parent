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
    private Long parentId; //父功能
    private Long applicationId;  // 归属应用
    private String permission; //权限
    private Boolean dataPermission; // 数据权限是否生效
    private String dataPermissionKey; //数据权限key

    private String opFlag = "0"; // 操作标识(为解决维护菜单时id变化不一致问题)

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

    public Long getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Long applicationId) {
        this.applicationId = applicationId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
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

    public String getOpFlag() {
        return opFlag;
    }

    public void setOpFlag(String opFlag) {
        this.opFlag = opFlag;
    }
}
