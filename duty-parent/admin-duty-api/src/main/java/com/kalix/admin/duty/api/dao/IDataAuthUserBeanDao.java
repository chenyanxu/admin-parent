package com.kalix.admin.duty.api.dao;


import com.kalix.admin.duty.entities.DataAuthUserBean;
import com.kalix.framework.core.api.dao.IGenericDao;

import java.util.List;

/**
 * Created by zangyanming on 2016/3/15.
 */
public interface IDataAuthUserBeanDao extends IGenericDao<DataAuthUserBean, Long> {

    List<DataAuthUserBean> getEntitiesByUserId(Long userId);
}