/**
 * 机构组件
 *
 * @author zangyanming <br/>
 *         date:2016-3-10
 * @version 1.0.0
 */
Ext.define('kalix.admin.org.Main', {
    extend: 'Ext.panel.Panel',
    requires: [
        'kalix.admin.org.viewModel.OrgViewModel',
        'kalix.admin.org.controller.OrgController'
    ],
    controller: 'orgController',
    xtype: 'orgPanel',
    viewModel: {
        type: 'orgViewModel'
    },
    layout: {
        type: 'hbox',
        align: 'stretch'
    },
    items: [
        {
            xtype: 'orgGridPanel',
            title: '机构列表',
            flex: 2,
            store: Ext.create('kalix.admin.org.store.OrgStore')
        }
    ]
});