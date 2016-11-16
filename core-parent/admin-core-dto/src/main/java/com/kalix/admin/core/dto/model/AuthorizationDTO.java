package com.kalix.admin.core.dto.model;

import com.kalix.framework.core.api.web.model.BaseDTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 授权模型类
 * @author majian <br/>
 *         date:2015-7-31
 * @version 1.0.0
 */
public class AuthorizationDTO extends BaseDTO {
    private String name; //名称
    private String code; //代码
    private String text; //名称
    private Boolean leaf; //是否是叶子节点
    private Long parentId; //父节点
    private String parentName; //父节点名称
    private Boolean expanded; //是否展开子节点
    private Boolean checked; //是否多选
    private List<AuthorizationDTO> children=new ArrayList<AuthorizationDTO>();

    public Boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(Boolean expanded) {
        this.expanded = expanded;
    }

    public Boolean isChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

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


    public List<AuthorizationDTO> getChildren() {
        return children;
    }

    public void setChildren(List<AuthorizationDTO> children) {
        this.children = children;
    }
}
