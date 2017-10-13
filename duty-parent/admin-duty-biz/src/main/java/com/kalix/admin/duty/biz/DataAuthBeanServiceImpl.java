package com.kalix.admin.duty.biz;

import com.kalix.admin.core.api.biz.IAdminDictBeanService;
import com.kalix.admin.core.entities.AdminDictBean;
import com.kalix.admin.duty.api.biz.IDataAuthBeanService;
import com.kalix.admin.duty.api.dao.IDataAuthBeanDao;
import com.kalix.admin.duty.api.dao.IDataAuthUserBeanDao;
import com.kalix.admin.duty.entities.DataAuthBean;
import com.kalix.framework.core.api.persistence.JsonStatus;
import com.kalix.framework.core.impl.biz.ShiroGenericBizServiceImpl;
import com.kalix.framework.core.util.Assert;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @类描述：
 * @创建人：
 * @创建时间：
 * @修改人：
 * @修改时间：
 * @修改备注：
 */
public class DataAuthBeanServiceImpl extends ShiroGenericBizServiceImpl<IDataAuthBeanDao, DataAuthBean> implements IDataAuthBeanService {
    private static final String FUNCTION_NAME = "数据权限";
    private IDataAuthUserBeanDao dataAuthUserBeanDao;
    private IAdminDictBeanService adminDictBeanService;

    @Override
    public boolean isSave(DataAuthBean entity, JsonStatus status) {
        Assert.notNull(entity, "实体不能为空.");
        DataAuthBean dataAuthBean = (DataAuthBean) entity;
        List<DataAuthBean> beans = dao.find("select ob from DataAuthBean ob where ob.appId = ?1 and ob.menuId = ?2",
                dataAuthBean.getAppId(), dataAuthBean.getMenuId());
        if (beans != null && beans.size() > 0) {
            String typeLabel = "";
            AdminDictBean adminDictBean = (AdminDictBean) adminDictBeanService.getByTypeAndValue("数据权限", entity.getType());
            if (adminDictBean != null && adminDictBean.getLabel() != null) {
                typeLabel = adminDictBean.getLabel();
            }
            status.setSuccess(false);
            String info = "新增失败,应用[" + entity.getAppName() + "]下菜单[" + entity.getMenuName() + "]下" +
                    FUNCTION_NAME + "[" + typeLabel + "]已经存在!";
            status.setMsg(info);
            return false;
        }
        return true;
    }

    @Override
    public boolean isUpdate(DataAuthBean entity, JsonStatus status) {
        Assert.notNull(entity, "实体不能为空.");
        DataAuthBean dataAuthBean = (DataAuthBean) entity;
        List<DataAuthBean> beans = dao.find("select ob from DataAuthBean ob where ob.id <>  ob.appId = ?1 and ob.menuId = ?2",
                dataAuthBean.getId(), dataAuthBean.getAppId(), dataAuthBean.getMenuId());
        if (beans != null && beans.size() > 0) {
            String typeLabel = "";
            AdminDictBean adminDictBean = (AdminDictBean) adminDictBeanService.getByTypeAndValue("数据权限", entity.getType());
            if (adminDictBean != null && adminDictBean.getLabel() != null) {
                typeLabel = adminDictBean.getLabel();
            }
            status.setSuccess(false);
            String info = "更新失败,应用[" + entity.getAppName() + "]下菜单[" + entity.getMenuName() + "]下" +
                    FUNCTION_NAME + "[" + typeLabel + "]已经存在！";
            status.setMsg(info);
            return false;
        }
        return true;
    }

    @Override
    public boolean isDelete(Long entityId, JsonStatus status) {
        if (dao.get(entityId) == null) {
            status.setFailure(true);
            status.setMsg(FUNCTION_NAME + "已经被删除！");
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public JsonStatus saveDataAuthUsers(List ids) {
        /*String roleId = null;
        String authorizationIds = null;

        if (ids != null && ids.size() == 2) {
            roleId = ids.get(0).toString();
            authorizationIds = ids.get(1).toString();
        }

        Assert.notNull(roleId, "角色编号不能为空.");
        Assert.notNull(authorizationIds, "授权编号不能为空.");
        JsonStatus jsonStatus = new JsonStatus();

        try {
            //清除关联关系
            roleApplicationBeanDao.deleteByRoleId(Long.parseLong(roleId));
            roleFunctionBeanDao.deleteByRoleId(Long.parseLong(roleId));
            String userName = getShiroService().getCurrentUserLoginName();
//            if (authorizationIds.indexOf(",") != -1) {
            String[] _authorizationIds = authorizationIds.split(",");

            for (String _authorizationId : _authorizationIds) {
                if (_authorizationId.indexOf("root") != -1)
                    continue;
                if (_authorizationId.startsWith("app:")) {
                    RoleApplicationBean roleApplicationBean = new RoleApplicationBean();
                    roleApplicationBean.setCreateBy(userName);
                    roleApplicationBean.setUpdateBy(userName);
                    roleApplicationBean.setRoleId(Long.parseLong(roleId));
                    String applicationId = _authorizationId.substring("app:".length(), _authorizationId.length());
                    roleApplicationBean.setApplicationId(Long.parseLong(applicationId));
                    roleApplicationBeanDao.save(roleApplicationBean);
                } else if (_authorizationId.startsWith("fun:")) {
                    RoleFunctionBean roleFunctionBean = new RoleFunctionBean();
                    roleFunctionBean.setCreateBy(userName);
                    roleFunctionBean.setUpdateBy(userName);
                    roleFunctionBean.setRoleId(Long.parseLong(roleId));
                    String functionId = _authorizationId.substring("fun:".length(), _authorizationId.length());
                    roleFunctionBean.setFunctionId(Long.parseLong(functionId));
                    roleFunctionBeanDao.save(roleFunctionBean);
                }
            }
//            } else {
//                if (authorizationIds.startsWith("app:") && authorizationIds.indexOf("root") == -1) {
//                    RoleApplicationBean roleApplicationBean = new RoleApplicationBean();
//                    roleApplicationBean.setCreateBy(userName);
//                    roleApplicationBean.setUpdateBy(userName);
//                    roleApplicationBean.setRoleId(Long.parseLong(roleId));
//                    String applicationId = authorizationIds.substring("app:".length(), authorizationIds.length());
//                    roleApplicationBean.setApplicationId(Long.parseLong(applicationId));
//                    roleApplicationBeanDao.save(roleApplicationBean);
//                } else if (authorizationIds.startsWith("fun:") && authorizationIds.indexOf("root") == -1) {
//                    RoleFunctionBean roleFunctionBean = new RoleFunctionBean();
//                    roleFunctionBean.setCreateBy(userName);
//                    roleFunctionBean.setUpdateBy(userName);
//                    roleFunctionBean.setRoleId(Long.parseLong(roleId));
//                    String functionId = authorizationIds.substring("fun:".length(), authorizationIds.length());
//                    roleFunctionBean.setFunctionId(Long.parseLong(functionId));
//                    roleFunctionBeanDao.save(roleFunctionBean);
//                }
//            }
        } catch (Exception e) {
            e.printStackTrace();
            jsonStatus.setFailure(true);
            jsonStatus.setMsg("保存失败!");
            return jsonStatus;
        }
        jsonStatus.setSuccess(true);
        jsonStatus.setMsg("保存成功!");
        return jsonStatus;*/
        return null;
    }

    @Override
    public DataAuthBean getDataAuthBean(Long userId, String appId, String menuId) {
        DataAuthBean result = null;
        /*List<DataAuthUserBean> dataAuthUserBeans = dataAuthUserBeanDao.getEntitiesByUserId(userId);
        List<DataAuthBean> dataAuthBeans = this.dao.find("select d.id, d.name from DataAuthBean d where d.appId = ?", appId);
        for (DataAuthUserBean dataAuthUserBean: dataAuthUserBeans) {
            for (DataAuthBean dataAuthBean : dataAuthBeans) {
                if (dataAuthBean.getId() == dataAuthUserBean.getDataAuthId()) {
                    result = dataAuthBean;
                    break;
                }
            }
        }*/
        List<DataAuthBean> dataAuthBeans = this.dao.find("select d.id, d.name from DataAuthBean d, DataAuthUserBean u " +
                "where d.id = u.dataAuthId and u.userid = ?1 and d.appid = ?2", userId, appId);
        if (dataAuthBeans != null && dataAuthBeans.size() > 0) {
            result = dataAuthBeans.get(0);
        }
        return result;
    }

    public void setDataAuthUserBeanDao(IDataAuthUserBeanDao dataAuthUserBeanDao) {
        this.dataAuthUserBeanDao = dataAuthUserBeanDao;
    }

    public void setAdminDictBeanService(IAdminDictBeanService adminDictBeanService) {
        this.adminDictBeanService = adminDictBeanService;
    }
}
