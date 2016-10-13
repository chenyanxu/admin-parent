package com.kalix.admin.audit.biz;

import com.google.gson.Gson;
import com.kalix.admin.audit.api.dao.IAuditBeanDao;
import com.kalix.admin.audit.api.dao.IAuditConfigBeanDao;
import com.kalix.admin.audit.entities.AuditBean;
import com.kalix.framework.core.api.dto.AuditDTOBean;
import com.kalix.framework.core.api.security.IShiroService;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventHandler;

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
        Gson gson = new Gson();
        String json = (String) event.getProperty("message");
        AuditDTOBean dto = gson.fromJson(json, AuditDTOBean.class);
        auditBean.setAction(dto.getAction());
        auditBean.setActor(shiroService.getCurrentUserRealName());
//        List<AuditConfigBean> configList= configDao.find("select * from AuditConfigBean a where a.clsName=?",dto.getClsName());
        if (event.getTopic().equals(EVENT_TOPIC_SAVE)) {
            auditBean.setContent(dto.getNewEntity().toString());
        } else if (event.getTopic().equals(EVENT_TOPIC_UPDATE)) {
            auditBean.setContent(AuditUtil.Match(dto.getNewEntity(), dto.getOldEntity(), dto.getClsName()));
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
