/**
 * 插件首页
 *
 * @author
 * @version 1.0.0
 */
Ext.define('kalix.app.plugin.Main', {
    extend: 'kalix.container.BaseContainer',
    requires: [
        'kalix.app.plugin.view.PluginGrid',
        'kalix.app.plugin.view.PluginSearchForm',
        'kalix.app.plugin.viewModel.PluginViewModel'
    ],
    storeId: 'pluginStore',
    viewModel: 'pluginViewModel',
    items: [
        {
            title: '插件查询',
            iconCls: 'x-fa fa-search',
            xtype: 'pluginSearchForm'
        }, {
            xtype: 'pluginGridPanel',
            id: 'pluginGridPanel',
            title: '插件列表',
            iconCls: 'x-fa fa-wrench',
            margin: 10
        }
    ]
});
