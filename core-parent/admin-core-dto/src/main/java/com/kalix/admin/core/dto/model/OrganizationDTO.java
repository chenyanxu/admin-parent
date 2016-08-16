package com.kalix.admin.core.dto.model;

import com.kalix.framework.core.api.web.model.BaseDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * 机构模型类
 * @author majian <br/>
 *         date:2015-7-21
 * @version 1.0.0
 */
public class OrganizationDTO extends BaseDTO {
    private long orgId;
    private String orgCode;
    private String orgName;

    private String name; //机构名称
    private String code; //机构代码
    private String text; //名称
    private String centerCode; //中心代码
    private boolean leaf; //是否是叶子节点
    private long parentId; //父机构
    private int areaId;  // 归属区域
    private String parentName; //父节点名称
    private boolean dept;//是否是部门

    public long getOrgId() {
        return orgId;
    }

    public void setOrgId(long orgId) {
        this.orgId = orgId;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    private List<OrganizationDTO> children=new ArrayList<OrganizationDTO>();

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
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


    public boolean isLeaf() {
        return leaf;
    }

    public void setLeaf(boolean leaf) {
        this.leaf = leaf;
    }

    public long getParentId() {
        return parentId;
    }

    public void setParentId(long parentId) {
        this.parentId = parentId;
    }

    public int getAreaId() {
        return areaId;
    }

    public void setAreaId(int areaId) {
        this.areaId = areaId;
    }

    public List<OrganizationDTO> getChildren() {
        return children;
    }

    public void setChildren(List<OrganizationDTO> children) {
        this.children = children;
    }

    public boolean isDept() {
        return dept;
    }

    public void setDept(boolean dept) {
        this.dept = dept;
    }
}
