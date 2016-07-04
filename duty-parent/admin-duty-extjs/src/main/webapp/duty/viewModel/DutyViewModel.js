/**
 * 部门视图模型
 *
 * @author zangyanming <br/>
 *         date:2016-3-10
 * @version 1.0.0
 */
Ext.define('kalix.admin.duty.viewModel.DutyViewModel', {
    extend: 'Ext.app.ViewModel',
    requires: [
        'kalix.admin.duty.store.DutyStore'
    ],
    alias: 'viewmodel.dutyViewModel',
    data: {
        addTitle: '新增职位',
        editTitle: '编辑职位',
        url: CONFIG.restRoot + '/camel/rest/dutys',
        rec:null
    }
});