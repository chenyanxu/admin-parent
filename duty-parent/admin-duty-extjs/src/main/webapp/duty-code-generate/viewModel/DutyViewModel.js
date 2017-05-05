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
        view_operation: false
    }
});