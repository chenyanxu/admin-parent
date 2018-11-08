package com.kalix.admin.duty.api.biz;

import com.kalix.admin.duty.entities.DataAuthBean;
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
public interface IDataAuthBeanService extends IBizService<DataAuthBean> {

    /**
     * 保存数据权限与用户关联
     *
     * @param ids
     * @return
     */
    JsonStatus saveDataAuthUsers(List ids);

    DataAuthBean getDataAuthBean(String userId, String appId, String menuId);

    List getUserIdsByDataAuthId(String dataAuthId);
}
