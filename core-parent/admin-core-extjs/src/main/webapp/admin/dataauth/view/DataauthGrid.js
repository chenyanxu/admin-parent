/**
 * 角色表格
 * @author majian <br/>
 *         date:2015-7-10
 * @version 1.0.0
 */
Ext.define('kalix.admin.dataauth.view.DataauthGrid', {
    extend: 'kalix.view.components.common.BaseGrid',
    requires: [
        'kalix.admin.dataauth.controller.DataauthGridController',
        'kalix.admin.dataauth.store.DataauthStore',
        'kalix.view.components.common.SecurityGridColumnCommon',
        'kalix.admin.adminDict.component.AdminDictGridColumn',
        'kalix.view.components.common.IconColumn'
    ],
    alias: 'widget.dataauthGrid',
    xtype: 'dataauthGridPanel',
    controller: {
        type: 'dataauthGridController',
        cfgForm: 'kalix.admin.dataauth.view.DataauthWindow',
        cfgViewForm: 'kalix.admin.dataauth.view.DataauthViewWindow',
        cfgModel: 'kalix.admin.dataauth.model.DataauthModel'
    },
    store: {
        type: 'dataauthStore'
    },
    //columns: {
        //defaults: {flex: 1, renderer: 'addTooltip'},
        columns: [
            {
                xtype: 'rownumberer'
                // text: '行号',
                // width: 50,
                // align: 'center',
                // flex:0,
                // renderer: this.update
            },
            {text: '编号', dataIndex: 'id', hidden: true},
            {text: '所属应用', dataIndex: 'appName',flex:0.5},
            {text: '数据权限名称',
                xtype: 'adminDictGridColumn',
                dictType: '数据权限',
                dataIndex: 'type'},
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
                    }]
            }],
    //},
    tbar: {
        xtype: 'securityToolbar',
        verifyItems: [
            {
                text: '添加',
                tooltip: '添加权限',
                xtype: 'button',
                permission: 'add',
                iconCls:'iconfont icon-add',
                handler: 'onAdd'
            }
        ]
    }

});