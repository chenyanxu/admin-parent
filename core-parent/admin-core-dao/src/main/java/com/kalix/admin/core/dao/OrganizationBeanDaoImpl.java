package com.kalix.admin.core.dao;

import com.kalix.admin.core.api.dao.IOrganizationBeanDao;
import com.kalix.admin.core.entities.OrganizationBean;

import java.util.*;

/**
 * 机构管理DAO实现
 * @author majian date:2015-7-21
 *
 * 修改 2016-06-30 by p
 * 删除多余的方法
 * 添加需要的方法
 *
 * @version 1.0.0
 */
public class OrganizationBeanDaoImpl extends BaseAdminDao<OrganizationBean, Long> implements IOrganizationBeanDao {
    private final String className = OrganizationBean.class.getName();

    @Override
    @SuppressWarnings("unchecked")
    public List<OrganizationBean> findByName(Long id, String name) {
        return (List<OrganizationBean>) this.find("select ob from OrganizationBean ob where ob.id <> ?1 and ob.name = ?2 order by ob.code", id, name);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<OrganizationBean> findByCode(Long id, String code) {
        return (List<OrganizationBean>) this.find("select ob from OrganizationBean ob where ob.id <> ?1 and ob.code = ?2 order by ob.code", id, code);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<OrganizationBean> findByCode(String code) {
        return (List<OrganizationBean>) this.find("select ob from OrganizationBean ob where ob.code like ?1 order by ob.code", code + "%");
    }
    @Override
    @SuppressWarnings("unchecked")
    public List<OrganizationBean> findByParentId(Long parentId) {
        return (List<OrganizationBean>) this.find("select ob from OrganizationBean ob where ob.parentId = ?1 order by ob.code", parentId);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<OrganizationBean> findById(List<Long> id) {
        if (id != null && !id.isEmpty()) {
            return (List<OrganizationBean>) this.find("select ob from OrganizationBean ob where ob.id in (?1) order by ob.code", id);
        } else {
            return new ArrayList<>();
        }
    }
}
