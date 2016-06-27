package com.kalix.admin.core.api.dao;

import com.kalix.admin.core.entities.UserBean;
import com.kalix.framework.core.api.dao.IGenericDao;
import com.kalix.framework.core.api.persistence.JsonData;

/**
 * Created by dell on 14-1-16.
 */

public interface IUserBeanDao extends IGenericDao<UserBean, Long> {
    /**
     * Gets a list of users ordered by the uppercase version of their username.
     *
     * @return List populated list of users
     */
    JsonData getUserList(int page,int limit);

    /**
     * Saves a user's information.
     *
     * @param user the object to be saved
     * @return the persisted User object
     */
    UserBean saveUser(UserBean user);

    /**
     * 删除一个用户
     *
     * @param userId 用户ID
     */
    void removeUser(Long userId);

    UserBean getUser(Long userId);

    /**
     * 通过用户名获得用户对象
     *
     * @param username 用户名
     * @return
     */
    UserBean getUser(String username);

    void updateUserLoginInfo(long id, String loginIp);
}
