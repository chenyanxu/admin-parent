package com.kalix.admin.template.biz;


import com.kalix.admin.template.api.biz.ITemplateContentBeanService;
import com.kalix.admin.template.api.dao.ITemplateContentBeanDao;
import com.kalix.admin.template.entities.TemplateContentBean;
import com.kalix.framework.core.impl.biz.GenericBizServiceImpl;

import java.util.List;

/**
 * @类描述：审计配置管理
 * @创建人： yangz
 * @创建时间：2018/02/09
 * @修改人：
 * @修改时间：
 * @修改备注：
 */
public class TemplateContentBeanServiceImpl extends GenericBizServiceImpl<ITemplateContentBeanDao, TemplateContentBean> implements ITemplateContentBeanService {

    public TemplateContentBeanServiceImpl() {
        super.init(TemplateContentBean.class.getName());
    }

    @Override
    public void deleteByTemplateId(Long templateId) {
        dao.updateNativeQuery("delete from sys_templatecontent where templateid = " + templateId);
    }

    @Override
    public List<TemplateContentBean> getContentByTemplateId(Long templateId, Integer templateType) {
        List<TemplateContentBean> configsObj = dao.findByNativeSql("select * from sys_templatecontent where templateid = " + templateId + "and templatetype = " + templateType, TemplateContentBean.class, null);
        return configsObj;
    }
}
