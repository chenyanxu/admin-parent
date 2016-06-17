package com.kalix.admin.core.web.impl.menu;

import com.kalix.admin.core.web.impl.Const;
import com.kalix.framework.core.api.web.IMenu;

/**
 * 工作组菜单
 * @author majian <br/>
 *         date:2015-8-10
 * @version 1.0.0
 */
public class WorkGroupMenuImpl implements IMenu {
    @Override
    public boolean isLeaf() {
        return true;
    }

    @Override
    public String getModuleId() {
        return "permissionModule";
    }

    @Override
    public String getParentMenuId() {
        return null;
    }

    @Override
    public String getId() {
        return "workGroupMenu";
    }

    @Override
    public String getText() {
        return "工作组管理";
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public String getIcon() {
        return "admin/resources/images/cup.png";
    }

    @Override
    public String getRouteId() {
        return "admin/workgroup";
    }

    @Override
    public int getIndex() {
        return 2;
    }

    @Override
    public String getPermission() {
        return Const.APP_NAME + ":" + getModuleId() + ":" + getId();
    }

    @Override
    public String getIconCls() {
        return "iconfont icon-workgroup-management";
    }
}
