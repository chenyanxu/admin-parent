package com.kalix.admin.core.api.dao;

import com.kalix.admin.core.entities.PermissionBean;
import com.kalix.framework.core.api.dao.IGenericDao;

import java.util.List;

/**
 * @类描述：权限Dao接口类
 * @创建人：sunlf
 * @创建时间：2014-05-14 上午11:21
 * @修改人：
 * @修改时间：
 * @修改备注：
 */

public interface IPermissionBeanDao extends IGenericDao<PermissionBean, Long> {
    PermissionBean getRootPermission();

    List<PermissionBean> getChildPermission(PermissionBean permissionBean);
}
