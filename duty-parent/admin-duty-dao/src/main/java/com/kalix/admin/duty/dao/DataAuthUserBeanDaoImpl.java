package com.kalix.admin.duty.dao;

import com.kalix.admin.duty.api.dao.IDataAuthUserBeanDao;
import com.kalix.admin.duty.entities.DataAuthUserBean;
import com.kalix.framework.core.impl.dao.GenericDao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

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

    @Override
    public List<DataAuthUserBean> getEntitiesByUserId(Long userId) {
        final Query query = this.createQuery("select d.userId,d.dataAuthId from DataAuthUserBean d where d.id = ?1", userId);
        final List<DataAuthUserBean> resultList = query.getResultList();
        return resultList;
    }
}
