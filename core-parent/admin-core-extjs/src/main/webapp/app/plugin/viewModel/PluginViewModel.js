/**
 * 插件模型
 *
 * @author
 * @version 1.0.0
 */

Ext.define('kalix.app.plugin.viewModel.PluginViewModel', {
    extend: 'Ext.app.ViewModel',
    alias: 'viewmodel.pluginViewModel',
    data: {
        rec: null,
        validation: {},  //验证错误信息
        icon: '',
        title: '',
        view_operation: false,
        view_title: '查看插件',
        add_title: '添加插件',
        edit_title: '修改插件',
        add_image_path: CONFIG.restRoot + '/app/resources/images/plugin_add.png',
        view_image_path: CONFIG.restRoot + '/app/resources/images/plugin.png',
        edit_image_path: CONFIG.restRoot + '/app/resources/images/plugin_edit.png',
    }
});