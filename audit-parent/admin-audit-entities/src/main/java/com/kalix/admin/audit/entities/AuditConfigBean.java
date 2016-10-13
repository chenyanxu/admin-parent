package com.kalix.admin.audit.entities;


import com.kalix.framework.core.api.persistence.PersistentEntity;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

/**
 * @类描述：审计配置管理
 * @创建人： sunlf
 * @创建时间：2015/8/27
 * @修改人：
 * @修改时间：
 * @修改备注：
 */
@Entity
@Table(name = "sys_auditconfig")
@Inheritance(strategy = InheritanceType.JOINED)
public class AuditConfigBean extends PersistentEntity {
    private String clsName; //类名称
    private String appName; //应用名称
    private String funName;//功能名称
    private Boolean enable;//是否监控
    private String remark;//备注

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getFunName() {
        return funName;
    }

    public void setFunName(String funName) {
        this.funName = funName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getClsName() {
        return clsName;
    }

    public void setClsName(String clsName) {
        this.clsName = clsName;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }
}
