/**
 * 插件查看表单
 *
 * @author
 * @version 1.0.0
 */

Ext.define('kalix.admin.plugin.view.PluginViewWindow', {
    extend: 'kalix.view.components.common.BaseWindow',
    requires: [
        'kalix.admin.user.store.UserStore'
    ],
    alias: 'widget.pluginViewWindow',
    xtype: "pluginViewWindow",
    width: 400,
    items: [{
        defaults: {readOnly: true},
        xtype: 'baseForm',
        items: [{
            fieldLabel: '名称',
            bind: {
                value: '{rec.name}'
            }
        },
            {
                fieldLabel: '代码',
                bind: {
                    value: '{rec.code}'
                }
            }, {
                fieldLabel: '备注',
                xtype: 'textarea',
                bind: {
                    value: '{rec.remark}'
                }
            }]
    }]
});