package com.kalix.admin.duty.biz;

import com.kalix.admin.duty.api.biz.IDataAuthBeanService;
import com.kalix.admin.duty.entities.DataAuthBean;
import com.kalix.framework.core.api.security.IDataAuthService;
import com.kalix.framework.core.api.security.model.EnumDataAuth;
import com.kalix.framework.core.api.web.ISystemService;
import com.kalix.framework.core.util.JNDIHelper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;

import java.io.IOException;

/**
 * Created by admin on 2016/12/3.
 */
public class DataAuthServiceImpl implements IDataAuthService {
    private IDataAuthBeanService dataAuthBeanService;
    @Override
    public boolean isAuth(String entityClassName, Long userId) {
        return false;
    }

    @Override
    public EnumDataAuth getDataAuth(Long userId) {
        ISystemService systemService = null;
        Session session = SecurityUtils.getSubject().getSession();
        String reqAppName = session.getAttribute("DataAuthApp").toString();
        if (!reqAppName.equals("")) {
            try {
                systemService = JNDIHelper.getJNDIServiceForName(ISystemService.class.getName());
            } catch (IOException e) {
                e.printStackTrace();
            }
            String appName = systemService.getAppName(reqAppName); //获得appName
            //根据appName查询具体的数据权限
            DataAuthBean authBean = dataAuthBeanService.getDataAuthBean(userId, appName, "");
            return EnumDataAuth.values()[Integer.parseInt(authBean.getName())];
        }
        return EnumDataAuth.ALL;
    }

    public void setDataAuthBeanService(IDataAuthBeanService dataAuthBeanService) {
        this.dataAuthBeanService = dataAuthBeanService;
    }
}
