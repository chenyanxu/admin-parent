package com.kalix.admin.core.web.impl.module;

import com.kalix.admin.core.web.impl.Const;
import com.kalix.framework.core.api.web.IMenu;
import com.kalix.framework.core.api.web.IModule;

import java.util.List;

/**
 * Created by sunlf on 2015/7/19.
 */
public class AdminModuleImpl implements IModule {
    @Override
    public List<IMenu> getMenus() {
        return null;
    }

    @Override
    public String getApplicationId() {
        return Const.APP_NAME;
    }

    @Override
    public String getId() {
        return "sysModule";
    }

    @Override
    public String getText() {
        return "系统管理";
    }

    @Override
    public String getDescription() {
        return "系统管理";
    }

    @Override
    public String getIcon() {
        return "resources/images/wrench.png";
    }

    @Override
    public String getRouteId() {
        return null;
    }

    @Override
    public int getIndex() {
        return 10;
    }

    @Override
    public String getPermission() {
        return Const.APP_NAME + ":" + getId();
    }

    @Override
    public String getIconCls() {
        return "iconfont icon-system";
    }

    @Override
    public boolean isExpanded() {
        return false;
    }

    @Override
    public boolean isSelectable() {
        return false;
    }
}
