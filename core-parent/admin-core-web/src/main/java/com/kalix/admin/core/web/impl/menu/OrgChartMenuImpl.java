package com.kalix.admin.core.web.impl.menu;

import com.kalix.framework.core.api.web.IMenu;

/**
 * 机构菜单
 * @author majian <br/>
 *         date:2015-8-10
 * @version 1.0.0
 */
public class OrgChartMenuImpl implements IMenu {
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
        return "orgChartMenu";
    }

    @Override
    public String getText() {
        return "组织机构图";
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public String getIcon() {
        return "admin/resources/images/script.png";
    }

    @Override
    public String getRouteId() {
        return "admin/Orgchart";
    }

    @Override
    public int getIndex() {
        return 0;
    }

    @Override
    public String getPermission() {
        return "";
    }

    @Override
    public String getIconCls() {
        return "iconfont icon-organization-chart";
    }
}
