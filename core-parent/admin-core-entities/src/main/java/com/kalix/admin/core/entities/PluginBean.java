package com.kalix.admin.core.entities;

import com.kalix.framework.core.api.persistence.PersistentEntity;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 插件实体类
 *
 * @author sunlf <br/>
 *         date:2015-7-21
 * @version 1.0.0
 */
//@Entity
//@Table(name = "sys_plugin")
@Inheritance(strategy = InheritanceType.JOINED)
@XmlRootElement
public class PluginBean extends PersistentEntity {
    private String name; //功能名称
    private String code; //功能代码
    private String remark; //备注
    private String bundleId;

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public String getBundleId() {
        return bundleId;
    }

    public void setBundleId(String bundleId) {
        this.bundleId = bundleId;
    }
}
