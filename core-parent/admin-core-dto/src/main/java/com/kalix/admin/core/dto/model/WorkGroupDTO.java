package com.kalix.admin.core.dto.model;

import com.kalix.framework.core.api.web.model.BaseDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * 工作组模型类
 * @author majian <br/>
 *         date:2015-7-24
 * @version 1.0.0
 */
public class WorkGroupDTO extends BaseDTO {
    private String name; //名称
    private String remark; //名称
    private boolean leaf; //是否是叶子节点
    private long parentId; //父节点
    private String parentName; //父节点名称
    private List<WorkGroupDTO> children=new ArrayList<WorkGroupDTO>();

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


    public List<WorkGroupDTO> getChildren() {
        return children;
    }

    public void setChildren(List<WorkGroupDTO> children) {
        this.children = children;
    }
}
