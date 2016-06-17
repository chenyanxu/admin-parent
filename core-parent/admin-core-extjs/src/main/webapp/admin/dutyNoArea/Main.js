/**
 * 部门组件
 *
 * @author zangyanming <br/>
 *         date:2016-3-10
 * @version 1.0.0
 */
Ext.define('kalix.admin.dutyNoArea.Main', {
    extend: 'Ext.panel.Panel',
    requires: [
        'kalix.admin.dutyNoArea.viewModel.DutyNoAreaViewModel',
        'kalix.admin.dutyNoArea.controller.DutyNoAreaController',
        'kalix.admin.orgNoArea.view.OrgNoAreaTreeList',
        'kalix.admin.depNoArea.view.DepNoAreaTreeList',
        'kalix.admin.dutyNoArea.view.DutyNoAreaGrid'
    ],
    xtype: 'dutyNoAreaPanel',
    controller: 'dutyNoAreaController',
    viewModel: {
        type: 'dutyNoAreaViewModel'
    },
    layout: {
        type: 'hbox',
        align: 'stretch'
    },
    renderTo: document.body,
    items: [
        {
            xtype: 'orgNoAreaTreeList',
            region : 'west',
            flex: 1,
            listeners: {
                itemClick: 'onOrgClick'
            }
        },
        {
            xtype: 'depNoAreaTreeList',
            region : 'center',
            flex: 1,
            listeners: {
                itemClick: 'onDepClick'
            }
        },
        {
            xtype: 'dutyNoAreaGridPanel',
            region : 'east',
            title: '职位列表',
            flex: 2//,
            //store: Ext.create('kalix.admin.dutyNoArea.store.DutyNoAreaStore')
        }
    ]
});