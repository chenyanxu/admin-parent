/**
 * 用户模型
 *
 * @author
 * @version 1.0.0
 */

Ext.define('kalix.admin.user.viewModel.UserViewModel', {
    extend: 'Ext.app.ViewModel',
    alias: 'viewmodel.userViewModel',
    data: {
        rec: null,
        validation: {},  //验证错误信息
        title: '',
        view_operation: false,
        view_title: '查看用户',
        add_title: '添加用户',
        edit_title: '修改用户',
        key_title: '重置密码',
        add_image_path: CONFIG.restRoot + '/admin/resources/images/user_add.png',
        view_image_path: CONFIG.restRoot + '/admin/resources/images/user_view.png',
        edit_image_path: CONFIG.restRoot + '/admin/resources/images/user_edit.png',
        key_image_path: CONFIG.restRoot + '/admin/resources/images/user_key.png'
    }
});