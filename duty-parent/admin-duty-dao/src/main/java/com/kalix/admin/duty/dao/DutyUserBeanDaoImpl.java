package com.kalix.admin.duty.dao;

import com.kalix.admin.duty.api.dao.IDutyUserBeanDao;
import com.kalix.admin.duty.entities.DutyUserBean;
import com.kalix.framework.core.impl.dao.GenericDao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zangyanming on 2016/3/15.
 */
public class DutyUserBeanDaoImpl extends GenericDao<DutyUserBean, Long> implements IDutyUserBeanDao {
    @Override
    @PersistenceContext(unitName = "admin-duty-unit")
    public void setEntityManager(EntityManager em) {
        super.setEntityManager(em);
    }

    @Override
    public void deleteByDutyId(long id) {
        super.updateNativeQuery("delete from sys_duty_user where dutyId=" + id);
    }

    @Override
    public long findDutyIdByUserId(long userId) {
        List<DutyUserBean> dutyUserBeanList = findByNativeSql("select * from sys_duty_user where userId=" + userId, DutyUserBean.class, null);
        if (dutyUserBeanList != null && dutyUserBeanList.size() > 0) {
            return dutyUserBeanList.get(0).getDutyId();
        }
        return 0;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<DutyUserBean> findByDutyId(Long dutyId) {
        return (List<DutyUserBean>) this.find("select t from DutyUserBean t where t.dutyId = ?1", dutyId);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<DutyUserBean> findByUserIds(List<Long> userId) {
        if (userId != null && !userId.isEmpty()) {
            return (List<DutyUserBean>) this.find("select t from DutyUserBean t where t.userId in (?1) order by t.userId", userId);
        }
        else {
            return new ArrayList<>();
        }
    }
}
