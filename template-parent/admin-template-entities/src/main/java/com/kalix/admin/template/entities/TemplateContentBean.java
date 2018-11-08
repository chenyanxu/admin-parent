package com.kalix.admin.template.entities;

import com.kalix.framework.core.api.persistence.PersistentEntity;

import javax.persistence.Entity;
import javax.persistence.Lob;
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
@Table(name = "sys_templatecontent")
public class TemplateContentBean extends PersistentEntity {
    private String templateId; // 模板id
    private Integer templateType; // 模板类型
    private String templateTypeDesc; // 模板类型文本
    @Lob
    private String content; // 模板内容

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public Integer getTemplateType() {
        return templateType;
    }

    public void setTemplateType(Integer templateType) {
        this.templateType = templateType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTemplateTypeDesc() {
        return templateTypeDesc;
    }

    public void setTemplateTypeDesc(String templateTypeDesc) {
        this.templateTypeDesc = templateTypeDesc;
    }
}
