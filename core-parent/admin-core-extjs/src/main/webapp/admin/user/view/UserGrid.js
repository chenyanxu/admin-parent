/**
 * 用户表格
 * @author majian <br/>
 *         date:2015-7-3
 * @version 1.0.0
 */
Ext.define('kalix.admin.user.view.UserGrid', {
    extend: 'kalix.view.components.common.BaseGrid',
    requires: [
        'kalix.admin.user.controller.UserGridController',
        'kalix.admin.user.store.UserStore',
        'kalix.view.components.common.SecurityGridColumnCommon',
        'kalix.admin.adminDict.component.AdminDictGridColumn'
    ],
    alias: 'widget.userGrid',
    xtype: 'userGridPanel',
    controller: {
        type: 'userGridController',
        cfgForm: 'kalix.admin.user.view.UserWindow',
        cfgViewForm: 'kalix.admin.user.view.UserViewWindow',
        cfgModel: 'kalix.admin.user.model.UserModel'
    },
    store: {
        type: 'userStore'
    },
    columns: [
            {
                xtype: "rownumberer"
            },
            {
                text: '编号',
                dataIndex: 'id',
                hidden: true
            },
            {
                text: '登录名',
                dataIndex: 'loginName'
            },
            {
                text: '姓名',
                dataIndex: 'name'
            },
            {
                text: '岗位名称',
                xtype: 'adminDictGridColumn',
                dictType: '岗位名称',
                dataIndex: 'position'
            },
            {
                text: '邮箱',
                dataIndex: 'email'
            }, /*{
             text: '电话',
             dataIndex: 'phone',
             }, */
            {
                text: '手机',
                dataIndex: 'mobile'
            },
            {
                text: '创建人',
                dataIndex: 'createBy'
            },
            {
                text: '创建日期',
                dataIndex: 'creationDate',
                flex: 2
            }/*,
            {
             text: '更新人',
             dataIndex: 'updateBy',
             flex: 1
             //width: 60
             }, {
             text: '更新日期',
             dataIndex: 'updateDate',
             flex: 2,
             //width: 120,
             renderer: function (value) {
             var updateDate = new Date(value);
             return updateDate.format("yyyy-MM-dd hh:mm:ss");
             }
             }, {
             text: '最后登陆IP',
             dataIndex: 'loginIp',
             flex: 1
             //width: 70
             }, {
             text: '登陆日期',
             dataIndex: 'loginDate',
             flex: 2,
             //width: 120,
             renderer: function (value) {
             var loginDate = new Date(value);
             return loginDate.format("yyyy-MM-dd hh:mm:ss");
             }
             }*/, {
                text: '用户状态',
                dataIndex: 'available',
                renderer: function (value) {
                    if (value != null && value == "1") {
                        return "启用";
                    }
                    return "停用";
                }
            },
            {
                xtype: 'securityGridColumnCommon',
                verifyItems: [{
                    iconCls: 'iconfont icon-view-column',
                    permission: 'view',
                    tooltip: '查看',
                    handler: 'onView'
                },
                    {
                        iconCls: 'iconfont icon-edit-column',
                        permission: 'edit',
                        tooltip: '编辑',
                        handler: 'onEdit'
                    }, {
                        iconCls: 'iconfont icon-delete',
                        permission: 'delete',
                        tooltip: '删除',
                        handler: 'onDelete'
                    }, {
                        iconCls: 'iconfont icon-password-reset',
                        permission: 'key',
                        tooltip: '重置密码',
                        handler: 'onKey'
                    }
                ]
            }
        ]
    //}
    ,
    tbar: {
        xtype: 'securityToolbar',
        verifyItems: [
            {
                text: '添加',
                xtype: 'button',
                permission: 'add',
                iconCls: 'iconfont icon-add',
                handler: 'onAdd'
            }
        ]
    }

});
