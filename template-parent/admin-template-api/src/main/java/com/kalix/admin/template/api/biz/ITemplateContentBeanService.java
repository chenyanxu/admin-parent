package com.kalix.admin.template.api.biz;


import com.kalix.admin.template.entities.TemplateContentBean;
import com.kalix.framework.core.api.biz.IBizService;

import java.util.List;

/**
 * @类描述：审计配置管理
 * @创建人： yangz
 * @创建时间：2018/02/09
 * @修改人：
 * @修改时间：
 * @修改备注：
 */
public interface ITemplateContentBeanService extends IBizService<TemplateContentBean> {
    void deleteByTemplateId(Long templateId);

    List<TemplateContentBean> getContentByTemplateId(Long templateId, Integer templateType);
}
