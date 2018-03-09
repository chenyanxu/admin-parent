package com.kalix.admin.core.api.biz;

import com.kalix.admin.core.entities.UserBean;
import com.kalix.framework.core.api.persistence.JsonStatus;

import java.util.List;

/**
 * @类描述：获取融云token接口类
 * @创建人：fwb
 * @创建时间：2014-04-03 下午6:29
 * @修改人：
 * @修改时间：
 * @修改备注：
 */

public interface IAddFieldService{

    JsonStatus setField(UserBean entity);


}
