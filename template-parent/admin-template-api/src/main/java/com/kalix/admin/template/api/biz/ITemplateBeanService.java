package com.kalix.admin.template.api.biz;


import com.kalix.admin.template.entities.TemplateBean;
import com.kalix.framework.core.api.biz.IBizService;
import com.kalix.framework.core.api.persistence.JsonData;

import java.util.List;
import java.util.Map;

/**
 * @类描述：审计管理
 * @创建人： sunlf
 * @创建时间：2014/10/10
 * @修改人：
 * @修改时间：
 * @修改备注：
 */
public interface ITemplateBeanService extends IBizService<TemplateBean> {

    List<TemplateBean> query(String title);

    /**
     * 根据模板名称以及该模板的项目值返回替换后的模板
     *
     * @param templateName 模板名称
     * @param templateMap  该模板的项目
     * @return String       替换后的模板
     */
    String getTemplateResult(String templateName, Map<String, String> templateMap);

    JsonData getTranslateTemplate(String jsonStr);
}
