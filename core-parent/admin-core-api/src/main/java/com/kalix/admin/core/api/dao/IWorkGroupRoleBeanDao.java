package com.kalix.admin.core.api.dao;

import com.kalix.admin.core.entities.WorkGroupRoleBean;
import com.kalix.framework.core.api.persistence.IGenericDao;

/**
 * 工作组角色关联DAO接口
 * @author majian <br/>
 *         date:2015-7-27
 * @version 1.0.0
 */
public interface IWorkGroupRoleBeanDao extends IGenericDao<WorkGroupRoleBean, Long> {
    void deleteByWorkGroupId(long id);
}
