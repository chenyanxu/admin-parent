/**
 * 应用首页
 *
 * @author
 * @version 1.0.0
 */
Ext.define('kalix.app.application.Main', {
    extend: 'kalix.container.BaseContainer',
    requires: [
        'kalix.app.application.view.ApplicationGrid',
        'kalix.app.application.view.ApplicationSearchForm',
        'kalix.app.application.viewModel.ApplicationViewModel'
    ],
    storeId: 'applicationStore',
    viewModel: 'applicationViewModel',
    items: [
        {
            title: '应用查询',
            xtype: 'applicationSearchForm'
        }, {
            xtype: 'applicationGridPanel',
            id: 'applicationGridPanel',
            title: '应用列表',
            iconCls: 'iconfont icon-application-management',
            margin: 10
        }
    ]
});
