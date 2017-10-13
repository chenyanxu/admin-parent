package com.kalix.admin.duty.entities;

import com.kalix.framework.core.api.persistence.PersistentEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @类描述：数据权限管理
 * @创建人：hqj
 * @创建时间：2017-10-12
 * @修改人：
 * @修改时间：
 * @修改备注：
 */
//todo 修改模型定义
@Entity
@Table(name = "sys_data_auth")
public class DataAuthBean extends PersistentEntity {
    private String name;     //数据权限名称
    private String remark;   //数据权限备注
    private String appId;    //数据权限所属应用
    @Transient
    private String appName;  //应用名称
    private String menuId;   //数据权限所属菜单
    @Transient
    private String menuName; //菜单名称

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }
}
