package com.kalix.admin.core.api.dao;

import com.kalix.admin.core.entities.OrganizationUserBean;
import com.kalix.framework.core.api.dao.IGenericDao;

import java.util.List;

/**
 * 机构用户对应关系DAO实现
 * @author majian date:2015-7-27
 *
 * 修改 2016-07-01 by p
 * 原为部门用户，现改为机构用户
 * 修改类名及部分方法
 *
 * @version 1.0.0
 */
public interface IOrganizationUserBeanDao extends IGenericDao<OrganizationUserBean, String> {
    /**
     * 删除指定机构下所有用户 2016-07-01 by p
     *
     * @param id
     */
    void deleteByOrganizationId(String id);

    /**
     * 查询指定用户所属机构 2016-07-01 by p
     * 原有的对应关系为一个用户只能拥有一个机构
     * 暂时不做修改，如果用户可以拥有多个机构时，修改此接口实现
     *
     * @param userId
     * @return
     */
    String findOrganizationIdByUserId(String userId);

    /**
     * 同时查询指定机构父机构和兄弟机构用户对应关系 2016-09-01 by p
     *
     * @param orgId
     * @return
     */
    List<OrganizationUserBean> findByOrgId(String orgId);

    /**
     * 同时查询指定机构父机构和兄弟机构用户对应关系 2016-09-01 by p
     *
     * @param orgId
     * @return
     */
    List<OrganizationUserBean> findParentAndBrotherByOrgId(String orgId);
    /**
     * 查询不是指定机构的所有机构用户对应关系 2016-07-01 by p
     *
     * @param orgId
     * @return
     */
    List<OrganizationUserBean> findByNotOrgId(String orgId);

    /**
     * 查询指定用户的所有机构用户对应关系 2016-07-01 by p
     *
     * @param userId
     * @return
     */
    List<OrganizationUserBean> findByUserId(String userId);

    /**
     * 查询指定用户集合的所有机构用户对应关系 2016-08-25 by p
     *
     * @param userId
     * @return
     */
    List<OrganizationUserBean> findByUserIds(List<String> userId);
}
