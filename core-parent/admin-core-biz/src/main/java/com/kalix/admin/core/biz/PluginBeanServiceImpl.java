package com.kalix.admin.core.biz;


import com.kalix.admin.core.api.biz.IPluginBeanService;
import com.kalix.admin.core.api.dao.IPluginBeanDao;
import com.kalix.admin.core.entities.PluginBean;
import com.kalix.framework.core.api.biz.JsonStatus;
import com.kalix.framework.core.impl.biz.GenericBizServiceImpl;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleException;

import java.util.HashMap;
import java.util.Map;

/**
 * 插件服务实现
 *
 * @author sunlf <br/>
 *         date:2015-10-27
 * @version 1.0.0
 */
public class PluginBeanServiceImpl extends GenericBizServiceImpl<IPluginBeanDao, PluginBean> implements IPluginBeanService {
    private JsonStatus jsonStatus = new JsonStatus();

    public PluginBeanServiceImpl() {
        super.init(PluginBean.class.getName());
    }

    /**
     * 启动插件
     *
     * @param key 插件id
     * @return
     */
    @Override
    public JsonStatus startPlugin(String key) {
        Bundle bundle = PluginBundleHandler.getBundle(key);
        if (bundle != null) {
            try {
                bundle.start();
                jsonStatus.setSuccess(true);
                jsonStatus.setMsg("服务启动成功");
            } catch (BundleException e) {
                jsonStatus.setSuccess(false);
                jsonStatus.setMsg("服务启动失败");
                e.printStackTrace();
            }
        } else {
            jsonStatus.setSuccess(false);
            jsonStatus.setMsg("服务不存在");
        }
        return jsonStatus;
    }

    @Override
    public JsonStatus stopPlugin(String key) {
        Bundle bundle = PluginBundleHandler.getBundle(key);
        if (bundle != null) {
            try {
                bundle.stop();
                jsonStatus.setSuccess(true);
                jsonStatus.setMsg("服务停止成功");
            } catch (BundleException e) {
                jsonStatus.setSuccess(false);
                jsonStatus.setMsg("服务停止失败");
                e.printStackTrace();
            }
        } else {
            jsonStatus.setSuccess(false);
            jsonStatus.setMsg("服务不存在");
        }
        return jsonStatus;
    }

    @Override
    public Map getPluginStatus(String pluginIds) {
        Map<String, Object> map = new HashMap<>();
        String[] appIdArray = null;

        if (pluginIds != null) {
            appIdArray = pluginIds.split("_");

            for (int idx = 0; idx < appIdArray.length; ++idx) {
                Bundle bundle = PluginBundleHandler.getBundle(appIdArray[idx]);
                if (bundle != null) {
                    if (bundle.getState() == Bundle.ACTIVE) {
                        map.put(appIdArray[idx], true);
                    } else {
                        map.put(appIdArray[idx], false);
                    }
                }
            }
        }

        if (map.size() > 0) {
            map.put("success", true);
        } else {
            map.put("success", false);
        }

        return map;
    }
}