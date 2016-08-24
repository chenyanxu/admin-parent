package com.kalix.admin.core.biz;

import com.kalix.admin.core.api.biz.IAdminDictBeanService;
import com.kalix.admin.core.api.dao.IDictBeanDao;
import com.kalix.admin.core.entities.DictBean;
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
public class AdminDictBeanServiceImpl extends BaseDictServiceImpl<IDictBeanDao, DictBean> implements IAdminDictBeanService {
    @Override
    public JsonStatus saveEntity(DictBean entity) {
        Integer maxValue = dao.getFieldMaxValue("value","type='"+entity.getType()+"'");

        maxValue=maxValue+1;
        entity.setValue(maxValue);

        return super.saveEntity(entity);
    }
}
