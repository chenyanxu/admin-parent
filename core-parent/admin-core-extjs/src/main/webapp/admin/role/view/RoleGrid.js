/**
 * 角色表格
 * @author majian <br/>
 *         date:2015-7-10
 * @version 1.0.0
 */
Ext.define('kalix.admin.role.view.RoleGrid', {
    extend: 'kalix.view.components.common.BaseGrid',
    requires: [
        'kalix.admin.role.controller.RoleGridController',
        'kalix.admin.role.store.RoleStore',
    ],
    alias: 'widget.roleGrid',
    xtype: 'roleGridPanel',
    controller: {
        type: 'roleGridController',
        cfgForm: 'kalix.admin.role.view.RoleWindow',
        cfgViewForm: 'kalix.admin.role.view.RoleViewWindow',
        cfgModel: 'kalix.admin.role.model.RoleModel'
    },
    store: {
        type: 'roleStore'
    },
    //columns: {
        //defaults: {flex: 1, renderer: 'addTooltip'},
        columns: [
            {
                xtype: "rownumberer",
                // text: "行号",
                // width: 50,
                // align: 'center',
                // flex:0,
                // renderer: this.update
            },
            {text: '编号', dataIndex: 'id', hidden: true},
            {text: '所属应用', dataIndex: 'app',flex:0.5},
            {text: '名称', dataIndex: 'name'},
            {text: '备注', dataIndex: 'remark'},
            {text: '创建人', dataIndex: 'createBy'},
            {
                text: '创建日期', dataIndex: 'creationDate'
            },

            {
                xtype: 'securityGridColumnCommon',
                verifyItems: [
                    {
                        iconCls:'iconfont icon-view-column',
                        permission: 'view',
                        tooltip: '查看',
                        handler: 'onView'
                    },
                    {
                        iconCls:'iconfont icon-edit-column',
                        permission: 'edit',
                        tooltip: '编辑',
                        handler: 'onEdit'
                    }, {
                        iconCls:'iconfont icon-delete',
                        permission: 'delete',
                        tooltip: '删除',
                        handler: 'onDelete'
                    }, {
                        iconCls:'iconfont icon-add-user-column',
                        permission: 'addUser',
                        tooltip: '添加用户',
                        handler: 'onAddUser'
                    }, {
                        iconCls:'iconfont icon-permission-column',
                        permission: 'auth',
                        tooltip: '权限分配',
                        handler: 'onAuthorization'
                    }]
            }],
    //},
    tbar: {
        xtype: 'securityToolbar',
        verifyItems: [
            {
                text: '添加',
                tooltip: '添加角色',
                xtype: 'button',
                permission: 'add',
                iconCls:'iconfont icon-add',
                handler: 'onAdd'
            }
        ]
    }

});