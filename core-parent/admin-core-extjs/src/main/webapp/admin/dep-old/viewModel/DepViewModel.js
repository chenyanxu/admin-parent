/**
 * 部门视图模型
 *
 * @author majian <br/>
 *         date:2015-7-23
 * @version 1.0.0
 */
Ext.define('kalix.admin.dep.viewModel.DepViewModel', {
    extend: 'Ext.app.ViewModel',
    requires: [
        'kalix.admin.dep.store.DepStore'
    ],
    alias: 'viewmodel.depViewModel',
    data: {
        addTitle: '新增部门',
        editTitle: '编辑部门',
        url: CONFIG.restRoot + '/camel/rest/deps'
    }
});