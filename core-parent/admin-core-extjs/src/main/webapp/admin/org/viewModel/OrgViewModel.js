/**
 * 机构视图模型
 *
 * @author majian <br/>
 *         date:2015-7-21
 * @version 1.0.0
 */
Ext.define('kalix.admin.org.viewModel.OrgViewModel', {
    extend: 'Ext.app.ViewModel',
    requires: [
        'kalix.admin.org.store.OrgStore'
    ],
    alias: 'viewmodel.orgViewModel',
    data: {
        addTitle: '新增机构',
        editTitle: '编辑机构',
        url: CONFIG.restRoot + '/camel/rest/orgs'
    }
});