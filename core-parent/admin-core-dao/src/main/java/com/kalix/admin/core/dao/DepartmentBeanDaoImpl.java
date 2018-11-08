package com.kalix.admin.core.dao;

import com.kalix.admin.core.api.dao.IDepartmentBeanDao;
import com.kalix.admin.core.entities.DepartmentBean;

/**
 * 部门管理DAO实现
 * @author majian <br/>
 *         date:2015-7-23
 * @version 1.0.0
 */
public class DepartmentBeanDaoImpl extends BaseAdminDao<DepartmentBean, String> implements IDepartmentBeanDao {
    private final String className = DepartmentBean.class.getName();

}
