/**
 * 工作组表格
 * @author majian <br/>
 *         date:2015-7-10
 * @version 1.0.0
 */
Ext.define('kalix.admin.workgroup.view.WorkGroupGrid', {
    extend: 'kalix.view.components.common.BaseGrid',
    requires: [
        'kalix.admin.workgroup.controller.WorkGroupGridController',
        'kalix.admin.workgroup.store.WorkGroupStore',
    ],
    alias: 'widget.workgroupGrid',
    xtype: 'workgroupGridPanel',
    controller: {
        type: 'workgroupGridController',
        storeId: 'workgroupStore',
        cfgForm: 'kalix.admin.workgroup.view.WorkGroupWindow',
        cfgViewForm: 'kalix.admin.workgroup.view.WorkGroupViewWindow',
        cfgModel: 'kalix.admin.workgroup.model.WorkGroupModel'
    },
    store: {
        type: 'workgroupStore'
    },
    columns: {
        defaults: {flex: 1, renderer: 'addTooltip'},
        items: [
            {
                xtype: "rownumberer",
                text: "行号",
                width: 50,
                flex: 0,
                align: 'center',
                renderer: this.update
            },
            {text: '编号', dataIndex: 'id', hidden: true},
            {text: '所属应用', dataIndex: 'app'},
            {text: '名称', dataIndex: 'name'},
            {text: '备注', dataIndex: 'remark'},
            {text: '创建人', dataIndex: 'createBy'},
            {
                text: '创建日期', dataIndex: 'creationDate'
            },
            {
                xtype: 'securityGridColumnCommon',
                items: [
                    {
                        iconCls:'iconfont icon-view-column',
                        permission: 'admin:permissionModule:workGroupMenu:view',
                        tooltip: '查看',
                        handler: 'onView'
                    },
                    {
                        iconCls:'iconfont icon-edit-column',
                        permission: 'admin:permissionModule:workGroupMenu:edit',
                        tooltip: '编辑',
                        handler: 'onEdit'
                    }, {
                        iconCls:'iconfont icon-delete',
                        permission: 'admin:permissionModule:workGroupMenu:delete',
                        tooltip: '删除',
                        handler: 'onDelete'
                    }, {
                        iconCls:'iconfont icon-add-user-column',
                        permission: 'admin:permissionModule:workGroupMenu:addUser',
                        tooltip: '添加用户',
                        handler: 'onAddUser'
                    }, {
                        iconCls:'iconfont icon-role-management-column',
                        permission: 'admin:permissionModule:workGroupMenu:addRole',
                        tooltip: '添加角色',
                        handler: 'onAddRole'
                    }]
            }]

    },
    tbar: {
        xtype: 'securityToolbar',
        verifyItems: [
            {
                text: '添加',
                tooltip: '添加工作组',
                xtype: 'button',
                permission: 'admin:permissionModule:workGroupMenu:add',
                iconCls:'iconfont icon-add',
                handler: 'onAdd'
            }
        ]
    }

});