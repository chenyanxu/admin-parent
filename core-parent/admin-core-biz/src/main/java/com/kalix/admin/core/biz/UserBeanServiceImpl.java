package com.kalix.admin.core.biz;

import com.google.gson.reflect.TypeToken;
import com.kalix.admin.core.api.biz.IAddFieldService;
import com.kalix.admin.core.api.biz.IUserBeanService;
import com.kalix.admin.core.api.dao.*;
import com.kalix.admin.core.dto.model.OrganizationDTO;
import com.kalix.admin.core.dto.model.UserDTO;
import com.kalix.admin.core.entities.*;
import com.kalix.admin.duty.api.dao.IDutyBeanDao;
import com.kalix.admin.duty.api.dao.IDutyUserBeanDao;
import com.kalix.admin.duty.entities.DutyBean;
import com.kalix.admin.duty.entities.DutyUserBean;
import com.kalix.framework.core.api.extend.IUserDefaultRole;
import com.kalix.framework.core.api.persistence.GenericJsonData;
import com.kalix.framework.core.api.persistence.JsonData;
import com.kalix.framework.core.api.persistence.JsonStatus;
import com.kalix.framework.core.api.web.model.BaseDTO;
import com.kalix.framework.core.api.web.model.QueryDTO;
import com.kalix.framework.core.impl.biz.ShiroGenericBizServiceImpl;
import com.kalix.framework.core.util.*;
import com.kalix.framework.core.util.internal.InitActivator;
import org.apache.commons.lang.StringUtils;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import com.kalix.framework.core.util.OsgiUtil;
import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;

import javax.transaction.Transactional;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by dell on 14-1-17.
 */
public class UserBeanServiceImpl extends ShiroGenericBizServiceImpl<IUserBeanDao, UserBean> implements IUserBeanService {
    private static final String FUNCTION_NAME = "登录名";
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
    @Transactional
    public JsonStatus saveEntityWithOrg(UserBean entity, Long id) {
        JsonStatus jsonStatus = super.saveEntity(entity);
        if (jsonStatus.getSuccess()) {
            OrganizationUserBean organizationUserBean = new OrganizationUserBean();
            organizationUserBean.setCreateById(shiroService.getCurrentUserId());
            organizationUserBean.setCreateBy(shiroService.getCurrentUserRealName());
            organizationUserBean.setUpdateById(shiroService.getCurrentUserId());
            organizationUserBean.setUpdateBy(shiroService.getCurrentUserRealName());
            organizationUserBean.setOrgId(id);
            organizationUserBean.setUserId(Long.valueOf(jsonStatus.getTag()));
            organizationUserBeanDao.save(organizationUserBean);
        }

        return jsonStatus;
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

    /**
     * 查找组织机构下所有用户（分页）
     * 排序功能未加
     *
     * @param orgId
     * @param page
     * @param limit
     * @param sort
     * @return
     */
    @Override
    public JsonData findUserByOrgId(Long orgId, int page, int limit, String sort) {
        return getUserAttachedInfo(dao.findByNativeSql("select u.* from " + dao.getTableName() + " u, " + organizationUserBeanDao.getTableName() + " o  where u.id = o.userId and o.orgId = " + orgId, page, limit, UserBean.class));
    }

    /**
     * 查找全部用户
     *
     * @param queryDTO
     * @return
     */
    @Override
    public JsonData getAllEntityByQuery(QueryDTO queryDTO) {
        return getUserAttachedInfo(super.getAllEntityByQuery(queryDTO));
    }

    /**
     * 获得用户的组织
     */
    private String getUserOrg(long userId) {
        final String url = "/users/%s/orgs";
        String getStr = "";
        try {
            String sessionId = this.getShiroService().getSession().getId().toString();
            String access_token = this.getShiroService().getSession().getAttribute("access_token").toString();
            getStr = HttpClientUtil.shiroGet(String.format(url, userId), sessionId, access_token);
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
        final String url = "/users/user/%s/dutys";
        String getStr = "";
        try {
            String sessionId = this.getShiroService().getSession().getId().toString();
            String access_token = this.getShiroService().getSession().getAttribute("access_token").toString();
            getStr = HttpClientUtil.shiroGet(String.format(url, userId), sessionId, access_token);
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

    @Override
    public List<Long> findOrgsUserByUserId(Long userId, Boolean includeChildOrg) {
        //JsonData jsonData = null;
        List<Long> rtnList = new ArrayList<Long>();
        List<UserBean> userList = new ArrayList<UserBean>();
        if (includeChildOrg) {
            JsonData jsonData = findOrgsUserByUserId(userId);
            userList = jsonData.getData();
        } else {
            // 用户拥有的机构列表
            List<OrganizationBean> list = organizationBeanDao.findById(organizationUserBeanDao.findByUserId(userId).stream().map(OrganizationUserBean::getOrgId).collect(Collectors.toList()));

            // 拼接sql
            String sql = "";
            for (OrganizationBean bean : list) {
                if (sql.isEmpty()) {
                    sql = "org.code ='" + bean.getCode() + "'";
                } else {
                    sql += " or org.code ='" + bean.getCode() + "'";
                }
            }
            if (!sql.isEmpty()) {
                userList = dao.findByNativeSql("select id, name from sys_user where id in (select userid from sys_organization_user as orguser, sys_organization as org where orguser.orgid = org.id and (" + sql + "))", UserBean.class);
            }
        }
        //jsonData = JsonData.toJsonData(userList);
        for (UserBean obj : userList) {
            rtnList.add(obj.getId());
        }
        return rtnList;
    }

    /**
     * 为查询出的用户信息附加所属组织机构、职务、角色、工作组信息
     *
     * @param jsonData
     * @return
     */
    private JsonData getUserAttachedInfo(JsonData jsonData) {
        Mapper mapper = new DozerBeanMapper();

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

    @Override
    public Boolean existUserBeanByLoginName(String loginname) {
        List<UserBean> userBeans = dao.find("select ob from UserBean ob where ob.loginName = ?1", loginname);
        if (userBeans != null && userBeans.size() > 0) {
            return true;
        }
        return false;
    }

    @Override
    public List<UserBean> getUsersByIds(List<String> userIds) {
        String userIdsStr = String.join(",", userIds);
        String sql = "select * from sys_user where available=1 and id in (" + userIdsStr + ")";
        return dao.findByNativeSql(sql, UserBean.class, null);
    }

    /**
     * 在用户新建之后，设置用户的默认角色
     * 根据config.admin.dict.cfg文件中，admin_default_roles的属性
     *
     * @param entity
     * @param status
     */
    @Override
    public void afterSaveEntity(UserBean entity, JsonStatus status) {
        IUserDefaultRole defaultRole = null;

//        String roles = (String) ConfigUtil.getConfigProp("admin_default_roles", "config.admin.dict");
        Map<String, String> map = new HashMap();
        map.put(IUserDefaultRole.USER_TYPE, String.valueOf(entity.getUserType()));
        try {
            List  services =OsgiUtil.getServices(IAddFieldService.class,null);
           if(services!=null&&services.size()>0)
           {
               for(int i =0 ;i<services.size();i++)
               {
                   IAddFieldService service=(IAddFieldService)services.get(i);
                   service.setField(entity);
               }
           }


            defaultRole = JNDIHelper.getJNDIServiceForName(IUserDefaultRole.class.getName(), map);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidSyntaxException e) {
            e.printStackTrace();
        }
        if (defaultRole != null) {
            RoleBean roleBean = roleBeanDao.getRole(defaultRole.getRoleName());
            RoleUserBean bean = new RoleUserBean();
            bean.setRoleId(roleBean.getId());
            bean.setUserId(entity.getId());
            roleUserBeanDao.save(bean);
        }

        super.afterSaveEntity(entity, status);
    }
}
