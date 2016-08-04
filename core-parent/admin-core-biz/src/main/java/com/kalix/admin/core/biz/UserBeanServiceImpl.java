package com.kalix.admin.core.biz;

import com.kalix.admin.core.api.biz.IUserBeanService;
import com.kalix.admin.core.api.dao.*;
import com.kalix.admin.core.entities.RoleBean;
import com.kalix.admin.core.entities.UserBean;
import com.kalix.framework.core.api.persistence.JsonStatus;
import com.kalix.framework.core.impl.biz.ShiroGenericBizServiceImpl;
import com.kalix.framework.core.util.Assert;
import com.kalix.framework.core.util.MD5Util;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dell on 14-1-17.
 */
public class UserBeanServiceImpl extends ShiroGenericBizServiceImpl<IUserBeanDao, UserBean> implements IUserBeanService {
    private static final String FUNCTION_NAME = "用户";
    private IRoleBeanDao roleBeanDao;
    private IRoleUserBeanDao roleUserBeanDao;
    private IWorkGroupUserBeanDao workGroupUserBeanDao;
    private IOrganizationUserBeanDao departmentUserBeanDao;

    public UserBeanServiceImpl() {
        super.init(UserBean.class.getName());
    }

    public void setWorkGroupUserBeanDao(IWorkGroupUserBeanDao workGroupUserBeanDao) {
        this.workGroupUserBeanDao = workGroupUserBeanDao;
    }

    public void setDepartmentUserBeanDao(IOrganizationUserBeanDao departmentUserBeanDao) {
        this.departmentUserBeanDao = departmentUserBeanDao;
    }

    public void setRoleUserBeanDao(IRoleUserBeanDao roleUserBeanDao) {
        this.roleUserBeanDao = roleUserBeanDao;
    }

    @Override
    public void afterDeleteEntity(Long id, JsonStatus status) {
        roleUserBeanDao.update("delete from RoleUserBean ru where ru.userId=?1",id);
        departmentUserBeanDao.update("delete from OrganizationUserBean du where du.userId=?1", id);
        workGroupUserBeanDao.update("delete from WorkGroupUserBean wu where wu.userId=?1", id);
    }

    @Override
    public void beforeUpdateEntity(UserBean entity, JsonStatus status) {
        UserBean userEntity=(UserBean)entity;
        UserBean userBean = dao.get(entity.getId());
        //如果编辑时修改了密码将重新计算MD5
        if(userBean!=null&&!userBean.getPassword().equals(userEntity.getPassword())){
            userEntity.setPassword(MD5Util.encode(userEntity.getPassword()));
        }

        super.beforeUpdateEntity(entity,status);
    }

    @Override
    public void beforeSaveEntity(UserBean entity, JsonStatus status) {
        UserBean userEntity=(UserBean)entity;
        //密码加密
        if(StringUtils.isNotEmpty(userEntity.getPassword())){
            userEntity.setPassword(MD5Util.encode(userEntity.getPassword()));
        }

        super.beforeSaveEntity(entity,status);
    }

    @Override
    public boolean isUpdate(UserBean entity, JsonStatus status) {
        Assert.notNull(entity, "实体不能为空.");
        UserBean bean=(UserBean)entity;
        List<UserBean> userBeans = dao.find("select ob from UserBean ob where ob.loginName = ?1", bean.getLoginName());
        if(userBeans!=null&&userBeans.size()>0){
            UserBean _userBean=userBeans.get(0);
            if(entity.getId()!=_userBean.getId()) {
                status.setFailure(true);
                status.setMsg(FUNCTION_NAME + "已经存在！");
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean isSave(UserBean entity, JsonStatus status) {
        UserBean bean=(UserBean)entity;
        List<UserBean> userBeans = dao.find("select ob from UserBean ob where ob.loginName = ?1", bean.getLoginName());
        if(userBeans!=null&&userBeans.size()>0){
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

    public IRoleBeanDao getRoleBeanDao() {
        return roleBeanDao;
    }

    public void setRoleBeanDao(IRoleBeanDao roleBeanDao) {
        this.roleBeanDao = roleBeanDao;
    }

    public IUserBeanDao getUserBeanDao() {
        return dao;
    }

//    public void setUserBeanDao(IUserBeanDao dao) {
//        this.dao = dao;
//
//    }

   // public void init() {
        /*UserBeanImpl user = new UserBeanImpl();
        user.setName("test");
        user.setPassword("hello");
        user = addUser(user);
        List<UserBeanImpl> list = this.getAllUser();

        System.out.print("system is called " + list.size());*/
   // }



//    public JsonData getAllUser() {
//        JsonData jsonData=new JsonData();
//        List<PersistentEntity> users = dao.findByNativeSql("select * from sys_user order by name asc", UserBean.class, null);
//        /*List<PersistentEntity> persistentEntities=new ArrayList<PersistentEntity>();
//        if(users!=null&&users.size()>0){
//            for(UserBean user:users){
//                if(user!=null) {
//                    persistentEntities.add(user);
//                }
//            }
//        }*/
//        jsonData.setData(users);
//        jsonData.setTotalCount((long) users.size());
//       return jsonData;
//    }

//    @Override
//    public List<UserBean> queryUser(UserBean userBean, int is_ent) {
//
//        return dao.find("select a from UserBean a where a.is_ent_user =?1 and a.name LIKE ?2", is_ent, "%" + userBean.getName() + "%");
//    }
//
//    @Override
//    public List<UserBean> queryUser(String userName, int pageNumber, int pageSize) {
//
//        return dao.findbyPage("select a from UserBean a where a.name LIKE ?1", pageNumber, pageSize, "%" + userName + "%");
//    }
//
//    @Override
//    public List<UserBean> query(UserBean userBean, int is_ent) {
//        return queryUser(userBean, is_ent);
//    }

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

    @Override
    public void saveUserRole(UserBean userBean, List<String> roleSelect) {
        List<RoleBean> roleBeanList = userBean.getRoleList();
        //清空全部角色
        roleBeanList.clear();
        //重新添加角色
        if (roleSelect != null && roleSelect.size() > 0) {
            for (String role : roleSelect) {
                roleBeanList.add(roleBeanDao.getRole(role));
            }
        }
        dao.save(userBean);
    }

    @Override
    public void saveUserRoleNew(UserBean userBean, List<String> roleSelect) {
        List<RoleBean> roleBeanList = new ArrayList<RoleBean>();
        if (userBean.getId() == 0L) {       //为新用户对象
            userBean = dao.save(userBean);
        } else {                            //取出用户的角色集合
            roleBeanList = dao.get(userBean.getId()).getRoleList();
        }
        //删除全部该角色下的用户
        if (roleSelect != null) {
            removeRole(userBean, roleBeanList);
            if (roleSelect.size() > 0) {
                //添加角色到用户
                for (String roleName : roleSelect) {
                    RoleBean roleBean = roleBeanDao.getRole(roleName);
                    UserBean user = dao.getUser(userBean.getId());
                    if (!user.getRoleList().contains(roleBean)) {
                        user.getRoleList().add(roleBean);
                        dao.save(user);
                    }
                }
            }
        } else {
            dao.save(userBean);
        }
    }

    private void removeRole(UserBean userBean, List<RoleBean> roleBeanList) {
        for (RoleBean roleBean : roleBeanList) {
            userBean.getRoleList().remove(roleBean);
            dao.save(userBean);
        }
    }

//    @Override
//    public String getCurrentUserLoginName() {
//        Session session = SecurityUtils.getSubject().getSession();
//        UserBean userBean = (UserBean) session.getAttribute(PermissionConstant.SYS_CURRENT_USER);
//        if (userBean == null) {
//            SecurityUtils.getSubject().logout();
//        }
//        return userBean.getName();
//    }

//    @Override
//    public String getCurrentUserLoginName() {
//        Session session = SecurityUtils.getSubject().getSession();
//        UserBean userBean = (UserBean) session.getAttribute(PermissionConstant.SYS_CURRENT_USER);
//        if (userBean == null) {
//            SecurityUtils.getSubject().logout();
//        }
//        return userBean.getLoginName();
//    }

//    @Override
//    public UserBean getCurrentUser() {
//        try {
//            SecurityUtils.setSecurityManager((WebSecurityManager) JNDIHelper.getJNDIServiceForName(WebSecurityManager.class.getName()));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        Session session = SecurityUtils.getSubject().getSession();
//        UserBean userBean = (UserBean) session.getAttribute(PermissionConstant.SYS_CURRENT_USER);
//        if (userBean == null) {
//            SecurityUtils.getSubject().logout();
//        }
//        return userBean;
//    }

//    @Override
//    public String getCurrUserInQhdm() {
//        UserBean userBean = getCurrentUser();
//        if (userBean != null) {
//            List<String> qhdmList = dao.findByNativeSql("select t.xzqh_dm " +
//                    "from urg_ent_organization t where t.jgdm=" + userBean.getJgdm(), String.class);
//            if (qhdmList != null && qhdmList.size() > 0) {
//                return qhdmList.get(0);
//            }
//        }
//        return "";
//
//    }


//    @Override
//    public List getUserTokenListByIds(Long id) {
//        return dao.findByNativeSql("select s.token from sys_user_rel s where s.token is not null and s.user_id in (" + id + ")", String.class);
//    }

//    @Override
//    public List getUserTokenListJgdm(String jgdm, long user_id) {
//        String jgdmStr = jgdm.replaceAll("(0+)$", "");
//        return dao.findByNativeSql("select sur.token from sys_user s " +
//                " left join sys_user_rel sur on sur.user_id=s.id where s.id!=" + user_id +
//                " and sur.token is not null and s.jgdm like '" + jgdmStr + "%'", String.class);
//    }

//    @Override
//    public List getUserTokenListByNoticeId(Long notice_id, int reply_type, long user_id) {
//        return dao.findByNativeSql("select s.token from sys_user_rel s where s.token is not null and s.user_id in " +
//                "(select c.user_id from coop_notice_user_rel c " +
//                "where c.user_id != " + user_id + " and c.notice_id=" + notice_id + " and c.reply_type=" + reply_type + ")", String.class);
//    }

//    @Override
//    public List getUseridListByGgdm(String jgdm, long user_id) {
//        String jgdmStr = jgdm.replaceAll("(0+)$", "");
//        return dao.findByNativeSql("select s.id from sys_user s " +
//                " where s.id!=" + user_id + " and s.jgdm like '" + jgdmStr + "%'", Long.class);
//    }

//    @Override
//    public List<UserBean> getUserListByCond(int is_ent_user) {
//        return dao.findByNativeSql("select * from sys_user u where u.is_ent_user=" + is_ent_user, UserBean.class);
//    }

    @Override
    public UserBean getUserBeanByLoginName(String username) {
        return dao.findUnique("select a from UserBean a where a.loginName = ?1", username);
    }

    @Override
    public void setUserUnavailable(String relateId) {
        dao.update("update sys_user set available=0 where relateId=" + relateId);
    }

//    @Override
//    public UserBean getUserByRelateId(String relateId) {
//        return dao.findUnique("select a from UserBean a where a.relateId = ?1", relateId);
//    }
}
