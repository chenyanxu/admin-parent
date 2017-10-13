package com.kalix.admin.duty.biz;

import com.kalix.admin.duty.api.biz.IDataAuthBeanService;
import com.kalix.admin.duty.api.dao.IDataAuthBeanDao;
import com.kalix.admin.duty.api.dao.IDataAuthUserBeanDao;
import com.kalix.admin.duty.entities.DataAuthBean;
import com.kalix.framework.core.api.persistence.JsonStatus;
import com.kalix.framework.core.impl.biz.ShiroGenericBizServiceImpl;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @类描述：
 * @创建人：
 * @创建时间：
 * @修改人：
 * @修改时间：
 * @修改备注：
 */
public class DataAuthBeanServiceImpl extends ShiroGenericBizServiceImpl<IDataAuthBeanDao, DataAuthBean> implements IDataAuthBeanService {
    private IDataAuthUserBeanDao dataAuthUserBeanDao;

    @Override
    @Transactional
    public JsonStatus saveDataAuthUsers(List ids) {
        return null;
    }

    public void setDataAuthUserBeanDao(IDataAuthUserBeanDao dataAuthUserBeanDao) {
        this.dataAuthUserBeanDao = dataAuthUserBeanDao;
    }
}
