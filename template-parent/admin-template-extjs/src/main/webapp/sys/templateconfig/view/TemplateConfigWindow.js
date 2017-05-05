/**
 * 审计查看表单
 *
 * @author
 * @version 1.0.0
 */

Ext.define('kalix.sys.templateconfig.view.TemplateConfigWindow', {
    extend: 'kalix.view.components.common.BaseWindow',
    alias: 'widget.templateconfigWindow',
    xtype: "templateconfigWindow",
    requires: [
        'kalix.sys.templateconfig.controller.TemplateConfigWindowController'
    ],
    controller:'templateConfigWindowController',
    width: 400,
    items: [
        {
            defaults: {},
            xtype: 'baseForm',
            items: [
                {
                    fieldLabel: '属性名称',
                    bind: {
                        value: '{rec.fieldName}'
                    }
                },
                {
                    fieldLabel: '属性描述',
                    bind: {
                        value: '{rec.fieldDesc}'
                    }
                }
            ]
        }
    ]
});