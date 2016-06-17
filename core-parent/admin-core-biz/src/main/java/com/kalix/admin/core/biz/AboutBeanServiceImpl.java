package com.kalix.admin.core.biz;

import com.kalix.admin.core.api.biz.IAboutBeanService;
import com.kalix.admin.core.api.dao.IAboutBeanDao;
import com.kalix.admin.core.entities.AboutBean;
import com.kalix.framework.core.impl.biz.GenericBizServiceImpl;

/**
 * 项目名称:  urgent-project
 * 类描述:    系统版本服务实现类
 * 创建人:    sunlf
 * 创建时间:  2014/8/7 16:02
 * 修改人:    sunlf
 * 修改时间:  2014/8/7 16:02
 * 修改备注:  [说明本次修改内容]
 */
public class AboutBeanServiceImpl extends GenericBizServiceImpl<IAboutBeanDao, AboutBean> implements IAboutBeanService {
    public AboutBeanServiceImpl() {
        super.init(AboutBean.class.getName());
    }
    @Override
    public AboutBean getSysInfo() {
        return null;
    }

}
