package com.kalix.admin.audit.entities;


import com.kalix.framework.core.api.persistence.PersistentEntity;

import javax.persistence.*;

/**
 * @类描述：审计管理
 * @创建人： sunlf
 * @创建时间：2015/8/27
 * @修改人：
 * @修改时间：
 * @修改备注：
 */
@Entity
@Table(name = "sys_audit")
@Inheritance(strategy = InheritanceType.JOINED)
public class AuditBean extends PersistentEntity {
    private String appName; //应用名称
    private String funName;//功能名称
    private String actor;//操作人
    private String action;//操作
    @Lob
    private String content;//操作内容

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

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
