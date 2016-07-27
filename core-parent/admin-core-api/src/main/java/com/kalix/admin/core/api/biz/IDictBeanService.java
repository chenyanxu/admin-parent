package com.kalix.admin.core.api.biz;

import com.kalix.admin.core.entities.DictBean;
import com.kalix.framework.core.api.biz.IBizService;

import java.util.List;
import java.util.Map;


/**
 * @类描述：字典服务接口类
 * @创建人：sunlf
 * @创建时间：2014-05-14 下午1:56
 * @修改人：
 * @修改时间：
 * @修改备注：
 */

public interface IDictBeanService extends IBizService<DictBean> {
    List<Map> getDictTypes(String query);
}
