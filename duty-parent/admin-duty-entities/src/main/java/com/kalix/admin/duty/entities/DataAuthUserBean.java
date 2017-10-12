package com.kalix.admin.duty.entities;

import com.kalix.framework.core.api.persistence.PersistentEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @类描述：数据权限与用户关联实体类
 * @创建人：hqj
 * @创建时间：2017-10-12
 * @修改人：
 * @修改时间：
 * @修改备注：
 */
@Entity
@Table(name = "sys_data_auth_user")
public class DataAuthUserBean extends PersistentEntity {
    /**
     * 用户.
     */
    private Long userId;
    /**
     * 应用.
     */
    private String appId;
    /**
     * 菜单.
     */
    private String menuId;
    /**
     * 数据权限.
     */
    private Long dataDutyId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public Long getDataDutyId() {
        return dataDutyId;
    }

    public void setDataDutyId(Long dataDutyId) {
        this.dataDutyId = dataDutyId;
    }
}
