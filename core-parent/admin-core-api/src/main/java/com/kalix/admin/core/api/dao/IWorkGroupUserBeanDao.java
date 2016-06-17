package com.kalix.admin.core.api.dao;

import com.kalix.admin.core.entities.WorkGroupUserBean;
import com.kalix.framework.core.api.persistence.IGenericDao;

/**
 * 工作组用户关联DAO接口
 * @author majian <br/>
 *         date:2015-7-27
 * @version 1.0.0
 */
public interface IWorkGroupUserBeanDao extends IGenericDao<WorkGroupUserBean, Long> {
    void deleteByWorkGroupId(long id);
}
