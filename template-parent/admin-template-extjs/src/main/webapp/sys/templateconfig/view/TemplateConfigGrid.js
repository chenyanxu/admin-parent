/**
 * 审计表格
 * @author
 * @version 1.0.0
 */
Ext.define('kalix.sys.templateconfig.view.TemplateConfigGrid', {
    extend: 'kalix.view.components.common.BaseGrid',
    requires: [
        'kalix.sys.templateconfig.controller.TemplateConfigGridController',
        'kalix.sys.templateconfig.store.TemplateConfigStore'
    ],
    alias: 'widget.templateconfigGrid',
    xtype: 'templateconfigGridPanel',
    controller: {
        type: 'templateconfigGridController',
        cfgForm: 'kalix.sys.templateconfig.view.TemplateConfigWindow',
        cfgViewForm: 'kalix.sys.templateconfig.view.TemplateConfigViewWindow',
        cfgModel: 'kalix.sys.templateconfig.model.TemplateConfigModel'
    },
    autoLoad:false,
    store: {
        type: 'templateconfigStore'
    },
    forceFit: true,
    // selModel: {selType: 'checkboxmodel', mode: 'simple'},
    columns: [
        {
            xtype: "rownumberer"
        },
        {text: '编号', dataIndex: 'id', hidden: true},
        {text: '编号', dataIndex: 'templateId', hidden: true},
        {text: '属性名称', dataIndex: 'fieldName'},
        {text: '属性描述', dataIndex: 'fieldDesc', flex: 2},
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
                },
                {
                    iconCls: 'iconfont icon-delete',
                    permission: 'delete',
                    tooltip: '删除',
                    handler: 'onDelete'
                }
            ]
        }
    ],
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