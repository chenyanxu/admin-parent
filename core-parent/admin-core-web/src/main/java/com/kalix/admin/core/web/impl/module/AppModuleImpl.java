package com.kalix.admin.core.web.impl.module;

import com.kalix.framework.core.api.web.IMenu;
import com.kalix.framework.core.api.web.IModule;

import java.util.List;

/**
 * Created by sunlf on 2015/7/19.
 */
public class AppModuleImpl implements IModule {
    @Override
    public List<IMenu> getMenus() {
        return null;
    }

    @Override
    public String getApplicationId() {
        return "admin";
    }

    @Override
    public String getId() {
        return "appModule";
    }

    @Override
    public String getText() {
        return "模块管理";
    }

    @Override
    public String getDescription() {
        return "模块管理";
    }

    @Override
    public String getIcon() {
        return "resources/images/computer.png";
    }

    @Override
    public String getRouteId() {
        return null;
    }

    @Override
    public int getIndex() {
        return 20;
    }

    @Override
    public String getPermission() {
        return "admin:appModule";
    }

    @Override
    public String getIconCls() {
        return "iconfont icon-module";
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
