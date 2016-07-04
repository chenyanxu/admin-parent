package com.kalix.admin.core.web.impl.menu;

import com.kalix.framework.core.api.web.IMenu;

/**
 * 区域菜单
 * @author majian <br/>
 *         date:2015-8-10
 * @version 1.0.0
 */
public class AreaMenuImpl implements IMenu {
    @Override
    public boolean isLeaf() {
        return true;
    }

    @Override
    public String getModuleId() {
        return "constructModule";
    }

    @Override
    public String getParentMenuId() {
        return null;
    }

    @Override
    public String getId() {
        return "areaMenu";
    }

    @Override
    public String getText() {
        return "区域管理";
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public String getRouteId() {
        return "admin/Area";
    }

    @Override
    public int getIndex() {
        return 50;
    }

    @Override
    public String getPermission() {
        return  "admin:" + getModuleId() + ":" + getId();
    }

    @Override
    public String getIconCls() {
        return "iconfont icon-area-management";
    }
}
