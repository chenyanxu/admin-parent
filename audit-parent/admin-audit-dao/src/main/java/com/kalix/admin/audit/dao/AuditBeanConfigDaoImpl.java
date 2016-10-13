package com.kalix.admin.audit.dao;

import com.kalix.admin.audit.api.dao.IAuditConfigBeanDao;
import com.kalix.admin.audit.entities.AuditConfigBean;
import com.kalix.framework.core.impl.dao.GenericDao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.UUID;

/**
 * @类描述：审计配置管理
 * @创建人： zhangqingxin
 * @创建时间：2014/10/10
 * @修改人：
 * @修改时间：
 * @修改备注：
 */
public class AuditBeanConfigDaoImpl extends GenericDao<AuditConfigBean, Long> implements IAuditConfigBeanDao {
    private String uuid;

    public AuditBeanConfigDaoImpl() {
        uuid = UUID.randomUUID().toString();
    }

    @Override
    @PersistenceContext(unitName = "admin-audit-unit")
    public void setEntityManager(EntityManager em) {
        super.setEntityManager(em);
    }
}
