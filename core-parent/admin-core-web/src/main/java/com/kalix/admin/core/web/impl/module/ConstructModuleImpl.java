package com.kalix.admin.core.web.impl.module;

import com.kalix.admin.core.web.impl.Const;
import com.kalix.framework.core.api.web.IMenu;
import com.kalix.framework.core.api.web.IModule;

import java.util.List;

/**
 * 组织结构模块
 * @author majian <br/>
 *         date:2015-8-10
 * @version 1.0.0
 */
public class ConstructModuleImpl implements IModule {
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
        return "constructModule";
    }

    @Override
    public String getText() {
        return "组织结构";
    }

    @Override
    public String getDescription() {
        return "组织结构";
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
        return 5;
    }

    @Override
    public String getPermission() {
        return Const.APP_NAME + ":" + getId();
    }

    @Override
    public String getIconCls() {
        return "iconfont icon-organization";
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
