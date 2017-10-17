package com.kalix.admin.duty.biz;

import com.kalix.admin.core.api.biz.IAdminDictBeanService;
import com.kalix.admin.core.entities.AdminDictBean;
import com.kalix.admin.duty.api.biz.IDataAuthBeanService;
import com.kalix.admin.duty.api.dao.IDataAuthBeanDao;
import com.kalix.admin.duty.api.dao.IDataAuthUserBeanDao;
import com.kalix.admin.duty.entities.DataAuthBean;
import com.kalix.admin.duty.entities.DataAuthUserBean;
import com.kalix.framework.core.api.persistence.JsonStatus;
import com.kalix.framework.core.impl.biz.ShiroGenericBizServiceImpl;
import com.kalix.framework.core.util.Assert;
import org.apache.commons.lang.StringUtils;

import javax.transaction.Transactional;
import java.util.Date;
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
        JsonStatus jsonStatus = new JsonStatus();

        if (ids == null || ids.size() != 2) {
            jsonStatus.setFailure(true);
            jsonStatus.setMsg("保存失败!");
            return jsonStatus;
        } else {
            try {
                long dataAuthId = Long.valueOf(ids.get(0).toString());
                String userId = ids.get(1).toString();

                dataAuthUserBeanDao.deleteByDataAuthId(dataAuthId);

                String userName = getShiroService().getCurrentUserLoginName();
                if (StringUtils.isNotEmpty(userId)) {
                    String[] userIds = userId.split(",");
                    for (String _userId : userIds) {
                        if (StringUtils.isNotEmpty(_userId.trim())) {
                            DataAuthUserBean dataAuthUserBean = new DataAuthUserBean();
                            dataAuthUserBean.setCreateBy(userName);
                            dataAuthUserBean.setCreationDate(new Date());
                            dataAuthUserBean.setUpdateBy(userName);
                            dataAuthUserBean.setUpdateDate(new Date());
                            dataAuthUserBean.setDataAuthId(dataAuthId);
                            dataAuthUserBean.setUserId(Long.parseLong(_userId));
                            dataAuthUserBeanDao.save(dataAuthUserBean);
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                jsonStatus.setFailure(true);
                jsonStatus.setMsg("保存失败!");
                return jsonStatus;
            }
        }

        jsonStatus.setSuccess(true);
        jsonStatus.setMsg("保存成功!");

        return jsonStatus;
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
        List<DataAuthBean> dataAuthBeans = this.dao.find("select d.id, d.type from DataAuthBean d, DataAuthUserBean u " +
                "where d.id = u.dataAuthId and u.userId = ?1 and d.appId = ?2", userId, appId);
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
