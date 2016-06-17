package com.kalix.admin.audit.biz.biz;

import com.google.gson.Gson;
import com.kalix.admin.audit.api.dao.IAuditBeanDao;
import com.kalix.admin.audit.entities.AuditBean;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventHandler;

/**
 * 用户登录登出的事件监听处理类
 * Created by sunlf on 2015/11/22.
 */
public class AuditUserLoginEventImpl implements EventHandler {
    IAuditBeanDao dao;

    public void setDao(IAuditBeanDao dao) {
        this.dao = dao;
    }

    @Override
    public void handleEvent(Event event) {
        Gson gson = new Gson();
        String json = (String) event.getProperty("message");
        AuditBean auditBean = gson.fromJson(json, AuditBean.class);
        dao.save(auditBean);
    }
}
