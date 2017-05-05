/**
 * 审计查看表单
 *
 * @author
 * @version 1.0.0
 */

Ext.define('kalix.sys.templateconfig.view.TemplateConfigViewWindow', {
    extend: 'kalix.view.components.common.BaseWindow',
    requires: [
        'kalix.admin.user.store.UserStore'
    ],
    alias: 'widget.templateconfigViewWindow',
    xtype: "templateconfigViewWindow",
    width: 400,
    items: [{
        defaults: {readOnly: true},
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
    }]
});