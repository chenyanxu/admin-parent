package com.kalix.admin.duty.api.dao;

import com.kalix.admin.duty.entities.DataAuthUserBean;
import com.kalix.framework.core.api.dao.IGenericDao;

import java.util.List;

/**
 * Created by zangyanming on 2016/3/15.
 */
public interface IDataAuthUserBeanDao extends IGenericDao<DataAuthUserBean, String> {
    void deleteByDataAuthId(String dataAuthId);

    /**
     * 通过数据权限id查找实体List
     *
     * @param dataAuthId
     * @return
     */
    List<DataAuthUserBean> findByDataAuthId(String dataAuthId);

    /**
     * 通过用户id查找实体List
     * @param userId
     * @return
     */
    List<DataAuthUserBean> findByUserId(String userId);
}
