/**
 * 应用首页
 *
 * @author
 * @version 1.0.0
 */
Ext.define('kalix.sys.application.Main', {
    extend: 'kalix.container.BaseContainer',
    requires: [
        'kalix.sys.application.view.ApplicationGrid',
        'kalix.sys.application.view.ApplicationSearchForm',
        'kalix.sys.application.viewModel.ApplicationViewModel'
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
