package com.kalix.admin.core.entities;

import com.kalix.framework.core.api.persistence.PersistentEntity;

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
@Inheritance(strategy = InheritanceType.JOINED)
@XmlRootElement
public class ApplicationBean extends PersistentEntity {
    private String name;   // 名称
    private String remark;   // 备注
    private String code; //代码
    private String location; //地址

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

    public ApplicationBean() {

    }

}
