package com.kalix.admin.state.entities;

import com.kalix.framework.core.api.persistence.PersistentEntity;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

/**
 * 状态机实体类
 *
 * @author hqj <br/>
 *         date:2017-4-25
 * @version 1.0.0
 */
@Entity
@Table(name = "sys_statemachine")
public class StateMachineBean extends PersistentEntity {
    private String app;         //所属应用
    private String key;         //状态机key
    private String description; //状态机描述
    /*private Long type;          //状态机类型*/
    @Lob
    private String config;      //状态机配置文件
    private String remark;      //备注

    public String getApp() {
        return app;
    }

    public void setApp(String app) {
        this.app = app;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /*public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }*/

    public String getConfig() {
        return config;
    }

    public void setConfig(String config) {
        this.config = config;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
