/**
 * 应用模型
 *
 * @author
 * @version 1.0.0
 */

Ext.define('kalix.app.application.viewModel.ApplicationViewModel', {
    extend: 'Ext.app.ViewModel',
    alias: 'viewmodel.applicationViewModel',
    data: {
        rec: null,
        validation: {},  //验证错误信息
        icon: '',
        title: '',
        view_operation: false,
        view_title: '查看应用',
        add_title: '添加应用',
        edit_title: '修改应用',
        add_image_path: CONFIG.restRoot + '/app/resources/images/application_add.png',
        view_image_path: CONFIG.restRoot + '/app/resources/images/application.png',
        edit_image_path: CONFIG.restRoot + '/app/resources/images/application_edit.png',
    }
});