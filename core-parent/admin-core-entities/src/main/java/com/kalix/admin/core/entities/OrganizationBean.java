package com.kalix.admin.core.entities;

import com.kalix.framework.core.api.persistence.PersistentEntity;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

/**
 * 机构实体类
 * @author majian <br/>
 *         date:2015-7-21
 * @version 1.0.0
 */
@Entity
@Table(name = "sys_organization")
public class OrganizationBean extends PersistentEntity {
    private String name; //机构名称
    private String code; //机构代码
    private String centerCode; //中心代码
    private long isLeaf; //是否是叶子节点
    private long parentId; //父机构
    private long areaId;  // 归属区域
    private boolean dept;//是否是部门


    public long getParentId() {
        return parentId;
    }

    public void setParentId(long parentId) {
        this.parentId = parentId;
    }

    public long getAreaId() {
        return areaId;
    }

    public void setAreaId(int areaId) {
        this.areaId = areaId;
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

    public String getCenterCode() {
        return centerCode;
    }

    public void setCenterCode(String centerCode) {
        this.centerCode = centerCode;
    }

    public long getIsLeaf() {
        return isLeaf;
    }

    public void setIsLeaf(int isLeaf) {
        this.isLeaf = isLeaf;
    }

    public boolean isDept() {
        return dept;
    }

    public void setDept(boolean dept) {
        this.dept = dept;
    }
}
