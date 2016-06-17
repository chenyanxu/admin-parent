package com.kalix.admin.core.dao;

import com.kalix.admin.core.api.dao.IRoleUserBeanDao;
import com.kalix.admin.core.entities.RoleUserBean;

/**
 * 角色用户DAO实现
 * @author majian <br/>
 *         date:2015-7-23
 * @version 1.0.0
 */
public class RoleUserBeanDaoImpl extends BaseAdminDao<RoleUserBean, Long> implements IRoleUserBeanDao {
    private final String className = RoleUserBean.class.getName();

    @Override
    public void deleteByRoleId(long id) {
        super.updateNativeQuery("delete from sys_role_user where roleId="+id);
    }
}
