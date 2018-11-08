package com.kalix.admin.core.api.dao;

import com.kalix.admin.core.entities.RoleFunctionBean;
import com.kalix.framework.core.api.dao.IGenericDao;

import java.util.List;

/**
 * 角色功能关联DAO接口
 * @author majian <br/>
 *         date:2015-7-27
 * @version 1.0.0
 */
public interface IRoleFunctionBeanDao extends IGenericDao<RoleFunctionBean, String> {
    void deleteByRoleId(String id);

    List<RoleFunctionBean> getRoleFunctionsByRoleId(String roleId);
}
