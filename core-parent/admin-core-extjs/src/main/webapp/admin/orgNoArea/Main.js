/**
 * 机构组件
 *
 * @author zangyanming <br/>
 *         date:2016-3-10
 * @version 1.0.0
 */
Ext.define('kalix.admin.orgNoArea.Main', {
    extend: 'Ext.panel.Panel',
    requires: [
        'kalix.admin.orgNoArea.viewModel.OrgNoAreaViewModel',
        'kalix.admin.orgNoArea.controller.OrgNoAreaController'
    ],
    controller: 'orgNoAreaController',
    xtype: 'orgNoAreaPanel',
    viewModel: {
        type: 'orgNoAreaViewModel'
    },
    layout: {
        type: 'hbox',
        align: 'stretch'
    },
    items: [
        {
            xtype: 'orgNoAreaGridPanel',
            title: '机构列表',
            flex: 2,
            store: Ext.create('kalix.admin.orgNoArea.store.OrgNoAreaStore')
        }
    ]
});