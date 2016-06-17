package com.kalix.admin.core.dto.model;

import com.kalix.framework.core.api.web.model.BaseDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * 区域模型类
 * @author majian <br/>
 *         date:2015-7-24
 * @version 1.0.0
 */
public class AreaDTO extends BaseDTO {
    private String name; //名称
    private String code; //代码
    private String text; //名称
    private String centerCode; //中心代码
    private boolean leaf; //是否是叶子节点
    private long parentId; //父节点
    private String parentName; //父节点名称
    private List<AreaDTO> children=new ArrayList<AreaDTO>();
    private String jd; //经度
    private String wd; //纬度


    public String getJd() {
        return jd;
    }

    public void setJd(String jd) {
        this.jd = jd;
    }

    public String getWd() {
        return wd;
    }

    public void setWd(String wd) {
        this.wd = wd;
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


    public List<AreaDTO> getChildren() {
        return children;
    }

    public void setChildren(List<AreaDTO> children) {
        this.children = children;
    }
}
