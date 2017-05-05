package com.kalix.admin.template.api.biz;


import com.kalix.admin.template.entities.TemplateBean;
import com.kalix.framework.core.api.biz.IBizService;

import java.util.List;

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

    public void test();
}
