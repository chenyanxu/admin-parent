package com.kalix.admin.core.biz;

import com.kalix.admin.core.api.biz.IAdminDictBeanService;
import com.kalix.admin.core.api.dao.IAdminDictBeanDao;
import com.kalix.admin.core.entities.AdminDictBean;
import com.kalix.framework.core.api.persistence.JsonStatus;
import com.kalix.framework.core.impl.system.BaseDictServiceImpl;

/**
 * @类描述：字典服务实现类
 * @创建人：sunlf
 * @创建时间：2014-05-14 下午1:59
 * @修改人：
 * @修改时间：
 * @修改备注：
 */
public class AdminDictBeanServiceImpl extends BaseDictServiceImpl<IAdminDictBeanDao, AdminDictBean> implements IAdminDictBeanService {
    @Override
    public JsonStatus saveEntity(AdminDictBean entity) {
        Integer maxValue = dao.getFieldMaxValue("value","type='"+entity.getType()+"'");

        maxValue=maxValue+1;
        entity.setValue(maxValue);

        return super.saveEntity(entity);
    }
}
