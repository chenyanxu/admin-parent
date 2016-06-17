/**
 * 工作组视图模型
 *
 * @author majian <br/>
 *         date:2015-7-24
 * @version 1.0.0
 */
Ext.define('kalix.admin.workgroup.viewModel.WorkGroupViewModel', {
    extend: 'Ext.app.ViewModel',
    alias: 'viewmodel.workgroupViewModel',
    data: {
        rec: null,
        validation: {},  //验证错误信息
        icon: '',
        title: '',
        view_operation: false,
        view_title: '查看工作组',
        add_title: '添加工作组',
        edit_title: '修改工作组',
        add_image_path: CONFIG.restRoot + '/admin/resources/images/cup_add.png',
        view_image_path: CONFIG.restRoot + '/admin/resources/images/cup.png',
        edit_image_path: CONFIG.restRoot + '/admin/resources/images/cup_edit.png',
        url: CONFIG.restRoot + '/camel/rest/workGroups'
    }
});