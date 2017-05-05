/**
 * 模板查看表单
 *
 * @author
 * @version 1.0.0
 */

Ext.define('kalix.sys.template.view.TemplateViewWindow', {
    extend: 'kalix.view.components.common.BaseWindow',
    requires: [
        'kalix.admin.user.store.UserStore'
    ],
    alias: 'widget.templateViewWindow',
    xtype: "templateViewWindow",
    width: 400,
    items: [
        {
            defaults: {readOnly: true},
            xtype: 'baseForm',
            items: [{
                fieldLabel: '模板名称',
                bind: {
                    value: '{rec.name}'
                }
            },
                {
                    fieldLabel: '模板描述',
                    xtype: 'textarea',
                    bind: {
                        value: '{rec.desc}'
                    }
                },
                {
                    fieldLabel: '模板内容',
                    xtype: 'textarea',
                    height: 500,
                    bind: {
                        value: '{rec.content}'
                    }
                }
            ]
        }
    ]
});