package com.kalix.admin.core.dao;

import com.kalix.admin.core.api.dao.IPermissionBeanDao;
import com.kalix.admin.core.entities.PermissionBean;

import java.util.List;

/**
 * @类描述：权限Dao实现类
 * @创建人：sunlf
 * @创建时间：2014-05-14 上午11:26
 * @修改人：
 * @修改时间：
 * @修改备注：
 */

public class PermissionBeanDaoImpl extends BaseAdminDao<PermissionBean, Long> implements IPermissionBeanDao {
    @Override
    public PermissionBean getRootPermission() {
        PermissionBean permissionBean = this.findUnique("select u from PermissionBean u where u.parent is null");
        return permissionBean;
    }

    @Override
    public List<PermissionBean> getChildPermission(PermissionBean permissionBean) {
        return this.find("select u from PermissionBean u where u.parent=?1", permissionBean);
    }
}
