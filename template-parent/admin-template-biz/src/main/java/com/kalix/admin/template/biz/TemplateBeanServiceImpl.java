package com.kalix.admin.template.biz;


import com.kalix.admin.template.api.biz.ITemplateBeanService;
import com.kalix.admin.template.api.biz.ITemplateConfigBeanService;
import com.kalix.admin.template.api.biz.ITemplateContentBeanService;
import com.kalix.admin.template.api.dao.ITemplateBeanDao;
import com.kalix.admin.template.entities.TemplateBean;
import com.kalix.admin.template.entities.TemplateConfigBean;
import com.kalix.admin.template.entities.TemplateContentBean;
import com.kalix.framework.core.api.persistence.JsonData;
import com.kalix.framework.core.api.persistence.JsonStatus;
import com.kalix.framework.core.impl.biz.GenericBizServiceImpl;
import com.kalix.framework.core.util.SerializeUtil;
import com.kalix.framework.core.util.StringUtils;

import javax.transaction.Transactional;
import java.util.*;

/**
 * @类描述：公告管理
 * @创建人： zhangqingxin
 * @创建时间：2014/10/10
 * @修改人：
 * @修改时间：
 * @修改备注：
 */
public class TemplateBeanServiceImpl extends GenericBizServiceImpl<ITemplateBeanDao, TemplateBean> implements ITemplateBeanService {
    private static final String FUNCTION_NAME = "模板信息";
    private JsonStatus jsonStatus = new JsonStatus();
    private String uuid;
    private ITemplateConfigBeanService templateConfigBeanService;
    private ITemplateContentBeanService templateContentBeanService;

    public TemplateBeanServiceImpl() {
        uuid = UUID.randomUUID().toString();
        super.init(TemplateBean.class.getName());
    }

    @Override
    public List<TemplateBean> query(String title) {
        return dao.find("select n from templateBean n where n.name LIKE ?1 ", "%" + title + "%");
    }

    @Override
    @Transactional
    public boolean isSave(TemplateBean entity, JsonStatus status) {
        TemplateBean bean = (TemplateBean) entity;
        List<TemplateBean> templateBeans = dao.find("select ob from TemplateBean ob where ob.name = ?1", bean.getName());
        if (templateBeans != null && templateBeans.size() > 0) {
            status.setFailure(true);
            status.setMsg(FUNCTION_NAME + "已经存在！");
            return false;
        }
        return true;
    }

    /**
     * 根据模板名称以及该模板的项目值返回替换后的模板
     * @param templateName  模板名称
     * @param templateMap   该模板的项目
     * @return String       替换后的模板
     * */
    @Override
    public String getTemplateResult(String templateName, Map<String, String> templateMap) {

        String result = "";
        if (StringUtils.isEmpty(templateName)) {
            return result;
        }

        List<TemplateBean> templateBeans = dao.find("select ob from TemplateBean ob where ob.name = ?1", templateName);
        if (templateBeans != null && templateBeans.size() > 0) {
            result = replaceValue(templateBeans.get(0).getContent(), templateMap);
        }

        return result;
    }

    @Override
    public String getTemplateResult(String templateName, Integer templateType, Map<String, String> templateMap) {
        String result = "";
        if (StringUtils.isEmpty(templateName)) {
            return result;
        }
        List<TemplateBean> templateBeans = dao.find("select ob from TemplateBean ob where ob.name = ?1", templateName);
        TemplateBean templateBean = null;
        if (templateBeans != null && !templateBeans.isEmpty()) {
            templateBean = templateBeans.get(0);
            if (templateBean != null) {
                List<TemplateContentBean> templateContentBeans = this.templateContentBeanService.getContentByTemplateId(templateBean.getId(), templateType);
                if (templateContentBeans != null && templateContentBeans.size() > 0) {
                    String content = templateContentBeans.get(0).getContent();
                    if (StringUtils.isNotEmpty(content))
                        result = replaceValue(content, templateMap);
                }
            }
        }
        return result;
    }

    private String replaceValue(String result, Map<String, String> templateMap) {
        if (templateMap != null && templateMap.size() > 0) {
            String key, value;
            Iterator<Map.Entry<String, String>> it = templateMap.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<String, String> entry = it.next();
                key = "[$][{](" + entry.getKey() + ")[}]";
                value = entry.getValue();
                if (value != null) {
                    result = result.replaceAll(key, value);
                }
            }
        }
        return result;
    }

    @Override
    public JsonData getTranslateTemplate(String jsonStr) {
        Map<String, Object> jsonMap = SerializeUtil.jsonToMap(jsonStr);
        Long templateId = Long.valueOf((String) jsonMap.get("templateId"));
        Integer templateType = Integer.valueOf((String) jsonMap.get("templateType"));
//        TemplateBean template = dao.get(templateId);
        List<TemplateContentBean> templateContentBeans = this.templateContentBeanService.getContentByTemplateId(templateId, templateType);
        String templateContent = "";
        if (templateContentBeans != null && !templateContentBeans.isEmpty()) {
            TemplateContentBean templateContentBean = templateContentBeans.get(0);
            templateContent = templateContentBean.getContent();
        }
        List<TemplateConfigBean> configBeans = this.templateConfigBeanService.getConfigByTemplateId(templateId);
        for (TemplateConfigBean configBean : configBeans) {
            String attrName = "\\$\\{" + configBean.getFieldName() + "\\}";
            templateContent = templateContent.replaceAll(attrName, configBean.getFieldValue());
        }
        JsonData jsonData = new JsonData();
        List<String> contentList = new ArrayList<>();
        contentList.add(templateContent);
        jsonData.setData(contentList);
        return jsonData;
    }

    @Override
    @Transactional
    public void beforeDeleteEntity(Long id, JsonStatus status) {
        TemplateBean templateBean = this.getEntity(id);
        this.templateConfigBeanService.deleteByTemplateId(templateBean.getId());
        this.templateContentBeanService.deleteByTemplateId(templateBean.getId());
    }

    public void setTemplateConfigBeanService(ITemplateConfigBeanService templateConfigBeanService) {
        this.templateConfigBeanService = templateConfigBeanService;
    }

    public void setTemplateContentBeanService(ITemplateContentBeanService templateContentBeanService) {
        this.templateContentBeanService = templateContentBeanService;
    }
}
