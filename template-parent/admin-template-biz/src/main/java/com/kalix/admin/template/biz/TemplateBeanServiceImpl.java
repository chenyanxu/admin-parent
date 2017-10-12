package com.kalix.admin.template.biz;


import com.kalix.admin.template.api.biz.ITemplateBeanService;
import com.kalix.admin.template.api.dao.ITemplateBeanDao;
import com.kalix.admin.template.entities.TemplateBean;
import com.kalix.framework.core.api.exception.KalixRuntimeException;
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
    public JsonStatus saveEntity(TemplateBean entity) {
        JsonStatus jsonStatus = new JsonStatus();
        Map<String, String> params = new HashMap();
        params.put("name", entity.getName());
        String jsonStr = SerializeUtil.serializeJson(params);

        // 根据模板名称查找模板，判断该模板名称是否已经存在，如果存在给前台提示信息
        JsonData jsonData = super.getAllByNativeQuery(0, 10, jsonStr);
        List<TemplateBean> templateBeanList = jsonData.getData();
        if (templateBeanList != null && templateBeanList.size() > 0) {
            jsonStatus.setFailure(true);
            jsonStatus.setMsg("该模板名称已经存在，请修改！");
        } else {
            jsonStatus = super.saveEntity(entity);
        }

        return jsonStatus;
    }

    /**
     * 根据模板名称以及该模板的项目值返回替换后的模板
     * @param templateName  模板名称
     * @param templateMap   该模板的项目
     * @return String       替换后的模板
     * */

    public String getTemplateResult(String templateName, Map<String, String> templateMap) {
        String result = "";
        if (StringUtils.isEmpty(templateName) || templateMap == null || templateMap.size() < 1) {
            return result;
        }

        Map<String, String> params = new HashMap();
        params.put("name", templateName);
        String jsonStr = SerializeUtil.serializeJson(params);

        JsonData jsonData = super.getAllByNativeQuery(0, 10, jsonStr);
        List<TemplateBean> templateBeanList = jsonData.getData();
        if (templateBeanList != null && templateBeanList.size() > 0) {
            String template = templateBeanList.get(0).getContent();

            String key,value;
            Iterator<Map.Entry<String, String>> it = templateMap.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<String, String> entry = it.next();
                key = "${" + entry.getKey() + "}";
                value = entry.getValue();
                result = template;
                result = result.replaceAll(key,value);
            }
        }

        return result;
    }

}
