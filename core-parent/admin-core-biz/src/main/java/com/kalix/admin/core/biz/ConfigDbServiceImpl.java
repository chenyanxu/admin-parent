package com.kalix.admin.core.biz;

import com.kalix.admin.core.api.biz.IConfigDbService;
import com.kalix.framework.core.api.config.IConfigService;
import com.kalix.framework.core.api.persistence.JsonData;
import com.kalix.framework.core.api.persistence.JsonStatus;

/**
 * Created by Administrator_ on 2018/1/26.
 */
public class ConfigDbServiceImpl implements IConfigDbService {
    private JsonStatus jsonStatus = new JsonStatus();
    private IConfigService configService;

    public void setConfigService(IConfigService configService) {
        this.configService = configService;
    }

    /**
     * 获取数据库配置信息
     *
     * @return
     */
    @Override
    public JsonData getDBInfo() {
        JsonData jsondata= configService.getConfigInfo("ConfigDb");
        jsonStatus.setSuccess(true);
        return jsondata;
    }

    /**
     * 保存数据库配置信息
     *
     * @return
     */
    @Override
    public JsonStatus configureDB(String content)
    {
        configService.configureConfigInfo(content,"ConfigDb");
        jsonStatus.setMsg("设置成功！");
        jsonStatus.setSuccess(true);
        return jsonStatus;
    }

    /**
     * 根据id获取配置信息
     *
     * @return
     */
    public JsonData configureDbinfo(String id)
    {
        JsonData jsondata= configService.getConfigInfoById("ConfigDb",id);
        jsonStatus.setSuccess(true);
        return jsondata;
    }
}
