/**
 * 插件新增和修改表单
 *
 * @author
 * @version 1.0.0
 */
Ext.define('kalix.app.plugin.view.PluginWindow', {
    extend: 'kalix.view.components.common.BaseWindow',
    requires: [
        'kalix.app.plugin.viewModel.PluginViewModel',
        'kalix.controller.BaseWindowController',
        'kalix.admin.user.store.UserStore'
    ],
    alias: 'widget.pluginWindow',
    viewModel: 'pluginViewModel',
    controller: {
        type: 'baseWindowController',
        storeId: 'pluginStore'
    },
    xtype: "pluginWindow",
    width: 400,
    items: [{
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