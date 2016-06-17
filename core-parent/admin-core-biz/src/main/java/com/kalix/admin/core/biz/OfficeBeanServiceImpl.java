package com.kalix.admin.core.biz;

import com.kalix.admin.core.api.biz.IOfficeBeanService;
import com.kalix.admin.core.api.dao.IOfficeBeanDao;
import com.kalix.admin.core.entities.OfficeBean;
import com.kalix.framework.core.api.persistence.IGenericDao;
import com.kalix.framework.core.impl.biz.GenericBizServiceImpl;

/**
 * @类描述：机构服务实现类
 * @创建人：sunlf
 * @创建时间：2014-05-14 下午1:59
 * @修改人：
 * @修改时间：
 * @修改备注：
 */

public class OfficeBeanServiceImpl extends GenericBizServiceImpl<IOfficeBeanDao, OfficeBean> implements IOfficeBeanService {
    private IOfficeBeanDao officeBeanDao;

    public OfficeBeanServiceImpl() {
        super.init(OfficeBean.class.getName());
    }

//    public void setOfficeBeanDao(IOfficeBeanDao officeBeanDao) {
//        this.officeBeanDao = officeBeanDao;
//
//    }
}
