package com.kalix.admin.core.biz;

import com.google.gson.reflect.TypeToken;
import com.kalix.admin.core.api.biz.IUserBeanService;
import com.kalix.admin.core.api.dao.*;
import com.kalix.admin.core.dto.model.OrganizationDTO;
import com.kalix.admin.core.dto.model.UserDTO;
import com.kalix.admin.core.entities.*;
import com.kalix.admin.duty.api.dao.IDutyBeanDao;
import com.kalix.admin.duty.api.dao.IDutyUserBeanDao;
import com.kalix.admin.duty.entities.DutyBean;
import com.kalix.admin.duty.entities.DutyUserBean;
import com.kalix.framework.core.api.persistence.GenericJsonData;
import com.kalix.framework.core.api.persistence.JsonData;
import com.kalix.framework.core.api.persistence.JsonStatus;
import com.kalix.framework.core.api.web.model.BaseDTO;
import com.kalix.framework.core.api.web.model.QueryDTO;
import com.kalix.framework.core.impl.biz.ShiroGenericBizServiceImpl;
import com.kalix.framework.core.util.Assert;
import com.kalix.framework.core.util.HttpClientUtil;
import com.kalix.framework.core.util.MD5Util;
import com.kalix.framework.core.util.SerializeUtil;
import org.apache.commons.lang.StringUtils;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by dell on 14-1-17.
 */
public class UserBeanServiceImpl extends ShiroGenericBizServiceImpl<IUserBeanDao, UserBean> implements IUserBeanService {
    private static final String FUNCTION_NAME = "用户";
    private IRoleBeanDao roleBeanDao;
    private IRoleUserBeanDao roleUserBeanDao;
    private IWorkGroupBeanDao workGroupBeanDao;
    private IWorkGroupUserBeanDao workGroupUserBeanDao;
    private IOrganizationBeanDao organizationBeanDao;
    private IOrganizationUserBeanDao organizationUserBeanDao;
    private IDutyBeanDao dutyBeanDao;
    private IDutyUserBeanDao dutyUserBeanDao;

    public UserBeanServiceImpl() {
        super.init(UserBean.class.getName());
    }

    public void setRoleBeanDao(IRoleBeanDao roleBeanDao) {
        this.roleBeanDao = roleBeanDao;
    }

    public void setRoleUserBeanDao(IRoleUserBeanDao roleUserBeanDao) {
        this.roleUserBeanDao = roleUserBeanDao;
    }

    public void setWorkGroupBeanDao(IWorkGroupBeanDao workGroupBeanDao) {
        this.workGroupBeanDao = workGroupBeanDao;
    }

    public void setWorkGroupUserBeanDao(IWorkGroupUserBeanDao workGroupUserBeanDao) {
        this.workGroupUserBeanDao = workGroupUserBeanDao;
    }

    public void setOrganizationBeanDao(IOrganizationBeanDao organizationBeanDao) {
        this.organizationBeanDao = organizationBeanDao;
    }

    public void setOrganizationUserBeanDao(IOrganizationUserBeanDao organizationUserBeanDao) {
        this.organizationUserBeanDao = organizationUserBeanDao;
    }

    public void setDutyBeanDao(IDutyBeanDao dutyBeanDao) {
        this.dutyBeanDao = dutyBeanDao;
    }

    public void setDutyUserBeanDao(IDutyUserBeanDao dutyUserBeanDao) {
        this.dutyUserBeanDao = dutyUserBeanDao;
    }

    @Override
    public void afterDeleteEntity(Long id, JsonStatus status) {
        roleUserBeanDao.update("delete from RoleUserBean ru where ru.userId=?1", id);
        organizationUserBeanDao.update("delete from OrganizationUserBean du where du.userId=?1", id);
        workGroupUserBeanDao.update("delete from WorkGroupUserBean wu where wu.userId=?1", id);
    }

    @Override
    public void beforeUpdateEntity(UserBean entity, JsonStatus status) {
        UserBean userBean = dao.get(entity.getId());

        if (StringUtils.isEmpty(entity.getPassword())) {
            // 密码为空，代表不修改原密码，需要从数据库中读取原密码
            entity.setPassword(userBean.getPassword());
        } else {
            entity.setPassword(MD5Util.encode(entity.getPassword()));
        }
        super.beforeUpdateEntity(entity, status);
    }

    @Override
    public void beforeSaveEntity(UserBean entity, JsonStatus status) {
        UserBean userEntity = (UserBean) entity;
        //密码加密
        if (StringUtils.isNotEmpty(userEntity.getPassword())) {
            userEntity.setPassword(MD5Util.encode(userEntity.getPassword()));
        }

        super.beforeSaveEntity(entity, status);
    }

    @Override
    public boolean isUpdate(UserBean entity, JsonStatus status) {
        Assert.notNull(entity, "实体不能为空.");
        UserBean bean = (UserBean) entity;
        List<UserBean> userBeans = dao.find("select ob from UserBean ob where ob.loginName = ?1", bean.getLoginName());
        if (userBeans != null && userBeans.size() > 0) {
            UserBean _userBean = userBeans.get(0);
            if (entity.getId() != _userBean.getId()) {
                status.setFailure(true);
                status.setMsg(FUNCTION_NAME + "已经存在！");
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean isSave(UserBean entity, JsonStatus status) {
        UserBean bean = (UserBean) entity;
        List<UserBean> userBeans = dao.find("select ob from UserBean ob where ob.loginName = ?1", bean.getLoginName());
        if (userBeans != null && userBeans.size() > 0) {
            status.setFailure(true);
            status.setMsg(FUNCTION_NAME + "已经存在！");
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

    /**
     * 生成roleList列表，以逗号分隔
     *
     * @param userBean 用户
     * @return
     */
    @Override
    public String getRoleList(UserBean userBean) {
        List<RoleBean> roleBeanList = roleBeanDao.find("select r from RoleBean r join r.userList u where u=?1", userBean);
        String value = "";
        if (roleBeanList != null && roleBeanList.size() > 0) {
            for (RoleBean roleBean : roleBeanList) {
                value = value + roleBean.getName() + ",";
            }
            //截掉最后一个“，”
            if (value.length() > 1)
                value = value.substring(0, value.length() - 1);
        }
        return value;
    }

//    @Override
//    public void saveUserRole(UserBean userBean, List<String> roleSelect) {
//        List<RoleBean> roleBeanList = userBean.getRoleList();
//        //清空全部角色
//        roleBeanList.clear();
//        //重新添加角色
//        if (roleSelect != null && roleSelect.size() > 0) {
//            for (String role : roleSelect) {
//                roleBeanList.add(roleBeanDao.getRole(role));
//            }
//        }
//        dao.save(userBean);
//    }

//    @Override
//    public void saveUserRoleNew(UserBean userBean, List<String> roleSelect) {
//        List<RoleBean> roleBeanList = new ArrayList<RoleBean>();
//        if (userBean.getId() == 0L) {       //为新用户对象
//            userBean = dao.save(userBean);
//        } else {                            //取出用户的角色集合
//            roleBeanList = dao.get(userBean.getId()).getRoleList();
//        }
//        //删除全部该角色下的用户
//        if (roleSelect != null) {
//            removeRole(userBean, roleBeanList);
//            if (roleSelect.size() > 0) {
//                //添加角色到用户
//                for (String roleName : roleSelect) {
//                    RoleBean roleBean = roleBeanDao.getRole(roleName);
//                    UserBean user = dao.getUser(userBean.getId());
//                    if (!user.getRoleList().contains(roleBean)) {
//                        user.getRoleList().add(roleBean);
//                        dao.save(user);
//                    }
//                }
//            }
//        } else {
//            dao.save(userBean);
//        }
//    }

//    private void removeRole(UserBean userBean, List<RoleBean> roleBeanList) {
//        for (RoleBean roleBean : roleBeanList) {
//            userBean.getRoleList().remove(roleBean);
//            dao.save(userBean);
//        }
//    }

    @Override
    public UserBean getUserBeanByLoginName(String username) {
        return dao.findUnique("select a from UserBean a where a.loginName = ?1", username);
    }

    @Override
    public void setUserUnavailable(String relateId) {
        dao.update("update sys_user set available=0 where relateId=" + relateId);
    }

    @Override
    public JsonData getAllEntityByQuery(QueryDTO queryDTO) {
        Mapper mapper = new DozerBeanMapper();
        JsonData jsonData = super.getAllEntityByQuery(queryDTO);
        List userList = jsonData.getData();
        List<UserDTO> userDTOList = new ArrayList<>();
        for (Object user : userList) {
            UserDTO userDTO = mapper.map(user, UserDTO.class);
            //userDTO.setOrg(getUserOrg(userDTO.getId()));
            //userDTO.setDuty(getUserDuty(userDTO.getId()));
            userDTOList.add(userDTO);
        }

        // 全部组织机构
        List<OrganizationBean> orgList = organizationBeanDao.getAll();
        // 部分机构人员对应关系
        List<OrganizationUserBean> orgUserList = organizationUserBeanDao.findByUserIds(userDTOList.stream().map(BaseDTO::getId).collect(Collectors.toList()));
        // 全部职务
        List<DutyBean> dutyList = dutyBeanDao.getAll();
        // 部分职务人员对应关系
        List<DutyUserBean> dutyUserList = dutyUserBeanDao.findByUserIds(userDTOList.stream().map(BaseDTO::getId).collect(Collectors.toList()));
        // 全部角色
        List<RoleBean> roleList = roleBeanDao.getAll();
        // 全部角色人员对应关系
        List<RoleUserBean> roleUserList = roleUserBeanDao.getAll();
        // 全部工作组
        List<WorkGroupBean> workGroupList = workGroupBeanDao.getAll();
        // 全部工作组人员对应关系
        List<WorkGroupUserBean> workGroupUserList = workGroupUserBeanDao.getAll();

        userList = userDTOList.stream().peek(n -> {
            orgUserList.stream().filter(m -> n.getId() == m.getUserId())
                    .forEach(m -> orgList.stream().filter(org -> org.getId() == m.getOrgId())
                            .forEach(org -> n.setOrg(n.getOrg() == null ? org.getName() : n.getOrg() + "," + org.getName())));

            dutyUserList.stream().filter(m -> n.getId() == m.getUserId())
                    .forEach(m -> dutyList.stream().filter(duty -> duty.getId() == m.getDutyId())
                            .forEach(duty -> n.setDuty(n.getDuty() == null ? duty.getName() : n.getDuty() + "," + duty.getName())));

            roleUserList.stream().filter(m -> n.getId() == m.getUserId())
                    .forEach(m -> roleList.stream().filter(role -> role.getId() == m.getRoleId())
                            .forEach(role -> n.setRole(n.getRole() == null ? role.getName() : n.getRole() + "," + role.getName())));

            workGroupUserList.stream().filter(m -> n.getId() == m.getUserId())
                    .forEach(m -> workGroupList.stream().filter(workGroup -> workGroup.getId() == m.getGroupId())
                            .forEach(workGroup -> n.setWorkGroup(n.getWorkGroup() == null ? workGroup.getName() : n.getWorkGroup() + "," + workGroup.getName())));

            if (n.getOrg() == null || "".equals(n.getOrg())) {
                n.setOrg("暂无所属机构");
            }

            if (n.getDuty() == null || "".equals(n.getDuty())) {
                n.setDuty("暂无职务");
            }

            if (n.getRole() == null || "".equals(n.getRole())) {
                n.setRole("暂无角色");
            }

            if (n.getWorkGroup() == null || "".equals(n.getWorkGroup())) {
                n.setWorkGroup("暂无工作组");
            }
        }).collect(Collectors.toList());

        jsonData.setData(userList);

        return jsonData;
    }

    /**
     * 获得用户的组织
     */
    private String getUserOrg(long userId) {
        final String url = "http://localhost:8181/kalix/camel/rest/users/%s/orgs";
        String getStr = "";
        try {
            getStr = HttpClientUtil.shiroGet(String.format(url, userId), this.getShiroService().getSession().getId().toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        GenericJsonData<OrganizationDTO> jsonData = new GenericJsonData();
        if (getStr != null) {
            Type type = new TypeToken<GenericJsonData<OrganizationDTO>>() {
            }.getType();
            jsonData = SerializeUtil.unserializeJson(getStr, type);
        }
        List<OrganizationDTO> org = jsonData.getData();
        String rtnStr = "";
        for (OrganizationDTO obj : org) {
            rtnStr = rtnStr + "," + obj.getName();
        }
        if (!rtnStr.isEmpty())
            rtnStr = rtnStr.substring(1, rtnStr.length());
        return rtnStr;
    }

    /**
     * 获得用户的职位
     *
     * @param userId
     * @return
     */
    private String getUserDuty(long userId) {
        final String url = "http://localhost:8181/kalix/camel/rest/users/user/%s/dutys";
        String getStr = "";
        try {
            getStr = HttpClientUtil.shiroGet(String.format(url, userId), this.getShiroService().getSession().getId().toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<String> dutyList = new ArrayList<>();
        if (getStr != null) {
            Type type = new TypeToken<ArrayList<String>>() {
            }.getType();
            dutyList = SerializeUtil.unserializeJson(getStr, type);
        }
        String rtnStr = "";
        for (String obj : dutyList) {
            rtnStr = rtnStr + "," + obj;
        }
        if (!rtnStr.isEmpty())
            rtnStr = rtnStr.substring(1, rtnStr.length());
        return rtnStr;
    }


    /**
     * 检查指定用户id的密码是否正确
     *
     * @param userId
     * @param password
     * @return
     */
    @Override
    public boolean checkUserPassword(long userId, String password) {
        UserBean userBean = dao.get(userId);
        return userBean != null && userBean.getPassword() != null && password != null && userBean.getPassword().equals(MD5Util.encode(password)) ? true : false;
    }

    /**
     * 查询指定用户id所属机构（包括子机构）下所有用户
     *
     * @param userId
     * @return
     */
    @Override
    public JsonData findOrgsUserByUserId(Long userId) {
        List<UserBean> userList = new ArrayList<>();

        // 用户拥有的机构列表
        List<OrganizationBean> list = organizationBeanDao.findById(organizationUserBeanDao.findByUserId(userId).stream().map(OrganizationUserBean::getOrgId).collect(Collectors.toList()));

        // 查找最顶层机构code长度
        int minLength = 100;
        for (OrganizationBean bean : list) {
            if (bean.getCode().length() < minLength) {
                minLength = bean.getCode().length();
            }
        }

        // 拼接sql
        String sql = "";
        for (OrganizationBean bean : list) {
            if (bean.getCode().length() == minLength) {
                if (sql.isEmpty()) {
                    sql = "org.code like '" + bean.getCode() + "%'";
                } else {
                    sql += " or org.code like '" + bean.getCode() + "%'";
                }

            }
        }

        if (!sql.isEmpty()) {
            userList = dao.findByNativeSql("select id, name from sys_user where id in (select userid from sys_organization_user as orguser, sys_organization as org where orguser.orgid = org.id and (" + sql + "))", UserBean.class);
        }

        return JsonData.toJsonData(userList);
    }
}
