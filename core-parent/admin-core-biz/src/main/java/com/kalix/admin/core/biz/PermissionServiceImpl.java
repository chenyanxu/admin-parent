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


    @Override
    public List<String> getApplicationCodesByUserId(long userId) {
        Assert.notNull(userId, "用户编号不能为空.");
        List<String> applicationCodes=new ArrayList<String>();
        //返回用户下所有角色
        List<RoleBean> roleBeans=roleBeanService.getRolesByUserId(userId);
        if(roleBeans!=null&&!roleBeans.isEmpty()){
            for(RoleBean roleBean:roleBeans){
                List<RoleApplicationBean> roleApplicationBeans=roleApplicationBeanDao.getRoleApplicationsByRoleId(roleBean.getId());
                fillApplicationCodeByRoles(applicationCodes, roleApplicationBeans);
            }
        }
        //返回用户下所有工作组,根据工作组再返回工作组下所有角色
        List<WorkGroupUserBean> workGroupUserBeans=workGroupBeanService.getWorkGroupUserBeanByUserId(userId);
        if(workGroupUserBeans!=null&&!workGroupUserBeans.isEmpty()){
            for(WorkGroupUserBean workGroupUserBean:workGroupUserBeans){
                List<RoleBean> _roleBeans = roleBeanService.getRolesByWorkGroupId(workGroupUserBean.getGroupId());
                if(_roleBeans!=null&&!_roleBeans.isEmpty()){
                    for(RoleBean roleBean:_roleBeans){
                        List<RoleApplicationBean> roleApplicationBeans=roleApplicationBeanDao.getRoleApplicationsByRoleId(roleBean.getId());
                        fillApplicationCodeByRoles(applicationCodes, roleApplicationBeans);
                    }
                }
            }
        }
        return applicationCodes;
    }

    @Override
    public List<String> getFunctionCodesByUserId(long userId) {
        Assert.notNull(userId, "用户编号不能为空.");
        List<String> functionCodes=new ArrayList<String>();
        //返回用户下所有角色
        List<RoleBean> roleBeans=roleBeanService.getRolesByUserId(userId);
        if(roleBeans!=null&&!roleBeans.isEmpty()){
            for(RoleBean roleBean:roleBeans){
                List<RoleFunctionBean> roleFunctionBeans=roleFunctionBeanDao.getRoleFunctionsByRoleId(roleBean.getId());
                fillFunctionCodeByRoles(functionCodes, roleFunctionBeans);
            }
        }
        //返回用户下所有工作组,根据工作组再返回工作组下所有角色
        List<WorkGroupUserBean> workGroupUserBeans=workGroupBeanService.getWorkGroupUserBeanByUserId(userId);
        if(workGroupUserBeans!=null&&!workGroupUserBeans.isEmpty()){
            for(WorkGroupUserBean workGroupUserBean:workGroupUserBeans){
                List<RoleBean> _roleBeans = roleBeanService.getRolesByWorkGroupId(workGroupUserBean.getGroupId());
                if(_roleBeans!=null&&!_roleBeans.isEmpty()){
                    for(RoleBean roleBean:_roleBeans){
                        List<RoleFunctionBean> roleFunctionBeans=roleFunctionBeanDao.getRoleFunctionsByRoleId(roleBean.getId());
                        fillFunctionCodeByRoles(functionCodes, roleFunctionBeans);
                    }
                }
            }
        }
        return functionCodes;
    }

    private void fillApplicationCodeByRoles(List<String> applicationCodes, List<RoleApplicationBean> roleApplicationBeans){
        if(roleApplicationBeans!=null&&!roleApplicationBeans.isEmpty()){
            for(RoleApplicationBean roleApplicationBean:roleApplicationBeans){
                ApplicationBean applicationBean = applicationBeanDao.get(roleApplicationBean.getApplicationId());
                if(applicationBean!=null)
                    applicationCodes.add(applicationBean.getCode());
            }
        }
    }

    private void fillFunctionCodeByRoles(List<String> functionCodes, List<RoleFunctionBean> roleFunctionBeans){
        if(roleFunctionBeans!=null&&!roleFunctionBeans.isEmpty()){
            for(RoleFunctionBean roleFunctionBean:roleFunctionBeans){
                FunctionBean functionBean = functionBeanDao.get(roleFunctionBean.getFunctionId());
                if(functionBean!=null)
                    functionCodes.add(functionBean.getPermission());
            }
        }
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