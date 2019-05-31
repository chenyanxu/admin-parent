package com.kalix.admin.core.api.dao;

import com.kalix.admin.core.entities.UserBean;
import com.kalix.framework.core.api.dao.IGenericDao;
import com.kalix.framework.core.api.dao.IUserEntityDao;
import com.kalix.framework.core.api.persistence.JsonData;

import java.util.List;

/**
 * Created by dell on 14-1-16.
 */

public interface IUserBeanDao extends IUserEntityDao<UserBean,Long> {//IGenericDao<UserBean, Long> {
    /**
     * Gets a list of users ordered by the uppercase version of their username.
     *
     * @return List populated list of users
     */
    //JsonData getUserList(int page,int limit);

    /**
     * Saves a user's information.
     *
     * @param user the object to be saved
     * @return the persisted User object
     */
    //UserBean saveUser(UserBean user);

    /**
     * 删除一个用户
     *
     * @param userId 用户ID
     */
    //void removeUser(Long userId);

    //UserBean getUser(Long userId);

    /**
     * 通过用户名获得用户对象
     *
     * @param username 用户名
     * @return
     */
    //UserBean getUser(String username);

    //void updateUserLoginInfo(long id, String loginIp);

    /**
     * 查询包含（不包含）用户id集合的用户信息 20106-07-01 by p
     *
     * @param userId
     * @param contain true 包含、false不包含
     * @return
     */
    List<UserBean> findByUserId(List<Long> userId, boolean contain);

    void updateBatch(List<UserBean> userBeans);
}
