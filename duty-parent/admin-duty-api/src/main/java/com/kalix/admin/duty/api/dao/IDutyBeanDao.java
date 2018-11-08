package com.kalix.admin.duty.api.dao;

import com.kalix.admin.duty.entities.DutyBean;
import com.kalix.framework.core.api.dao.IGenericDao;

import java.util.List;

/**
 * @类描述：DAO接口
 * @创建人：
 * @创建时间：
 * @修改人：
 * @修改时间：
 * @修改备注：
 */
public interface IDutyBeanDao extends IGenericDao<DutyBean, String> {
    //在此添加新的DAO方法

    /**
     * 通过机构id查找职位信息 2016-07-05 by p
     *
     * @param orgId
     * @return
     */
    List<DutyBean> findByOrgId(String orgId);

}
