/**
 * 机构视图模型
 *
 * @author zangyanming <br/>
 *         date:2016-3-10
 * @version 1.0.0
 */
Ext.define('kalix.admin.orgNoArea.viewModel.OrgNoAreaViewModel', {
    extend: 'Ext.app.ViewModel',
    alias: 'viewmodel.orgNoAreaViewModel',
    xtype:'orgNoAreaViewModel',
    requires: [
        'kalix.admin.orgNoArea.store.OrgNoAreaStore'
    ],
    data: {
        addTitle: '新增机构',
        editTitle: '编辑机构',
        url: CONFIG.restRoot + '/camel/rest/orgs',
        rec:null
    }
});