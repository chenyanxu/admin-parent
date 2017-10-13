package com.kalix.admin.duty.dao;

import com.kalix.admin.duty.api.dao.IDataAuthUserBeanDao;
import com.kalix.admin.duty.entities.DataAuthUserBean;
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
public class DataAuthUserBeanDaoImpl extends GenericDao<DataAuthUserBean, Long> implements IDataAuthUserBeanDao {
    @Override
    @PersistenceContext(unitName = "admin-duty-unit")
    public void setEntityManager(EntityManager em) {
        super.setEntityManager(em);
    }

}
