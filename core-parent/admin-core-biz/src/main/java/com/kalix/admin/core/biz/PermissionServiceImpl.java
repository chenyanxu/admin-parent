package com.kalix.admin.core.biz;

import com.kalix.admin.core.api.biz.IPermissionService;
import com.kalix.admin.core.api.biz.IRoleBeanService;
import com.kalix.admin.core.api.biz.IWorkGroupBeanService;
import com.kalix.admin.core.api.dao.IApplicationBeanDao;
import com.kalix.admin.core.api.dao.IFunctionBeanDao;
import com.kalix.admin.core.api.dao.IRoleApplicationBeanDao;
import com.kalix.admin.core.api.dao.IRoleFunctionBeanDao;
import com.kalix.admin.core.entities.*;
import com.kalix.framework.core.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @类描述：权限服务实现类
 * @创建人：sunlf
 * @创建时间：2014-05-14 上午11:23
 * @修改人：
 * @修改时间：
 * @修改备注：
 */

public class PermissionServiceImpl  implements IPermissionService {
    private IRoleBeanService roleBeanService;
    private IRoleApplicationBeanDao roleApplicationBeanDao;
    private IWorkGroupBeanService workGroupBeanService;
    private IApplicationBeanDao applicationBeanDao;
    private IRoleFunctionBeanDao roleFunctionBeanDao;
    private IFunctionBeanDao functionBeanDao;


    /**
     * 获取指定用户的applicationCodes
     *
     * @param userId
     * @return
     */
    @Override
    public List<String> getApplicationCodesByUserId(long userId) {
        Assert.notNull(userId, "用户编号不能为空.");
        List<String> applicationCodes = new ArrayList<>();

        // 用户所有角色
        List<RoleBean> roleList = roleBeanService.getRolesByUserId(userId);

        // 用户所有工作组
        List<WorkGroupUserBean> workGroupUserList = workGroupBeanService.getWorkGroupUserBeanByUserId(userId);

        // 所以application
        List<ApplicationBean> applicationList = applicationBeanDao.getAll();

        // 获取该用户工作组对应的角色，并添加到roleList中
        workGroupUserList.stream().forEach(n -> {
            List<RoleBean> roleWrokGroupList = roleBeanService.getRolesByWorkGroupId(n.getGroupId());
            if (roleWrokGroupList != null && !roleWrokGroupList.isEmpty()) {
                roleList.addAll(roleWrokGroupList);
            }
        });

        // 过滤重复角色，并获取角色对应applicationCodes
        roleList.stream().distinct().forEach(n -> {
            // 角色与application对应关系
            roleApplicationBeanDao.getRoleApplicationsByRoleId(n.getId()).stream()
                    .forEach(m -> applicationList.stream().filter(app -> m.getApplicationId().equals(app.getId()))
                            .forEach(app -> applicationCodes.add(app.getCode())));
        });

        // 过滤重复applicationCodes
        return applicationCodes.stream().distinct().collect(Collectors.toList());
    }

    /**
     * 获取指定用户的functionCodes
     *
     * @param userId
     * @return
     */
    @Override
    public List<String> getFunctionCodesByUserId(long userId) {
        Assert.notNull(userId, "用户编号不能为空.");
        List<String> functionCodes=new ArrayList<>();

        // 用户所有角色
        List<RoleBean> roleList = roleBeanService.getRolesByUserId(userId);

        // 用户所有工作组
        List<WorkGroupUserBean> workGroupUserList = workGroupBeanService.getWorkGroupUserBeanByUserId(userId);

        // 所以application
        List<FunctionBean> functionList = functionBeanDao.getAll();

        // 获取该用户工作组对应的角色，并添加到roleList中
        workGroupUserList.stream().forEach(n -> {
            List<RoleBean> roleWrokGroupList = roleBeanService.getRolesByWorkGroupId(n.getGroupId());
            if (roleWrokGroupList != null && !roleWrokGroupList.isEmpty()) {
                roleList.addAll(roleWrokGroupList);
            }
        });

        // 过滤重复角色，并获取角色对应functionCodes
        roleList.stream().distinct().forEach(n -> {
            // 角色与function对应关系
            roleFunctionBeanDao.getRoleFunctionsByRoleId(n.getId()).stream()
                    .forEach(m -> functionList.stream().filter(func -> m.getFunctionId().equals(func.getId()))
                            .forEach(func -> functionCodes.add(func.getPermission())));
        });

        // 过滤重复functionCodes
        return functionCodes.stream().distinct().collect(Collectors.toList());
    }

    public void setFunctionBeanDao(IFunctionBeanDao functionBeanDao) {
        this.functionBeanDao = functionBeanDao;
    }

    public void setRoleFunctionBeanDao(IRoleFunctionBeanDao roleFunctionBeanDao) {
        this.roleFunctionBeanDao = roleFunctionBeanDao;
    }

    public void setApplicationBeanDao(IApplicationBeanDao applicationBeanDao) {
        this.applicationBeanDao = applicationBeanDao;
    }

    public void setWorkGroupBeanService(IWorkGroupBeanService workGroupBeanService) {
        this.workGroupBeanService = workGroupBeanService;
    }

    public void setRoleApplicationBeanDao(IRoleApplicationBeanDao roleApplicationBeanDao) {
        this.roleApplicationBeanDao = roleApplicationBeanDao;
    }

    public void setRoleBeanService(IRoleBeanService roleBeanService) {
        this.roleBeanService = roleBeanService;
    }
}