/**
 * 职位模型
 *
 * @author
 * @version 1.0.0
 */

Ext.define('kalix.app.duty.viewModel.DutyViewModel', {
    extend: 'Ext.app.ViewModel',
    alias: 'viewmodel.dutyViewModel',
    data: {
        rec: null,
        validation: {},  //验证错误信息
        icon: '',
        title: '',
        view_operation: false,
        view_title: '查看职位',
        add_title: '添加职位',
        edit_title: '修改职位',
        add_image_path: '/kalix/app/duty/resources/images/duty_add.png',
        view_image_path: '/kalix/app/duty/resources/images/duty_view.png',
        edit_image_path: '/kalix/app/duty/resources/images/duty_edit.png',
    }
});