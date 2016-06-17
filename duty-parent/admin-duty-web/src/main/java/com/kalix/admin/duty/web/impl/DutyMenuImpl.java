package com.kalix.admin.duty.web.impl;

import com.kalix.framework.core.api.web.IMenu;

public class DutyMenuImpl implements IMenu {
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
            return "dutyMenu";
        }

        @Override
        public String getDescription() {
            return "职位管理";
        }

        @Override
        public String getIcon() {
            return null;
        }

        @Override
        public int getIndex() {
            return 40;
        }

        @Override
        public String getIconCls() {
            return "iconfont icon-duty-management";
        }

        @Override
        public String getText() {
            return "职位管理";
        }

        @Override
        public String getRouteId() {
            return "admin/dutyNoArea";
        }

        @Override
        public String getPermission() {
            return "admin:" + getModuleId() + ":" + getId();
        }

}
