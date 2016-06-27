package com.kalix.admin.duty.api.biz;


import com.kalix.admin.duty.entities.DutyBean;
import com.kalix.framework.core.api.biz.IBizService;
import com.kalix.framework.core.api.persistence.JsonStatus;
import com.kalix.framework.core.api.persistence.JsonData;

import java.util.List;

/**
 * @类描述：应用服务接口.
 * @创建人：
 * @创建时间：
 * @修改人：
 * @修改时间：
 * @修改备注：
 */
public interface IDutyBeanService extends IBizService<DutyBean> {
    //在此添加新的业务方法
    List getDutiesByDepId(long depId);

    List getUsersByDutyId(long depId, long dutyId);

    JsonData getUserAll(long depId);

    JsonData getUserAllAndDutyUsers(long depId, long dutyId);

    JsonStatus saveDutyUsers(long depId, long dutyId, String userIds);

    JsonStatus deleteDuty(long depId, long dutyId);
}
