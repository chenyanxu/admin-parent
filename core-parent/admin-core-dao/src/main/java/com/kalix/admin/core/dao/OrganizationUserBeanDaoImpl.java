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
public class OrganizationUserBeanDaoImpl extends BaseAdminDao<OrganizationUserBean, String> implements IOrganizationUserBeanDao {
    private final String className = OrganizationUserBean.class.getName();

    @Override
    public void deleteByOrganizationId(String id) {
        super.updateNativeQuery("delete from sys_organization_user where orgId = '" + id + "'");
    }

    @Override
    @SuppressWarnings("unchecked")
    public String findOrganizationIdByUserId(String id) {
        Optional<OrganizationUserBean> bean = ((List<OrganizationUserBean>) this.find("select t from OrganizationUserBean t where t.userId = ?1", id))
                .stream().findFirst();
        if (bean.isPresent()) {
            return bean.get().getOrgId();
        } else {
            return "0";
        }
    }

    /**
     * 查询指定机构的所有机构用户对应关系 2016-07-01 by p
     *
     * @param orgId
     * @return
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<OrganizationUserBean> findByOrgId(String orgId) {
        return (List<OrganizationUserBean>) this.find("select t from OrganizationUserBean t where t.orgId = ?1", orgId);
    }

    /**
     * 同时查询指定机构父机构和兄弟机构用户对应关系 2016-09-01 by p
     *
     * @param orgId
     * @return
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<OrganizationUserBean> findParentAndBrotherByOrgId(String orgId) {
        return (List<OrganizationUserBean>) this.findByNativeSql("select * from sys_organization_user where orgId in (select id from sys_organization where id = (select parentid from sys_organization where id = '" + orgId + "') or parentId = (select parentid from sys_organization where id = '" + orgId + "'))", OrganizationUserBean.class);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<OrganizationUserBean> findByNotOrgId(String orgId) {
        return (List<OrganizationUserBean>) this.find("select t from OrganizationUserBean t where t.orgId <> ?1", orgId);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<OrganizationUserBean> findByUserId(String userId) {
        return (List<OrganizationUserBean>) this.find("select t from OrganizationUserBean t where t.userId = ?1", userId);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<OrganizationUserBean> findByUserIds(List<String> userId) {
        if (userId != null && !userId.isEmpty()) {
            return (List<OrganizationUserBean>) this.find("select t from OrganizationUserBean t where t.userId in (?1) order by t.userId", userId);
        }
        else {
            return new ArrayList<>();
        }
    }
}
