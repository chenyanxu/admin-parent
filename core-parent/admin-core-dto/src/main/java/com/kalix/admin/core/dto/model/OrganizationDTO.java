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
    private String name; //机构名称
    private String code; //机构代码
    private String text; //名称
    private String centerCode; //中心代码
    private Boolean leaf; //是否是叶子节点
    private String parentId; //父机构
    private String areaId;  // 归属区域
    private String parentName; //父节点名称
    private Boolean dept;//是否是部门
    private String szxqid;
    private String szxqname;

    private List<OrganizationDTO> children=new ArrayList<OrganizationDTO>();

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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCenterCode() {
        return centerCode;
    }

    public void setCenterCode(String centerCode) {
        this.centerCode = centerCode;
    }

    public Boolean getLeaf() {
        return leaf;
    }

    public void setLeaf(Boolean leaf) {
        this.leaf = leaf;
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

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
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

    public String getSzxqname() {
        return szxqname;
    }

    public void setSzxqname(String szxqname) {
        this.szxqname = szxqname;
    }

    public List<OrganizationDTO> getChildren() {
        return children;
    }

    public void setChildren(List<OrganizationDTO> children) {
        this.children = children;
    }
}
