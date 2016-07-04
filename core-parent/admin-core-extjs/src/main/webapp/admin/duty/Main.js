/**
 * 部门组件
 *
 * @author zangyanming <br/>
 *         date:2016-3-10
 * @version 1.0.0
 */
Ext.define('kalix.admin.duty.Main', {
    extend: 'kalix.view.components.common.AutoHPanel',
    requires: [
        'kalix.admin.duty.viewModel.DutyNoAreaViewModel',
        'kalix.admin.duty.controller.DutyNoAreaController',
        'kalix.admin.org.view.OrgNoAreaTreeList',
        'kalix.admin.depNoArea.view.DepNoAreaTreeList',
        'kalix.admin.duty.view.DutyNoAreaGrid'
    ],
    xtype: 'dutyNoAreaPanel',
    controller: 'dutyNoAreaController',
    viewModel: {
        type: 'dutyNoAreaViewModel'
    },
    items: [
        {
            xtype: 'orgNoAreaTreeList',
            flex: 1,
            listeners: {
                itemClick: 'onOrgClick'
            }
        },
        {
            xtype: 'depNoAreaTreeList',
            flex: 1,
            listeners: {
                itemClick: 'onDepClick'
            }
        },
        {
            xtype: 'dutyNoAreaGridPanel',
            title: '职位列表',
            flex: 2
        }
    ]
});