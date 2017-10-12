package com.kalix.admin.duty.api.biz;


import com.kalix.admin.duty.entities.DutyBean;
import com.kalix.framework.core.api.biz.IBizService;
import com.kalix.framework.core.api.persistence.JsonStatus;

import java.util.List;

/**
 * @类描述：应用服务接口.
 * @创建人：
 * @创建时间：
 * @修改人：
 * @修改时间：
 * @修改备注：
 */
public interface IDataAuthBeanService extends IBizService<DutyBean> {
    JsonStatus saveDataAuthUsers(List ids);
}