package com.kalix.admin.duty.biz;

import com.kalix.admin.core.entities.UserBean;
import com.kalix.admin.duty.api.biz.IDutyBeanService;
import com.kalix.admin.duty.api.dao.IDutyBeanDao;
import com.kalix.admin.duty.api.dao.IDutyUserBeanDao;
import com.kalix.admin.duty.entities.DutyBean;
import com.kalix.admin.duty.entities.DutyUserBean;
import com.kalix.framework.core.api.biz.JsonStatus;
import com.kalix.framework.core.api.persistence.JsonData;
import com.kalix.framework.core.api.persistence.PersistentEntity;
import com.kalix.framework.core.impl.biz.ShiroGenericBizServiceImpl;
import com.kalix.framework.core.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @类描述： 
 * @创建人：  
 * @创建时间： 
 * @修改人：
 * @修改时间：
 * @修改备注：
 */
public class DutyBeanServiceImpl extends ShiroGenericBizServiceImpl<IDutyBeanDao, DutyBean> implements IDutyBeanService {
    private JsonStatus jsonStatus = new JsonStatus();
    private IDutyUserBeanDao dutyUserBeanDao;
    public void setDutyUserBeanDao(IDutyUserBeanDao dutyUserBeanDao) {
        this.dutyUserBeanDao = dutyUserBeanDao;
    }
    public DutyBeanServiceImpl() {
        super.init(DutyBean.class.getName());
    }

    @Override
    public List getDutiesByDepId(long depId) {
        return this.dao.find("select db from DutyBean db where db.depid=?1", depId);
    }

    @Override
    public List getUsersByDutyId(long depId, long dutyId) {
        List<String> userIds = new ArrayList<String>();
        List<DutyUserBean> dutyUserBeans = dutyUserBeanDao.find("select ob from DutyUserBean ob where ob.depId = ?1 and ob.dutyId = ?2", depId, dutyId);
        if (dutyUserBeans != null && !dutyUserBeans.isEmpty()) {
            for (DutyUserBean dutyUserBean : dutyUserBeans) {
                if (dutyUserBean != null && dutyUserBean.getUserId() != 0) {
                    userIds.add(String.valueOf(dutyUserBean.getUserId()));
                }
            }
        }
        return userIds;
    }

    @Override
    public JsonData getUserAll(long depId) {
        JsonData jsonData = new JsonData();
        List<UserBean> users = dutyUserBeanDao.findByNativeSql("select a.* from sys_user as  a, sys_department_user as  b where a.id = b.userid and depid=" + depId, UserBean.class, null);
        List<PersistentEntity> persistentEntities = new ArrayList<PersistentEntity>();
        if (users != null && users.size() > 0) {
            for (UserBean user : users) {
                if (user != null) {
                    persistentEntities.add(user);
                }
            }
        }
        jsonData.setData(persistentEntities);
        jsonData.setTotalCount((long) users.size());
        return jsonData;
    }

    @Override
    public JsonData getUserAllAndDutyUsers(long depId, long dutyId) {
        JsonData jsonData = new JsonData();
        List<UserBean> users = dutyUserBeanDao.findByNativeSql("select a.* from sys_user as  a, sys_department_user as  b where a.id = b.userid and depid=" + depId/*+" and a.id not in (select userid from sys_duty_user)"*/, UserBean.class, null);
        List<PersistentEntity> persistentEntities = new ArrayList<PersistentEntity>();
        if (users != null && users.size() > 0) {
            for (UserBean user : users) {
                if (user != null) {
                    persistentEntities.add(user);
                }
            }
        }
        List<UserBean> dutyUserBeans = dutyUserBeanDao.findByNativeSql("select a.* from sys_user a where a.id in (select du.userId from sys_duty_user du where du.depId=" + depId + " and du.dutyId=" + dutyId + ")", UserBean.class, null);
        if (dutyUserBeans != null && dutyUserBeans.size() > 0) {
            for (UserBean dutyUserBean : dutyUserBeans) {
                if (dutyUserBean != null) {
                    persistentEntities.add(dutyUserBean);
                }
            }
        }
        jsonData.setData(persistentEntities);
        jsonData.setTotalCount((long) persistentEntities.size());
        return jsonData;
    }

    @Override
    public JsonStatus saveDutyUsers(long depId, long dutyId, String userId) {
        JsonStatus jsonStatus = new JsonStatus();
        try {
            dutyUserBeanDao.deleteByDutyId(depId, dutyId);
            String userName = getShiroService().getCurrentUserName();
            if (StringUtils.isNotEmpty(userId)) {
                String[] userIds = userId.split(",");
                for (String _userId : userIds) {
                    if (StringUtils.isNotEmpty(_userId.trim())) {
                        DutyUserBean dutyUserBean = new DutyUserBean();
                        dutyUserBean.setCreateBy(userName);
                        dutyUserBean.setUpdateBy(userName);
                        dutyUserBean.setDepId(depId);
                        dutyUserBean.setDutyId(dutyId);
                        dutyUserBean.setUserId(Long.parseLong(_userId));
                        dutyUserBeanDao.save(dutyUserBean);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            jsonStatus.setFailure(true);
            jsonStatus.setMsg("保存失败!");
            return jsonStatus;
        }
        jsonStatus.setSuccess(true);
        jsonStatus.setMsg("保存成功!");
        return jsonStatus;
    }

    @Override
    public JsonStatus deleteDuty(long depId, long dutyId) {
        JsonStatus jsonStatus = new JsonStatus();
        try {
            dutyUserBeanDao.deleteByDutyId(depId, dutyId);
            deleteEntity(dutyId);
        } catch (Exception e) {
            e.printStackTrace();
            jsonStatus.setFailure(true);
            jsonStatus.setMsg("删除失败!");
            return jsonStatus;
        }
        jsonStatus.setSuccess(true);
        jsonStatus.setMsg("删除成功!");
        return jsonStatus;
    }
}
