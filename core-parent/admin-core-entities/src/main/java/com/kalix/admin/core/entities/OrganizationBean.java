package com.kalix.admin.core.entities;

import com.kalix.framework.core.api.persistence.PersistentEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 机构实体类
 *
 * @author majian <br/>
 *         date:2015-7-21
 * @version 1.0.0
 */
@Entity
@Table(name = "sys_organization")
public class OrganizationBean extends PersistentEntity {
    private String name; //机构名称
    @Column(unique = true)
    private String code; //机构代码
    private String centerCode; //中心代码
    private Long isLeaf = 1L; //是否是叶子节点
    private String parentId; //父机构
    private String areaId;  // 归属区域
    private Boolean dept;//是否是部门
    private String szxqid; //所属校区

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

    public Long getIsLeaf() {
        return isLeaf;
    }

    public void setIsLeaf(Long isLeaf) {
        this.isLeaf = isLeaf;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public Boolean getDept() {
        return dept;
    }

    public void setDept(Boolean dept) {
        this.dept = dept;
    }

    public String getSzxqid() {
        return szxqid;
    }

    public void setSzxqid(String szxqid) {
        this.szxqid = szxqid;
    }
}
