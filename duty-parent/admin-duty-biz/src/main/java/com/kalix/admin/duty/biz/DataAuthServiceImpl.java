package com.kalix.admin.duty.biz;

import com.kalix.framework.core.api.security.IDataAuthService;

/**
 * Created by admin on 2016/12/3.
 */
public class DataAuthServiceImpl implements IDataAuthService {
    @Override
    public boolean isAuth(String entityClassName, Long userId) {
        return false;
    }


}
