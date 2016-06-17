/**
 * 角色视图模型
 *
 * @author majian <br/>
 *         date:2015-7-10
 * @version 1.0.0
 */
Ext.define('kalix.admin.role.viewModel.RoleViewModel', {
    extend: 'Ext.app.ViewModel',
    alias: 'viewmodel.roleViewModel',
    data: {
        rec: null,
        validation: {},  //验证错误信息
        icon: '',
        title: '',
        view_operation: false,
        view_title: '查看角色',
        add_title: '添加角色',
        edit_title: '修改角色',
        add_image_path: CONFIG.restRoot + '/admin/resources/images/user_add.png',
        view_image_path: CONFIG.restRoot + '/admin/resources/images/user_view.png',
        edit_image_path: CONFIG.restRoot + '/admin/resources/images/user_edit.png',
        url: CONFIG.restRoot + '/camel/rest/roles',
        authorizationUrl: CONFIG.restRoot + '/camel/rest/roles/authorizations'
    }
});