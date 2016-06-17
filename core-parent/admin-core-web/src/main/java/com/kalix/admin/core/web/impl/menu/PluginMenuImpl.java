package com.kalix.admin.core.web.impl.menu;

import com.kalix.framework.core.api.web.IMenu;

/**
 * 插件菜单
 *
 * @author sunlf <br/>
 *         date:2015-12-10
 * @version 1.0.0
 */
public class PluginMenuImpl implements IMenu {
    @Override
    public boolean isLeaf() {
        return true;
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
        return "pluginMenu";
    }

    @Override
    public String getText() {
        return "插件管理";
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public String getIcon() {
        return "app/resources/images/note.png";
    }

    @Override
    public String getRouteId() {
        return "app/plugin";
    }

    @Override
    public int getIndex() {
        return 30;
    }

    @Override
    public String getPermission() {
        return "admin:" + getModuleId() + ":" + getId();
    }

    @Override
    public String getIconCls() {
        return "x-fa fa-wrench";
    }
}
