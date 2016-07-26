package com.kalix.admin.core.biz;

import com.kalix.admin.core.api.biz.IDictBeanService;
import com.kalix.admin.core.api.dao.IDictBeanDao;
import com.kalix.admin.core.entities.DictBean;
import com.kalix.framework.core.api.cache.ICacheManager;
import com.kalix.framework.core.api.persistence.JsonData;
import com.kalix.framework.core.api.persistence.JsonStatus;
import com.kalix.framework.core.impl.biz.ShiroGenericBizServiceImpl;
import com.kalix.framework.core.util.ConfigUtil;
import com.kalix.framework.core.util.SerializeUtil;

import java.util.ArrayList;
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
    private ICacheManager cacheManager=null;
    private List<Map> dictTypes=null;

    public DictBeanServiceImpl() {
        super.init(DictBean.class.getName());
    }

//    @Override
//    public List<DictBean> getDictList(String type) {
//        return dao.getDictList(type);
//    }
//
//    @Override
//    public Map<String, String> getDictMap(String type) {
//        List<DictBean> dictBeanList = getDictList(type);
//        Map map = new HashMap();
//        if (dictBeanList.size() > 0) {
//            for (DictBean dictBean : dictBeanList)
//                map.put(dictBean.getValue(), dictBean.getLabel());
//        }
//        return map;
//    }
//
//    @Override
//    public List<DictBean> query(DictBean dictBean) {
//        return dao.find("select a from DictBean a where a.type like ?1", "%" + dictBean.getType() + "%");
//    }


    @Override
    public JsonStatus saveEntity(DictBean entity) {
        Integer maxValue = dao.getFieldMaxValue("value","type='"+entity.getType()+"'");

        maxValue=maxValue+1;

        entity.setValue(maxValue.toString());

        return super.saveEntity(entity);
    }

    @Override
    public List getAllEntity() {
        List rtn = null;

        if (this.cacheManager.exists("admin_dict_cache")) {
            rtn = SerializeUtil.unserialize(cacheManager.getObj("admin_dict_cache"));
        } else {
            Object obj = ConfigUtil.getConfigProp("dict_cache_timeout", "ConfigAdminDict");
            int cacheTimeout = obj == null ? 600 : new Integer(obj.toString());

            rtn = super.getAllEntity();
            this.cacheManager.save("admin_dict_cache", rtn, cacheTimeout);
        }

        return rtn;
    }

    public void setCacheManager(ICacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    @Override
    public List<Map> getDictTypes(String query) {
        if(dictTypes==null){
            dictTypes=new ArrayList<>();
            Map map=null;
            Object obj = ConfigUtil.getConfigProp("dict_types", "ConfigAdminDict");

            for (String str :obj.toString().split(",")) {
                map=new HashMap<>();
                map.put("name",str);
                dictTypes.add(map);
            }
        }

        if(query!=null && !query.trim().equals("")){
            List<Map> rtn=new ArrayList<>();

            for(Map map:dictTypes){
                if(map.get("name").toString().contains(query.trim())){
                    rtn.add(map);
                }
            }

            return rtn;
        }

        return dictTypes;
    }
}
