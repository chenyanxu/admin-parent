/**
 * 插件查询表单
 * @author
 * @version 1.0.0
 */
Ext.define('kalix.app.plugin.view.PluginSearchForm', {
    extend: 'kalix.view.components.common.BaseSearchForm',
    alias: 'widget.pluginSearchForm',
    xtype: 'pluginSearchForm',
    storeId: 'pluginStore',
    items: [
        {
            xtype: 'textfield',
            fieldLabel: '名称',
            labelAlign: 'right',
            labelWidth: 60,
            width: 200,
            name: 'name'
        }]
});
