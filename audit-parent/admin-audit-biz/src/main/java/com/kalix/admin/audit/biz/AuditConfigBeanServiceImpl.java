package com.kalix.admin.audit.biz;


import com.kalix.admin.audit.api.biz.IAuditConfigBeanService;
import com.kalix.admin.audit.api.dao.IAuditConfigBeanDao;
import com.kalix.admin.audit.entities.AuditConfigBean;
import com.kalix.framework.core.api.persistence.JsonStatus;
import com.kalix.framework.core.impl.biz.GenericBizServiceImpl;

import java.util.UUID;

/**
 * @类描述：审计配置管理
 * @创建人： sunlf
 * @创建时间：2014/10/10
 * @修改人：
 * @修改时间：
 * @修改备注：
 */
public class AuditConfigBeanServiceImpl extends GenericBizServiceImpl<IAuditConfigBeanDao, AuditConfigBean> implements IAuditConfigBeanService {
    private JsonStatus jsonStatus = new JsonStatus();
    private String uuid;

    public AuditConfigBeanServiceImpl() {
        uuid = UUID.randomUUID().toString();
        super.init(AuditConfigBean.class.getName());
    }
}
