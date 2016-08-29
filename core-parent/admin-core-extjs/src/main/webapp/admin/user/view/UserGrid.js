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
        },
        {
            text: '所属部门',
            dataIndex: 'org'
        },
        {
            text: '职务',
            dataIndex: 'duty'
        },
        {
            text: '角色',
            dataIndex: 'role'
        },
        {
            text: '工作组',
            dataIndex: 'workGroup'
        },
        {
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
                }, {
                    iconCls: 'iconfont icon-permission-column',
                    permission: 'auth',
                    tooltip: '权限分配',
                    handler: 'onAuthorization'
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
