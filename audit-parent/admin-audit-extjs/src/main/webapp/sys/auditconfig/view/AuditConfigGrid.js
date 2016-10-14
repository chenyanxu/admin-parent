/**
 * 审计表格
 * @author
 * @version 1.0.0
 */
Ext.define('kalix.sys.auditconfig.view.AuditConfigGrid', {
    extend: 'kalix.view.components.common.BaseGrid',
    requires: [
        'kalix.sys.auditconfig.controller.AuditConfigGridController',
        'kalix.sys.auditconfig.store.AuditConfigStore',
    ],
    alias: 'widget.auditconfigGrid',
    xtype: 'auditconfigGridPanel',
    controller: {
        type: 'auditconfigGridController',
        cfgForm: 'kalix.sys.auditconfig.view.AuditConfigWindow',
        cfgViewForm: 'kalix.sys.auditconfig.view.AuditConfigViewWindow',
        cfgModel: 'kalix.sys.auditconfig.model.AuditConfigModel'
    },
    store: {
        type: 'auditconfigStore'
    },
    forceFit: true,
    // selModel: {selType: 'checkboxmodel', mode: 'simple'},
    columns: [
        {
            xtype: "rownumberer"
        },
        {text: '编号', dataIndex: 'id', hidden: true},
        {text: '类名称', dataIndex: 'clsName', flex: 2},
        {text: '应用名称', dataIndex: 'appName'},
        {text: '功能名称', dataIndex: 'funName'},
        {
            xtype: 'booleancolumn',
            text: '是否监控',
            trueText: '是',
            falseText: '否',
            dataIndex: 'enable',
            renderer: null
        },
        {
            text: '创建日期',
            dataIndex: 'creationDate'
        },
        {
            xtype: 'securityGridColumnCommon',
            verifyItems: [
                {
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
                }]
        }
    ]
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