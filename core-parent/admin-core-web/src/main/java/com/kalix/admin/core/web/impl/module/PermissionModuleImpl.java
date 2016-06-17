package com.kalix.admin.core.web.impl.module;

import com.kalix.admin.core.web.impl.Const;
import com.kalix.framework.core.api.web.IMenu;
import com.kalix.framework.core.api.web.IModule;

import java.util.List;

/**
 * Created by sunlf on 2015/7/19.
 * 权限控制菜单
 */
public class PermissionModuleImpl implements IModule {
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
        return "permissionModule";
    }

    @Override
    public String getText() {
        return "权限管理";
    }

    @Override
    public String getDescription() {
        return "权限管理";
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
        return 0;
    }

    @Override
    public String getPermission() {
        return Const.APP_NAME + ":" + getId();
    }

    @Override
    public String getIconCls() {
        return "iconfont icon-permission";
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
