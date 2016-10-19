package com.kalix.admin.core.entities;

import com.kalix.framework.core.api.persistence.PersistentEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @类描述：角色实现类
 * @创建人：sunlf
 * @创建时间：2014-04-26 上午11:07
 * @修改人：
 * @修改时间：
 * @修改备注：
 */
@Entity
@Table(name = "sys_role")
public class RoleBean extends PersistentEntity {
    private String name;    // 角色名称
    private String remark;  //角色备注
    private String app; //所属应用
//    //    @XmlTransient
//    @ManyToMany(mappedBy = "roleList", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @OrderBy("id")
//    private List<UserBean> userList = new ArrayList<>(); // 拥有用户列表
//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(name = "sys_role_permission", joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "permission_id", referencedColumnName = "id"))
//    @OrderBy("id")
//    private List<PermissionBean> permissionList = new ArrayList<>(); // 拥有菜单列表

    public RoleBean() {
    }

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


//    public List<UserBean> getUserList() {
//        return userList;
//    }
//
//
//    public void setUserList(List<UserBean> userList) {
//        this.userList = userList;
//    }
//
//
//    public List<PermissionBean> getPermissionList() {
//        return permissionList;
//    }
//
//
//    public void setPermissionList(List<PermissionBean> permissionList) {
//        this.permissionList = permissionList;
//    }

    public String getApp() {
        return app;
    }

    public void setApp(String app) {
        this.app = app;
    }
}
