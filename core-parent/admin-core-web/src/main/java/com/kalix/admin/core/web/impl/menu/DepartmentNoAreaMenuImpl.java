package com.kalix.admin.core.web.impl.menu;

import com.kalix.admin.core.web.impl.Const;
import com.kalix.framework.core.api.web.IMenu;

/**
 * 部门菜单
 *
 * @author majian <br/>
 *         date:2015-8-10
 * @version 1.0.0
 */
public class DepartmentNoAreaMenuImpl implements IMenu {
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
        return "departmentNoAreaMenu";
    }

    @Override
    public String getText() {
        return "部门管理";
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public String getIcon() {
        return "admin/resources/images/building.png";
    }

    @Override
    public String getRouteId() {
        return "admin/DepNoArea";
    }

    @Override
    public int getIndex() {
        return 30;
    }

    @Override
    public String getPermission() {
        return null;
    }

    @Override
    public String getIconCls() {
        return "iconfont icon-department-management";
    }
}
