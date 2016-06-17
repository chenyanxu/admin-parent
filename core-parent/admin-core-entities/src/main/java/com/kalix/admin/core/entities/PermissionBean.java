package com.kalix.admin.core.entities;

import com.kalix.framework.core.api.persistence.PersistentEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @类描述：权限实体实现类
 * @创建人：sunlf
 * @创建时间：2014-04-26 上午11:10
 * @修改人：
 * @修改时间：
 * @修改备注：
 */
@Entity
@Table(name = "sys_permission")
@Inheritance(strategy = InheritanceType.JOINED)
public class PermissionBean extends PersistentEntity {
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "parent_id")
    private PermissionBean parent;    // 父级菜单
    private String parentIds; // 所有父级编号
    //    @NotNull(message = "'名称'是必填项")
    private String name;    // 名称
    private String href;    // 链接
    private String icon;    // 图标
    private long sort;    // 序号
    private Boolean isShow;    // 是否在菜单中显示（1：显示；0：不显示）
    //    @NotNull(message = "'权限标识'是必填项")
    private String permission; // 权限标识
    @OneToMany(mappedBy = "parent", fetch = FetchType.EAGER, cascade = {CascadeType.REMOVE})
    private List<PermissionBean> childList = new ArrayList<>();// 拥有子菜单列表
    @ManyToMany(mappedBy = "permissionList", fetch = FetchType.EAGER)
    @OrderBy("id")
    private List<RoleBean> roleList = new ArrayList<>(); // 拥有角色列表



    public PermissionBean getParent() {
        return parent;
    }


    public void setParent(PermissionBean parent) {
        this.parent = parent;
    }


    public String getParentIds() {
        return parentIds;
    }


    public void setParentIds(String parentIds) {
        this.parentIds = parentIds;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getHref() {
        return href;
    }


    public void setHref(String href) {
        this.href = href;
    }


    public String getIcon() {
        return icon;
    }


    public void setIcon(String icon) {
        this.icon = icon;
    }


    public long getSort() {
        return sort;
    }


    public void setSort(Integer sort) {
        this.sort = sort;
    }


    public Boolean getIsShow() {
        return isShow;
    }

    public void setIsShow(Boolean isShow) {
        this.isShow = isShow;
    }

    public String getPermission() {
        return permission;
    }


    public void setPermission(String permission) {
        this.permission = permission;
    }

    //    @OneToMany(mappedBy = "parent", fetch = FetchType.EAGER, cascade = {CascadeType.REMOVE})
//    @OrderBy(value = "sort")
    public List<PermissionBean> getChildList() {
        return childList;
    }


    public void setChildList(List<PermissionBean> childList) {
        this.childList = childList;
    }


    public List<RoleBean> getRoleList() {
        return roleList;
    }


    public void setRoleList(List<RoleBean> roleList) {
        this.roleList = roleList;
    }

    @Override
    public String toString() {
        return getName();
    }
}
