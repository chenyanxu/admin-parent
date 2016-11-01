package com.kalix.admin.core.api.biz;


import com.kalix.admin.core.entities.UserBean;
import com.kalix.framework.core.api.biz.IBizService;
import com.kalix.framework.core.api.persistence.JsonData;
import com.kalix.framework.core.api.persistence.JsonStatus;

/**
 * Created by dell on 14-1-17.
 */
public interface IUserBeanService extends IBizService<UserBean> {
    /**
     * 生成roleList列表，以逗号分隔
     *
     * @param userBean 用户
     * @return
     */
    String getRoleList(UserBean userBean);

//    /**
//     * 保存用户以及相关的角色
//     *
//     * @param userBean
//     * @param roleSelect
//     */
    //void saveUserRole(UserBean userBean, List<String> roleSelect);

    //void saveUserRoleNew(UserBean userBean, List<String> roleSelect);

    UserBean getUserBeanByLoginName(String username);

    /**
     * 将用户置为无效
     *
     * @param relateId
     */
    void setUserUnavailable(String relateId);

    /**
     * 检查指定用户id的密码是否正确
     *
     * @param userId
     * @param password
     * @return
     */
    boolean checkUserPassword(long userId, String password);

    /**
     * 查询指定用户id所属机构（包括子机构）下所有用户
     *
     * @param userId
     * @return
     */
    JsonData findOrgsUserByUserId(Long userId);
//    JsonData getAllUser();

//    List<UserBean> queryUser(UserBean userBean, int is_ent);

    /**
     * 分页查询用户
     *
     * @param userName   用户名
     * @param pageNumber 页数
     * @param pageSize   每页大小
     * @return 用户结果集
     */
//    List<UserBean> queryUser(String userName, int pageNumber, int pageSize);

//    List<UserBean> query(UserBean userBean, int is_ent);

    /**
     * 获得当前登陆用户名称
     *
     * @return
     */
    //String getCurrentUserLoginName();

    /**
     * 获得当前登陆用户登陆名称
     *
     * @return
     */
    //String getCurrentUserLoginName();

    /**
     * 获得当前登陆用户实体
     *
     * @return
     */
    //UserBean getCurrentUser();

    /**
     * 获取当前用户所在地区的代码
     *
     * @return
     */
    //String getCurrUserInQhdm();

    /**
     * 通过用户id串获取用户token集合
     *
     * @param id
     * @return
     */
    //List getUserTokenListByIds(Long id);

    /**
     * 通过jgdm获取用户token集合
     *
     * @return
     */
    //List getUserTokenListJgdm(String jgdm, long user_id);

    /**
     * 根据日程id获取不同类型用户token
     *
     * @param notice_id
     * @return
     */
    //List getUserTokenListByNoticeId(Long notice_id, int reply_type, long user_id);

    /**
     * 根据机构代码串获取这些机构下的所有用户
     *
     * @param jgdm
     * @return
     */
    //List getUseridListByGgdm(String jgdm, long user_id);

    //List<UserBean> getUserListByCond(int is_ent_user);

    /**
     * 根据隐患排查传过来的信息修改用户
     *
     * @param relateId
     * @return
     */
    //UserBean getUserByRelateId(String relateId);

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
    JsonData findUserByOrgId(Long orgId, int page, int limit, String sort);

    JsonStatus saveEntityWithOrg(UserBean entity, Long id);
}
