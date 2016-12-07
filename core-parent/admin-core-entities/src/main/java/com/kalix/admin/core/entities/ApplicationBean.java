package com.kalix.admin.core.entities;

import com.kalix.framework.core.api.persistence.PersistentEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * 应用模型类
 * @author majian <br/>
 *         date:2015-7-24
 * @version 1.0.0
 */
@Entity
@Table(name = "sys_application")
@ApiModel("应用<br>ApplicationBean")
public class ApplicationBean extends PersistentEntity {
    @ApiModelProperty(value="名称",position=0,example = "测试应用")
    private String name;   // 名称
    @ApiModelProperty(value="备注",position=1,example = "测试备注")
    private String remark;   // 备注
    @ApiModelProperty(value="代码",position=2,example = "test")
    private String code; //代码
    @ApiModelProperty(value="地址",position = 3,hidden = true)
    private String location; //地址
    private String iconCls;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getIconCls() {
        return iconCls;
    }

    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }

    public ApplicationBean() {

    }

}
