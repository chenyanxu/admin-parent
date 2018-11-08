package com.kalix.admin.core.dao;

import com.kalix.admin.core.api.dao.IWorkGroupBeanDao;
import com.kalix.admin.core.entities.WorkGroupBean;

/**
 * 工作组管理DAO实现
 * @author majian <br/>
 *         date:2015-7-23
 * @version 1.0.0
 */
public class WorkGroupBeanDaoImpl extends BaseAdminDao<WorkGroupBean, String> implements IWorkGroupBeanDao {
    private final String className = WorkGroupBean.class.getName();

    /**
     * Constructor that sets the entity to User.class.
     */
    public WorkGroupBeanDaoImpl() {
//        super.setEntityManager(UserBean.class);
    }
}
