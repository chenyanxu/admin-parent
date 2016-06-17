package com.kalix.admin.core.api.dao;

import com.kalix.admin.core.entities.RoleApplicationBean;
import com.kalix.framework.core.api.persistence.IGenericDao;

import java.util.List;

/**
 * 角色应用关联DAO接口
 * @author majian <br/>
 *         date:2015-7-27
 * @version 1.0.0
 */
public interface IRoleApplicationBeanDao extends IGenericDao<RoleApplicationBean, Long> {
    /**
     * 根据角色ID删除所有关联
     * @param id
     */
    void deleteByRoleId(long id);

    /**
     * 返回指定角色下所有与应用的关联
     * @param id
     * @return
     */
    List<RoleApplicationBean> getRoleApplicationsByRoleId(long id);
}
