package com.kalix.admin.core.dto.model;

import com.kalix.framework.core.api.web.model.BaseDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * 部门模型类
 * @author majian <br/>
 *         date:2015-7-21
 * @version 1.0.0
 */
public class DepartmentDTO extends BaseDTO {
    private String name; //名称
    private String text; //名称
    private String code; //代码
    private String centerCode; //中心代码
    private Boolean leaf; //是否是叶子节点
    private Long parentId; //父节点
    private Integer orgId;  // 所属机构
    private String parentName; //父节点名称
    private List<DepartmentDTO> children=new ArrayList<DepartmentDTO>();

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


    public Boolean isLeaf() {
        return leaf;
    }

    public void setLeaf(Boolean leaf) {
        this.leaf = leaf;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public List<DepartmentDTO> getChildren() {
        return children;
    }

    public void setChildren(List<DepartmentDTO> children) {
        this.children = children;
    }
}
