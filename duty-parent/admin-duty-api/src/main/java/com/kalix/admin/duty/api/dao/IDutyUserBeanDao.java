package com.kalix.admin.duty.api.dao;


import com.kalix.admin.duty.entities.DutyUserBean;
import com.kalix.framework.core.api.persistence.IGenericDao;

/**
 * Created by zangyanming on 2016/3/15.
 */
public interface IDutyUserBeanDao extends IGenericDao<DutyUserBean, Long> {
    void deleteByDutyId(long depId, long id);

    long findDutyIdByUserId(long depid, long userId);
}