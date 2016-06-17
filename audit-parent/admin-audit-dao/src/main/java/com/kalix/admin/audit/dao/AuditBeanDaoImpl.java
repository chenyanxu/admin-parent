package com.kalix.admin.audit.dao;

import com.kalix.admin.audit.api.dao.IAuditBeanDao;
import com.kalix.admin.audit.entities.AuditBean;
import com.kalix.framework.core.impl.persistence.GenericDao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.UUID;

/**
 * @类描述：审计管理
 * @创建人： zhangqingxin
 * @创建时间：2014/10/10
 * @修改人：
 * @修改时间：
 * @修改备注：
 */
public class AuditBeanDaoImpl extends GenericDao<AuditBean, Long> implements IAuditBeanDao {
    private String uuid;

    public AuditBeanDaoImpl() {
        uuid = UUID.randomUUID().toString();
    }

    @Override
    @PersistenceContext(unitName = "audit-unit")
    public void setEntityManager(EntityManager em) {
        super.setEntityManager(em);
    }
}
