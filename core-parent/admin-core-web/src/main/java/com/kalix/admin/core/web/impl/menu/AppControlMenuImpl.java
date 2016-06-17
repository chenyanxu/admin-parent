package com.kalix.admin.core.web.impl.menu;

import com.kalix.framework.core.api.web.IMenu;

/**
 * Created by sunlf on 2015/7/19.
 * 权限控制菜单
 */
public class AppControlMenuImpl implements IMenu {
    @Override
    public boolean isLeaf() {
        return false;
    }

    @Override
    public String getModuleId() {
        return "appModule";
    }

    @Override
    public String getParentMenuId() {
        return null;
    }

    @Override
    public String getId() {
        return "appControlMenu";
    }

    @Override
    public String getText() {
        return "模块控制";
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public String getIcon() {
        return "resources/images/image_link.png";
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
        return "admin:appModule:appControlMenu";
    }

    @Override
    public String getIconCls() {
        return "x-fa fa-file-o";
    }
}
