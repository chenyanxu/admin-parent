/**
 * 部门视图模型
 *
 * @author zangyanming <br/>
 *         date:2016-3-10
 * @version 1.0.0
 */
Ext.define('kalix.admin.depNoArea.viewModel.DepNoAreaViewModel', {
    extend: 'Ext.app.ViewModel',
    requires: [
        'kalix.admin.depNoArea.store.DepNoAreaStore'
    ],
    alias: 'viewmodel.depNoAreaViewModel',
    data: {
        addTitle: '新增部门',
        editTitle: '编辑部门',
        url: CONFIG.restRoot + '/camel/rest/deps',
        rec:null
    }
});