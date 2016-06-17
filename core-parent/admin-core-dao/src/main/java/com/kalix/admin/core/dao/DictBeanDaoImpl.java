package com.kalix.admin.core.dao;

import com.kalix.admin.core.api.dao.IDictBeanDao;
import com.kalix.admin.core.entities.DictBean;

import javax.persistence.Query;
import java.util.List;

/**
 * @类描述：字典Dao实现类
 * @创建人：sunlf
 * @创建时间：2014-05-14 下午2:00
 * @修改人：
 * @修改时间：
 * @修改备注：
 */

public class DictBeanDaoImpl extends BaseAdminDao<DictBean, Long> implements IDictBeanDao {
    @Override
    public List<DictBean> getDictList(String type) {
        final Query query = createQuery("select c  from DictBean c where c.type=?1", type);
        final List<DictBean> resultList = query.getResultList();
        return resultList;
    }
}
