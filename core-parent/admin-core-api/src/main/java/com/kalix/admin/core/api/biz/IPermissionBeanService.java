package com.kalix.admin.core.api.biz;

import com.kalix.admin.core.entities.PermissionBean;
import com.kalix.admin.core.entities.RoleBean;
import com.kalix.framework.core.api.biz.IBizService;

import java.util.List;
import java.util.Set;

/**
 * @类描述：权限服务接口类
 * @创建人：sunlf
 * @创建时间：2014-05-14 上午11:19
 * @修改人：
 * @修改时间：
 * @修改备注：
 */

public interface IPermissionBeanService extends IBizService<PermissionBean> {
    /**
     * 获得root权限信息
     *
     * @return
     */
    PermissionBean getRootPermission();

    /**
     * 获得子权限
     * @param permissionBean
     * @return
     */
    List<PermissionBean> getChildPermission(PermissionBean permissionBean);

    /**
     * 查询指定权限
     * @param permissionBean
     * @return
     */
    List<PermissionBean> query(PermissionBean permissionBean);

    /**
     * 获得根对象列表
     * @return
     */
    List<PermissionBean> getRootBeanList();

    /**
     * 获得子对象列表
     * @param permissionBean
     * @return
     */
    List<PermissionBean> getChildBeanList(PermissionBean permissionBean);

    /**
     * 根据角色分配权限
     *
     * @param roleBean     角色bean
     * @param checkedNodes 权限列表
     */
    void assignPermission(RoleBean roleBean, Set<PermissionBean> checkedNodes);


    /**
     *
     * 根据用户编号返回所有应用代码(理应放到ApplicationBeanService中,为了避免相互依赖问题,所以放置PermissionBeanService中)
     * 首先查询角色,之后查询角色与应用关联,最后根据关联关系中的应用ID查询应用
     * @return
     */
    List<String> getApplicationCodesByUserId(long userId);

    /**
     *
     * 根据用户编号返回所有功能代码(理应放到FunctionBeanService中,为了避免相互依赖问题,所以放置PermissionBeanService中)
     * 首先查询角色,之后查询角色与功能关联,最后根据关联关系中的功能ID查询应用
     * @return
     */
    List<String> getFunctionCodesByUserId(long userId);
}
