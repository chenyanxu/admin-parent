package com.kalix.admin.core.dto.model;

import com.kalix.framework.core.api.web.model.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * 应用模型类
 * @author majian <br/>
 *         date:2015-7-31
 * @version 1.0.0
 */
@ApiModel("应用树<br>ApplicationDTO")
public class ApplicationDTO extends BaseDTO {
    @ApiModelProperty("名称")
    private String name; //名称
    @ApiModelProperty("代码")
    private String code; //代码
    @ApiModelProperty("文本")
    private String text; //名称
    @ApiModelProperty("是否是叶子节点")
    private Boolean leaf; //是否是叶子节点
    @ApiModelProperty("父节点")
    private Long parentId; //父节点
    @ApiModelProperty("父节点名称")
    private String parentName; //父节点名称
    @ApiModelProperty("图标")
    private String iconCls;//图标
    @ApiModelProperty("支持手机")
    private Boolean supportMobile;//支持手机

    private List<ApplicationDTO> children=new ArrayList<ApplicationDTO>();

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

    public String getIconCls() {
        return iconCls;
    }

    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }

    public List<ApplicationDTO> getChildren() {
        return children;
    }

    public void setChildren(List<ApplicationDTO> children) {
        this.children = children;
    }

    public Boolean getSupportMobile() {
        return supportMobile;
    }

    public void setSupportMobile(Boolean supportMobile) {
        this.supportMobile = supportMobile;
    }
}
