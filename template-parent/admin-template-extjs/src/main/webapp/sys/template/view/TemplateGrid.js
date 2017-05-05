/**
 * 模板表格
 * @author
 * @version 1.0.0
 */
Ext.define('kalix.sys.template.view.TemplateGrid', {
    extend: 'kalix.view.components.common.BaseGrid',
    requires: [
        'kalix.sys.template.controller.TemplateGridController',
        'kalix.sys.template.store.TemplateStore'
    ],
    alias: 'widget.templateGrid',
    xtype: 'templateGridPanel',
    controller: {
        type: 'templateGridController',
        cfgForm: 'kalix.sys.template.view.TemplateWindow',
        cfgViewForm: 'kalix.sys.template.view.TemplateViewWindow',
        cfgModel: 'kalix.sys.template.model.TemplateModel'
    },
    store: {
        type: 'templateStore'
    },
    forceFit: true,
    listeners: {
        select: 'onItemClick'
    },

    //selModel: {selType: 'checkboxmodel', mode: 'simple'},
    columns: [
        {
            xtype: "rownumberer"
        },
        {text: '编号', dataIndex: 'id', hidden: true},
        {text: '模板名称', dataIndex: 'name'},
        {text: '模板描述', dataIndex: 'desc'},
        //{text: '创建时间', dataIndex: 'creationDate'},
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