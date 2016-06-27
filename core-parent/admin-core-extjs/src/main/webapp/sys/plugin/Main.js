/**
 * 插件首页
 *
 * @author
 * @version 1.0.0
 */
Ext.define('kalix.sys.plugin.Main', {
    extend: 'kalix.container.BaseContainer',
    requires: [
        'kalix.sys.plugin.view.PluginGrid',
        'kalix.sys.plugin.view.PluginSearchForm',
        'kalix.sys.plugin.viewModel.PluginViewModel'
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
