package com.kalix.admin.duty.biz;

import com.kalix.framework.core.api.security.IDataAuthService;
import com.kalix.framework.core.api.security.model.EnumDataAuth;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;

/**
 * Created by admin on 2016/12/3.
 */
public class DataAuthServiceImpl implements IDataAuthService {
    @Override
    public boolean isAuth(String entityClassName, Long userId) {
        return false;
    }

    @Override
    public EnumDataAuth getDataAuth(Long userId) {
        Session session = SecurityUtils.getSubject().getSession();
        String reqAppName = session.getAttribute("DataAuthApp").toString();
        if (reqAppName.equals(""))
            return EnumDataAuth.ALL;
        else {
            //根据appName查询具体的数据权限
        }
        return EnumDataAuth.ALL;
    }
}
