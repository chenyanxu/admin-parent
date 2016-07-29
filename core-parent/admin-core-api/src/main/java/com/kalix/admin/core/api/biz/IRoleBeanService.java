package com.kalix.admin.core.api.biz;

import com.kalix.admin.core.dto.model.AuthorizationDTO;
import com.kalix.admin.core.entities.RoleBean;
import com.kalix.admin.core.entities.RoleUserBean;
import com.kalix.admin.core.entities.UserBean;
import com.kalix.framework.core.api.biz.IBizService;
import com.kalix.framework.core.api.persistence.JsonStatus;
import com.kalix.framework.core.api.persistence.JsonData;

import java.util.List;

/**
 * @类描述：角色管理业务接口类
 * @创建人：sunlf
 * @创建时间：2014-04-03 下午6:29
 * @修改人：
 * @修改时间：
 * @修改备注：
 */

public interface IRoleBeanService extends IBizService<RoleBean> {
    /**
     * 获得角色名称列表
     *
     * @return
     */
    List<String> getRoleNameList();

    JsonData getAllRole();


    /**
     * 返回授权树
     * @return
     */
    AuthorizationDTO getAuthorizationTree(long roleId);

    /**
     * 保存授权信息
     */
    JsonStatus saveAuthorization(List ids);

    /**
     * 根据用户获得角色名称列表
     *
     * @return
     */
    List<String> getRoleNameList(UserBean userBean);

//    List<String> getRoleNameListByLoginName(String loginName);
    /**
     * 根据角色保存该角色关联的用户列表
     *
     * @param roleBean
     * @param userSelect
     */
    void saveRoleUser(RoleBean roleBean, List<UserBean> userSelect);

    List<RoleBean> query(RoleBean roleBean);

    /**
     * 通过role获得UserLIst
     *
     * @param roleBean
     * @return
     */
    List<RoleUserBean> getUserList(RoleBean roleBean);

    /**
     * 根据角色名称返回RoleBean
     * @param roleName 角色名称
     * @return
     */
    RoleBean queryByRoleName(String roleName);

    /**
     * 返回用户下所有角色
     * @param userId
     * @return
     */
    List<RoleBean> getRolesByUserId(long userId);

    /**
     * 返回工作组下所有角色
     * @param workGroupId
     * @return
     */
    List<RoleBean> getRolesByWorkGroupId(long workGroupId);

    /**
     * 保存角色与用户关联
     * @return
     */
    JsonStatus saveRoleUsers(List ids);


    /**
     * 根据角色ID获得所有用户
     * @param id
     * @return
     */
    List getUserIdsByRoleId(long id);
}
