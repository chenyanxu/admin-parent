package com.kalix.admin.core.biz;

import com.kalix.admin.core.api.biz.IDictBeanService;
import com.kalix.admin.core.api.dao.IDictBeanDao;
import com.kalix.admin.core.entities.DictBean;
import com.kalix.framework.core.api.cache.ICacheManager;
import com.kalix.framework.core.api.persistence.JsonData;
import com.kalix.framework.core.impl.biz.ShiroGenericBizServiceImpl;
import com.kalix.framework.core.util.ConfigUtil;
import com.kalix.framework.core.util.SerializeUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @类描述：字典服务实现类
 * @创建人：sunlf
 * @创建时间：2014-05-14 下午1:59
 * @修改人：
 * @修改时间：
 * @修改备注：
 */

public class DictBeanServiceImpl extends ShiroGenericBizServiceImpl<IDictBeanDao, DictBean> implements IDictBeanService {
    private static final String FUNCTION_NAME = "字典";
    //    private IDictBeanDao dao;
    private ICacheManager cacheManager;


    public DictBeanServiceImpl() {
        super.init(DictBean.class.getName());
    }

    @Override
    public List<DictBean> getDictList(String type) {
        return dao.getDictList(type);
    }

    @Override
    public Map<String, String> getDictMap(String type) {
        List<DictBean> dictBeanList = getDictList(type);
        Map map = new HashMap();
        if (dictBeanList.size() > 0) {
            for (DictBean dictBean : dictBeanList)
                map.put(dictBean.getValue(), dictBean.getLabel());
        }
        return map;
    }

    @Override
    public List<DictBean> query(DictBean dictBean) {
        return dao.find("select a from DictBean a where a.type like ?1", "%" + dictBean.getType() + "%");
    }


    public JsonData getAll(int page,int limit) {
        return dao.getAll(page, limit);
    }

    @Override
    public List getAllEntity() {
        List rtn = null;

        if (this.cacheManager.exists("all_dict_cache")) {
            rtn = SerializeUtil.unserialize(cacheManager.getObj("all_dict_cache"));
        } else {
            Object obj = ConfigUtil.getConfigProp("dict_cache_timeout", "ConfigCache");
            int cacheTimeout = obj == null ? 600 : new Integer(obj.toString());

            rtn = super.getAllEntity();
            this.cacheManager.save("all_dict_cache", rtn, cacheTimeout);
        }

        return rtn;
    }

    public void setCacheManager(ICacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }
}
