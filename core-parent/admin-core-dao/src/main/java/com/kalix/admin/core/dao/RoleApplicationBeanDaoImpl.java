package com.kalix.admin.core.dao;

import com.kalix.admin.core.api.dao.IRoleApplicationBeanDao;
import com.kalix.admin.core.entities.RoleApplicationBean;

import java.util.List;

/**
 * 角色应用DAO实现
 * @author majian <br/>
 *         date:2015-7-23
 * @version 1.0.0
 */
public class RoleApplicationBeanDaoImpl extends BaseAdminDao<RoleApplicationBean, Long> implements IRoleApplicationBeanDao {
    private final String className = RoleApplicationBean.class.getName();

    @Override
    public void deleteByRoleId(long id) {
        super.updateNativeQuery("delete from sys_role_application where roleId="+id);
    }

    @Override
    public List<RoleApplicationBean> getRoleApplicationsByRoleId(long id) {
        return super.find("select rab from RoleApplicationBean rab where rab.roleId=?1",id);
    }
}
