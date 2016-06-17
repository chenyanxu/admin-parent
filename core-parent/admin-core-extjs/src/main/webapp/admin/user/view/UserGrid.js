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
        'kalix.view.components.common.SecurityGridColumnCommon'
    ],
    alias: 'widget.userGrid',
    xtype: 'userGridPanel',
    controller: {
        type: 'userGridController',
        storeId: 'userStore',
        cfgForm: 'kalix.admin.user.view.UserWindow',
        cfgViewForm: 'kalix.admin.user.view.UserViewWindow',
        cfgModel: 'kalix.admin.user.model.UserModel'
    },
    store: {
        type: 'userStore'
    },
    columns: {
        defaults: {flex: 1,renderer: 'addTooltip'},
        items: [
        {
            xtype: "rownumberer",
            text: "行号",
            width: 50,
            flex:0,
            align:'center',
            renderer:this.update
        },
        {
            text: '编号',
            dataIndex: 'id',
            hidden:true,
        }, {
            text: '登录名',
            dataIndex: 'loginName',
        }, {
            text: '姓名',
            dataIndex: 'name',
        }, {
            text: '邮箱',
            dataIndex: 'email',
            }, /*{
            text: '电话',
            dataIndex: 'phone',
             }, */{
            text: '手机',
            dataIndex: 'mobile',
        }, {
            text: '创建人',
            dataIndex: 'createBy',
        }, {
            text: '创建日期',
            dataIndex: 'creationDate',
                flex: 2
        }/*, {
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
                items:[{
                    iconCls:'iconfont icon-view-column',
                    permission: 'admin:permissionModule:userMenu:view',
                    tooltip: '查看',
                    handler: 'onView'
                },
                    {
                        iconCls:'iconfont icon-edit-column',
                        permission: 'admin:permissionModule:userMenu:edit',
                        tooltip: '编辑',
                        handler: 'onEdit'
                    }, {
                        iconCls:'iconfont icon-delete',
                        permission: 'admin:permissionModule:userMenu:delete',
                        tooltip: '删除',
                        handler: 'onDelete'
                    },{
                        iconCls:'iconfont icon-password-reset',
                        permission: 'admin:permissionModule:userMenu:key',
                        tooltip: '重置密码',
                        handler: 'onKey',
                    }]
            }
    ]},
    tbar: {
        xtype: 'securityToolbar',
        verifyItems: [
            {
                text: '添加',
                xtype: 'button',
                permission: 'admin:permissionModule:userMenu:add',
                iconCls:'iconfont icon-add',
                handler: 'onAdd'
            }
        ]
    }

});
