package com.kalix.admin.duty.api.dao;


import com.kalix.admin.duty.entities.DutyUserBean;
import com.kalix.framework.core.api.dao.IGenericDao;

import java.util.List;

/**
 * Created by zangyanming on 2016/3/15.
 */
public interface IDutyUserBeanDao extends IGenericDao<DutyUserBean, String> {
    void deleteByDutyId(String id);

    String findDutyIdByUserId(String userId);

    /**
     * 通过机构id查找职位信息 2016-07-05 by p
     *
     * @param dutyId
     * @return
     */
    List<DutyUserBean> findByDutyId(String dutyId);

    /**
     * 通过用户集合查找职位信息 2016-08-25 by p
     *
     * @param userId
     * @return
     */
    List<DutyUserBean> findByUserIds(List<String> userId);
}