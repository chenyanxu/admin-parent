package com.kalix.admin.core.api.dao;

import com.kalix.admin.core.entities.RoleUserBean;
import com.kalix.framework.core.api.persistence.IGenericDao;

/**
 * 角色用户关联DAO接口
 * @author majian <br/>
 *         date:2015-7-27
 * @version 1.0.0
 */
public interface IRoleUserBeanDao extends IGenericDao<RoleUserBean, Long> {
    void deleteByRoleId(long id);
}
