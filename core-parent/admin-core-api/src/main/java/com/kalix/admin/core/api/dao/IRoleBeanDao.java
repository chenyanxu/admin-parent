package com.kalix.admin.core.api.dao;

import com.kalix.admin.core.entities.RoleBean;
import com.kalix.admin.core.entities.UserBean;
import com.kalix.framework.core.api.persistence.IGenericDao;

import java.util.List;

/**
 * @类描述：角色管理Dao接口
 * @创建人：sunlf
 * @创建时间：下午6:27
 * @修改人：
 * @修改时间：
 * @修改备注：
 */
public interface IRoleBeanDao extends IGenericDao<RoleBean, Long> {
    /**
     * 获得角色名列表
     *
     * @return
     */
    List<String> getRoleNameList();

    /**
     * 通过角色名获得角色对象
     *
     * @param roleName 角色名
     * @return
     */
    RoleBean getRole(String roleName);


    /**
     * 获得角色.
     * @param roleId
     * @return
     */
    RoleBean getRole(Long roleId);

    /**
     * 删除一个角色.
     *
     * @param roleId 角色ID
     */
    void removeRole(Long roleId);

    /**
     * 获得角色名列表
     *
     * @return
     */
    List<String> getRoleNameList(UserBean userBean);

    /**
     * 保存角色.
     *
     * @param role the object to be saved
     * @return the persisted User object
     */
    RoleBean saveRole(RoleBean role);
}
