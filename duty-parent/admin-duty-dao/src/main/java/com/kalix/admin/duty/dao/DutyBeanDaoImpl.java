package com.kalix.admin.duty.dao;

import com.kalix.admin.duty.api.dao.IDutyBeanDao;
import com.kalix.admin.duty.entities.DutyBean;
import com.kalix.framework.core.impl.dao.GenericDao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


/**
 * @类描述：
 * @创建人：
 * @创建时间：
 * @修改人：
 * @修改时间：
 * @修改备注：
 */
public class DutyBeanDaoImpl extends GenericDao<DutyBean, String> implements IDutyBeanDao {
    @Override
    @PersistenceContext(unitName = "admin-duty-unit")
    public void setEntityManager(EntityManager em) {
        super.setEntityManager(em);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<DutyBean> findByOrgId(String orgId) {
        return (List<DutyBean>) this.find("select t from DutyBean t where t.orgid = ?1 ", orgId);
    }
}
