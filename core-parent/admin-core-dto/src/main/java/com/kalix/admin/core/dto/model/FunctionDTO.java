package com.kalix.admin.core.dto.model;

import com.kalix.framework.core.api.web.model.BaseDTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 机构模型类
 * @author majian <br/>
 *         date:2015-7-21
 * @version 1.0.0
 */
public class FunctionDTO extends BaseDTO {
    private String name; //功能名称
    private String code; //功能代码
    private String text; //名称
    private boolean leaf; //是否是叶子节点
    private Long parentId; //父功能
    private String remark; //备注
    private Long applicationId;  // 归属应用
    private String parentName; //父节点名称
    private String permission; //权限路径
    private List<FunctionDTO> children=new ArrayList<FunctionDTO>();

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


    public boolean isLeaf() {
        return leaf;
    }

    public void setLeaf(boolean leaf) {
        this.leaf = leaf;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Long getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Long applicationId) {
        this.applicationId = applicationId;
    }

    public List<FunctionDTO> getChildren() {
        return children;
    }

    public void setChildren(List<FunctionDTO> children) {
        this.children = children;
    }
}
