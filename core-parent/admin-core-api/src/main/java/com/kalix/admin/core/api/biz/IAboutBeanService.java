package com.kalix.admin.core.api.biz;

import com.kalix.admin.core.entities.AboutBean;
import com.kalix.framework.core.api.biz.IBizService;

/**
 * 项目名称:  urgent-project
 * 类描述:    系统版本服务接口类
 * 创建人:    sunlf
 * 创建时间:  2014/8/7 16:01
 * 修改人:    sunlf
 * 修改时间:  2014/8/7 16:01
 * 修改备注:  [说明本次修改内容]
 */
public interface IAboutBeanService extends IBizService<AboutBean> {
    /**
     * 获得系统版本信息
     *
     * @return
     */
    AboutBean getSysInfo();
}
