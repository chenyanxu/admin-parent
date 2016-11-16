package com.kalix.admin.audit.biz;

import com.google.gson.Gson;
import com.kalix.admin.audit.api.dao.IAuditBeanDao;
import com.kalix.admin.audit.api.dao.IAuditConfigBeanDao;
import com.kalix.admin.audit.entities.AuditBean;
import com.kalix.admin.audit.entities.AuditConfigBean;
import com.kalix.framework.core.api.dto.AuditDTOBean;
import com.kalix.framework.core.api.security.IShiroService;
import com.kalix.framework.core.util.SerializeUtil;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventHandler;

import java.util.Date;

/**
 * 业务数据的事件监听处理类
 * Created by sunlf on 2016/10/13.
 */
public class AuditBusinessEventImpl implements EventHandler {
    private IAuditBeanDao dao;
    private IAuditConfigBeanDao configDao;
    protected IShiroService shiroService;

    private final static String EVENT_TOPIC_SAVE = "com/kalix/business/save";
    private final static String EVENT_TOPIC_UPDATE = "com/kalix/business/update";
    private final static String EVENT_TOPIC_DELETE = "com/kalix/business/delete";


    @Override
    public void handleEvent(Event event) {
        AuditBean auditBean = new AuditBean();
        auditBean.setCreationDate(new Date());
        Gson gson = new Gson();
        String json = (String) event.getProperty("message");
        AuditDTOBean dto = gson.fromJson(json, AuditDTOBean.class);
        AuditConfigBean configBean = configDao.findUnique("select a from AuditConfigBean a where a.clsName=?1", dto.getClsName());
        //未设置监控则退出
        if (configBean == null || (!configBean.getEnable()))
            return;

        auditBean.setAction(dto.getAction());
        auditBean.setActor(dto.getActor());
        auditBean.setAppName(configBean.getAppName());
        auditBean.setFunName(configBean.getFunName());

        if (event.getTopic().equals(EVENT_TOPIC_SAVE)) {
            auditBean.setContent(dto.getNewEntity().toString());
        } else if (event.getTopic().equals(EVENT_TOPIC_UPDATE)) {
            String strOld = (String) event.getProperty("oldEntity");
            String strNew = (String) event.getProperty("newEntity");
            try {
                auditBean.setContent(AuditUtil.Match(
                        SerializeUtil.unserializeJson(strNew, Class.forName(dto.getClsName())),
                        SerializeUtil.unserializeJson(strOld, Class.forName(dto.getClsName())),
                        dto.getClsName()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (event.getTopic().equals(EVENT_TOPIC_DELETE)) {
            auditBean.setContent(dto.getOldEntity().toString());
        }
        dao.save(auditBean);
    }

    public void setDao(IAuditBeanDao dao) {
        this.dao = dao;
    }

    public void setConfigDao(IAuditConfigBeanDao configDao) {
        this.configDao = configDao;
    }

    public void setShiroService(IShiroService shiroService) {
        this.shiroService = shiroService;
    }
}
