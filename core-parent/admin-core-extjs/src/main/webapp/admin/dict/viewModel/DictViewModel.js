/**
 * 字典视图模型
 *
 * @author majian <br/>
 *         date:2015-7-9
 * @version 1.0.0
 */
Ext.define('kalix.admin.dict.viewModel.DictViewModel', {
    extend: 'Ext.app.ViewModel',
    alias: 'viewmodel.dictViewModel',
    data: {
        rec: null,
        validation: {},  //验证错误信息
        icon: '',
        title: '',
        view_operation: false,
        view_title: '查看字典',
        add_title: '添加字典',
        edit_title: '修改字典',
        add_image_path: CONFIG.restRoot + '/admin/resources/images/dict_add.png',
        view_image_path: CONFIG.restRoot + '/admin/resources/images/dict_view.png',
        edit_image_path: CONFIG.restRoot + '/admin/resources/images/dict_edit.png',
    }
});