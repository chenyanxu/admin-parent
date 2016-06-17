package com.kalix.admin.audit.web.impl;

import com.kalix.framework.core.api.web.IMenu;

/**
 * 审计菜单
 *
 * @author majian <br/>
 *         date:2015-8-10
 * @version 1.0.0
 */
public class AuditMenuImpl  implements IMenu {
    @Override
    public boolean isLeaf() {
        return true;
    }

    @Override
    public String getModuleId() {
        return "sysModule";
    }

    @Override
    public String getParentMenuId() {
        return null;
    }

    @Override
    public String getIconCls() {
        return "iconfont icon-operation-management";
    }

    @Override
    public String getId() {
        return "auditModule";
    }

    @Override
    public String getText() {
        return "审计管理";
    }

    @Override
    public String getDescription() {
        return "可以对业务操作进行审计管理";
    }

    @Override
    public String getIcon() {
        return null;
    }

    @Override
    public String getRouteId() {
        return "admin/Audit";
    }

    @Override
    public int getIndex() {
        return 10;
    }

    @Override
    public String getPermission() {
        return null;
    }
}
