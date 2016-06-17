package com.kalix.admin.core.api.dao;

import com.kalix.admin.core.entities.DepartmentUserBean;
import com.kalix.framework.core.api.persistence.IGenericDao;

/**
 * 部门用户关联DAO接口
 * @author majian <br/>
 *         date:2015-7-27
 * @version 1.0.0
 */
public interface IDepartmentUserBeanDao extends IGenericDao<DepartmentUserBean, Long> {
    void deleteByDepartmentId(long id);

    long findDepartmentIdByUserId(long userId);
}
