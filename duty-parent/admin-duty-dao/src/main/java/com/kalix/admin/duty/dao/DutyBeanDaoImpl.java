package com.kalix.admin.duty.dao;

import com.kalix.admin.duty.api.dao.IDutyBeanDao;
import com.kalix.admin.duty.entities.DutyBean;
import com.kalix.framework.core.impl.persistence.GenericDao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


/**
 * @类描述：
 * @创建人：
 * @创建时间：
 * @修改人：
 * @修改时间：
 * @修改备注：
 */
public class DutyBeanDaoImpl extends GenericDao<DutyBean, Long> implements IDutyBeanDao {
    @Override
    @PersistenceContext(unitName = "duty-unit")
    public void setEntityManager(EntityManager em) {
        super.setEntityManager(em);
    }
}
