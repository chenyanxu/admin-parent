package com.kalix.admin.core.biz;

import com.kalix.admin.core.api.biz.IWorkGroupBeanService;
import com.kalix.admin.core.api.dao.IWorkGroupBeanDao;
import com.kalix.admin.core.api.dao.IWorkGroupRoleBeanDao;
import com.kalix.admin.core.api.dao.IWorkGroupUserBeanDao;
import com.kalix.admin.core.entities.WorkGroupBean;
import com.kalix.admin.core.entities.WorkGroupRoleBean;
import com.kalix.admin.core.entities.WorkGroupUserBean;
import com.kalix.framework.core.api.persistence.JsonStatus;
import com.kalix.framework.core.api.persistence.JsonData;
import com.kalix.framework.core.impl.biz.ShiroGenericBizServiceImpl;
import com.kalix.framework.core.util.Assert;
import com.kalix.framework.core.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 工作组管理服务实现
 *
 * @author majian <br/>
 *         date:2015-7-27
 * @version 1.0.0
 */
public class WorkGroupBeanServiceImpl extends ShiroGenericBizServiceImpl<IWorkGroupBeanDao, WorkGroupBean> implements IWorkGroupBeanService {
    private static final String FUNCTION_NAME = "工作组";
    private IWorkGroupUserBeanDao workGroupUserBeanDao;
    private IWorkGroupRoleBeanDao workGroupRoleBeanDao;

    public WorkGroupBeanServiceImpl() {
        super.init(WorkGroupBean.class.getName());
    }

    public void setWorkGroupRoleBeanDao(IWorkGroupRoleBeanDao workGroupRoleBeanDao) {
        this.workGroupRoleBeanDao = workGroupRoleBeanDao;
    }

    public void setWorkGroupUserBeanDao(IWorkGroupUserBeanDao workGroupUserBeanDao) {
        this.workGroupUserBeanDao = workGroupUserBeanDao;
    }

    @Override
    public List<WorkGroupBean> query(WorkGroupBean WorkGroupBean) {
        return dao.find("select a from WorkGroupBean a where a.name like ?1", "%" + WorkGroupBean.getName() + "%");
    }

    @Override
    public void afterDeleteEntity(Long id, JsonStatus status) {
        workGroupUserBeanDao.deleteByWorkGroupId(id);
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
    public boolean isUpdate(WorkGroupBean entity, JsonStatus status) {
        Assert.notNull(entity, "实体不能为空.");
        WorkGroupBean bean = (WorkGroupBean) entity;
        List<WorkGroupBean> beans = dao.find("select ob from WorkGroupBean ob where ob.name = ?1", bean.getName());
        if (beans != null && beans.size() > 0) {
            WorkGroupBean _workGroup = beans.get(0);
            if (_workGroup.getId() != entity.getId()) {
                status.setFailure(true);
                status.setMsg(FUNCTION_NAME + "已经存在！");
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean isSave(WorkGroupBean entity, JsonStatus status) {
        Assert.notNull(entity, "实体不能为空.");
        WorkGroupBean bean = (WorkGroupBean) entity;
        List<WorkGroupBean> beans = dao.find("select ob from WorkGroupBean ob where ob.name = ?1", bean.getName());
        if (beans != null && beans.size() > 0) {
            status.setSuccess(false);
            status.setMsg(FUNCTION_NAME + "已经存在！");
            return false;
        }
        return true;
    }

    @Override
    public JsonData getAllWorkGroup(int page, int limit) {
        return dao.getAll(page, limit);
    }

    @Override
    public List getUserIdsByWorkGroupId(long id) {
        List userIds = new ArrayList<String>();
        List<WorkGroupUserBean> workGroupUserBeans = workGroupUserBeanDao.find("select ob from WorkGroupUserBean ob where ob.groupId = ?1", id);
        if (workGroupUserBeans != null && !workGroupUserBeans.isEmpty()) {
            for (WorkGroupUserBean workGroupUserBean : workGroupUserBeans) {
                if (workGroupUserBean != null && workGroupUserBean.getUserId() != 0) {
                    userIds.add(workGroupUserBean.getUserId());
                }
            }
        }
        return userIds;
    }

    @Override
    public List getRoleIdsByWorkGroupId(long id) {
        List roleIds = new ArrayList<>();
        List<WorkGroupRoleBean> workGroupRoleBeans = workGroupRoleBeanDao.find("select ob from WorkGroupRoleBean ob where ob.groupId = ?1", id);
        if (workGroupRoleBeans != null && !workGroupRoleBeans.isEmpty()) {
            for (WorkGroupRoleBean workGroupRoleBean : workGroupRoleBeans) {
                if (workGroupRoleBean != null && workGroupRoleBean.getRoleId() != 0) {
                    roleIds.add(workGroupRoleBean.getRoleId());
                }
            }
        }
        return roleIds;
    }

    @Override
    public JsonStatus saveWorkGroupUsers(List ids) {
        JsonStatus jsonStatus = new JsonStatus();

        if (ids == null || ids.size() != 2) {
            jsonStatus.setFailure(true);
            jsonStatus.setMsg("保存失败!");
            return jsonStatus;
        } else {
            try {
                long workGroupId = Long.valueOf(ids.get(0).toString());
                String userId = ids.get(1).toString();

                workGroupUserBeanDao.deleteByWorkGroupId(workGroupId);
                String userName = getShiroService().getCurrentUserLoginName();
                if (StringUtils.isNotEmpty(userId)) {
                    String[] userIds = userId.split(",");
                    for (String _userId : userIds) {
                        if (StringUtils.isNotEmpty(_userId.trim())) {
                            WorkGroupUserBean workGroupUserBean = new WorkGroupUserBean();
                            workGroupUserBean.setCreateBy(userName);
                            workGroupUserBean.setUpdateBy(userName);
                            workGroupUserBean.setGroupId(workGroupId);
                            workGroupUserBean.setUserId(Long.parseLong(_userId));
                            workGroupUserBeanDao.save(workGroupUserBean);
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
    public JsonStatus saveWorkGroupRoles(List ids) {
        JsonStatus jsonStatus = new JsonStatus();

        if (ids == null || ids.size() != 2) {
            jsonStatus.setFailure(true);
            jsonStatus.setMsg("保存失败!");
            return jsonStatus;
        } else {
            try {
                long workGroupId = Long.valueOf(ids.get(0).toString());
                String roleId = ids.get(1).toString();

                workGroupRoleBeanDao.deleteByWorkGroupId(workGroupId);
                String userName = getShiroService().getCurrentUserLoginName();
                if (StringUtils.isNotEmpty(roleId)) {
                    String[] roleIds = roleId.split(",");
                    for (String _roleId : roleIds) {
                        if (StringUtils.isNotEmpty(_roleId.trim())) {
                            WorkGroupRoleBean workGroupRoleBean = new WorkGroupRoleBean();
                            workGroupRoleBean.setCreateBy(userName);
                            workGroupRoleBean.setUpdateBy(userName);
                            workGroupRoleBean.setGroupId(workGroupId);
                            workGroupRoleBean.setRoleId(Long.parseLong(_roleId));
                            workGroupRoleBeanDao.save(workGroupRoleBean);
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
    }

        @Override
        public List<WorkGroupUserBean> getWorkGroupUserBeanByUserId ( long userId){
            return workGroupUserBeanDao.find("select wgu from WorkGroupUserBean wgu where wgu.userId=?1", userId);
        }
    }
