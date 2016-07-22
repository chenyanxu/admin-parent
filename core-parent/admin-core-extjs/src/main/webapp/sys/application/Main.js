/**
 * 应用首页
 *
 * @author
 * @version 1.0.0
 */
Ext.define('kalix.admin.application.Main', {
    extend: 'kalix.container.BaseContainer',
    requires: [
        'kalix.admin.application.view.ApplicationGrid',
        'kalix.admin.application.view.ApplicationSearchForm'
    ],
    storeId: 'applicationStore',
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
