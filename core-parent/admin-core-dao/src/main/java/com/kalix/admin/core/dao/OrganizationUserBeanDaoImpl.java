package com.kalix.admin.core.dao;

import com.kalix.admin.core.api.dao.IOrganizationUserBeanDao;
import com.kalix.admin.core.entities.OrganizationUserBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 机构用户对应关系DAO实现
 * @author majian date:2015-7-23
 *
 * 修改 2016-07-01 by p
 * 原为部门用户，现改为机构用户
 * 修改类名及部分方法
 *
 * @version 1.0.0
 */
public class OrganizationUserBeanDaoImpl extends BaseAdminDao<OrganizationUserBean, Long> implements IOrganizationUserBeanDao {
    private final String className = OrganizationUserBean.class.getName();

    @Override
    public void deleteByOrganizationId(long id) {
        super.updateNativeQuery("delete from sys_organization_user where orgId = " + id);
    }

    @Override
    @SuppressWarnings("unchecked")
    public long findOrganizationIdByUserId(long id) {
        Optional<OrganizationUserBean> bean = ((List<OrganizationUserBean>) this.find("select t from OrganizationUserBean t where t.userId = ?1", id))
                .stream().findFirst();
        if (bean.isPresent()) {
            return bean.get().getOrgId();
        } else {
            return 0;
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<OrganizationUserBean> findByOrgId(long orgId) {
        return (List<OrganizationUserBean>) this.find("select t from OrganizationUserBean t where t.orgId = ?1", orgId);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<OrganizationUserBean> findByNotOrgId(long orgId) {
        return (List<OrganizationUserBean>) this.find("select t from OrganizationUserBean t where t.orgId <> ?1", orgId);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<OrganizationUserBean> findByUserId(long userId) {
        return (List<OrganizationUserBean>) this.find("select t from OrganizationUserBean t where t.userId = ?1", userId);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<OrganizationUserBean> findByUserIds(List<Long> userId) {
        if (userId != null && !userId.isEmpty()) {
            return (List<OrganizationUserBean>) this.find("select t from OrganizationUserBean t where t.userId in (?1) order by t.userId", userId);
        }
        else {
            return new ArrayList<>();
        }
    }
}
