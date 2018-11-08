package com.kalix.admin.core.dao;

import com.kalix.admin.core.api.dao.IWorkGroupUserBeanDao;
import com.kalix.admin.core.entities.WorkGroupUserBean;

/**
 * 工作组用户DAO实现
 * @author majian <br/>
 *         date:2015-7-23
 * @version 1.0.0
 */
public class WorkGroupUserBeanDaoImpl extends BaseAdminDao<WorkGroupUserBean, String> implements IWorkGroupUserBeanDao {
    private final String className = WorkGroupUserBean.class.getName();

    @Override
    public void deleteByWorkGroupId(String id) {
        super.updateNativeQuery("delete from sys_workGroup_user where groupId='"+id+"'");
    }
}
