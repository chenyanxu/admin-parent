package com.kalix.admin.template.biz;


import com.kalix.admin.template.api.biz.ITemplateBeanService;
import com.kalix.admin.template.api.dao.ITemplateBeanDao;
import com.kalix.admin.template.entities.TemplateBean;
import com.kalix.framework.core.api.persistence.JsonStatus;
import com.kalix.framework.core.impl.biz.GenericBizServiceImpl;
import com.kalix.framework.core.util.StringUtils;

import javax.transaction.Transactional;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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
    public String getTemplateResult(String templateName, Map<String, String> templateMap) {

        String result = "";
        if (StringUtils.isEmpty(templateName)) {
            return result;
        }

        List<TemplateBean> templateBeans = dao.find("select ob from TemplateBean ob where ob.name = ?1", templateName);
        if (templateBeans != null && templateBeans.size() > 0) {
            result = templateBeans.get(0).getContent();
            if (templateMap != null && templateMap.size() > 0) {
                String key, value;
                Iterator<Map.Entry<String, String>> it = templateMap.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry<String, String> entry = it.next();
                    key = "${" + entry.getKey() + "}";
                    value = entry.getValue();
                    result = result.replaceAll(key, value);
                }
            }
        }

        return result;
    }
}
