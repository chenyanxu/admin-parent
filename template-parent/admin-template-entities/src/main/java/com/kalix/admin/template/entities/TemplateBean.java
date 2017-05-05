package com.kalix.admin.template.entities;


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
@Table(name = "sys_template")
public class TemplateBean extends PersistentEntity {
    private String name; //模板名称
    private String desc; //模板描述
    @Lob
    private String content;//模板内容

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
