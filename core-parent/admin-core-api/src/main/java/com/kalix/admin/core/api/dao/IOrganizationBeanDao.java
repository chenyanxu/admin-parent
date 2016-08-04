package com.kalix.admin.core.api.dao;

import com.kalix.admin.core.entities.OrganizationBean;
import com.kalix.framework.core.api.dao.IGenericDao;

import java.util.List;

/**
 * 机构管理DAO接口
 * @author majian date:2015-7-21
 *
 * 修改 2016-06-30 by p
 * 删除多余的接口方法
 * 添加需要的接口方法
 *
 * @version 1.0.0
 */
public interface IOrganizationBeanDao extends IGenericDao<OrganizationBean, Long> {

    /**
     * 查询指定名称的机构（不包括指定的id） 2016-06-30 by p
     *
     * @param id
     * @param name
     * @return
     */
    List<OrganizationBean> findByName(Long id, String name);

    /**
     * 查询指定代码的机构（不包括指定的id） 2016-06-30 by p
     *
     * @param id
     * @param code
     * @return
     */
    List<OrganizationBean> findByCode(Long id, String code);

    /**
     * 查询指定代码的机构 2016-06-30 by p
     *
     * @param code
     * @return
     */
    List<OrganizationBean> findByCode(String code);

    /**
     * 查询指定父代码的机构（不包括指定的id） 2016-06-30 by p
     *
     * @param parentId
     * @return
     */
    List<OrganizationBean> findByParentId(Long parentId);

    /**
     * 查询所有id集合中的机构 2016-08-03 by p
     *
     * @param id
     * @return
     */
    List<OrganizationBean> findById(List<Long> id);
}
