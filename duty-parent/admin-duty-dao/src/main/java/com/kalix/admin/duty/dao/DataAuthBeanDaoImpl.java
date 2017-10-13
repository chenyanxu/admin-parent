package com.kalix.admin.duty.dao;

import com.kalix.admin.duty.api.dao.IDataAuthBeanDao;
import com.kalix.admin.duty.entities.DataAuthBean;
import com.kalix.framework.core.impl.dao.GenericDao;

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
public class DataAuthBeanDaoImpl extends GenericDao<DataAuthBean, Long> implements IDataAuthBeanDao {
    @Override
    @PersistenceContext(unitName = "admin-duty-unit")
    public void setEntityManager(EntityManager em) {
        super.setEntityManager(em);
    }
}
