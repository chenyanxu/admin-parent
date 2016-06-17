/**
 * 部门视图模型
 *
 * @author zangyanming <br/>
 *         date:2016-3-10
 * @version 1.0.0
 */
Ext.define('kalix.admin.dutyNoArea.viewModel.DutyNoAreaViewModel', {
    extend: 'Ext.app.ViewModel',
    requires: [
        'kalix.admin.dutyNoArea.store.DutyNoAreaStore'
    ],
    alias: 'viewmodel.dutyNoAreaViewModel',
    data: {
        addTitle: '新增职位',
        editTitle: '编辑职位',
        url: CONFIG.restRoot + '/camel/rest/dutys',
        rec:null
    }
});