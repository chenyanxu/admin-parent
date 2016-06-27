package com.kalix.admin.core.api.biz;

import com.kalix.admin.core.entities.PluginBean;
import com.kalix.framework.core.api.biz.IBizService;
import com.kalix.framework.core.api.persistence.JsonStatus;

import java.util.Map;

/**
 * 插件服务接口.
 *
 * @author sunlf <br/>
 *         date:2015-12-30
 * @version 1.0.0
 */
public interface IPluginBeanService extends IBizService<PluginBean> {
    JsonStatus startPlugin(String key);

    JsonStatus stopPlugin(String key);

    Map getPluginStatus(String pluginIds);
}
