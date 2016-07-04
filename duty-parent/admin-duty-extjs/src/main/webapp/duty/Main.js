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
        'kalix.admin.duty.viewModel.DutyViewModel',
        'kalix.admin.duty.controller.DutyController',
        'kalix.admin.org.view.OrgTreeList',
        'kalix.admin.duty.view.DutyGrid'
    ],
    xtype: 'dutyPanel',
    controller: 'dutyController',
    viewModel: {
        type: 'dutyViewModel'
    },
    items: [
        {
            xtype: 'orgTreeList',
            flex: 1,
            listeners: {
                itemClick: 'onOrgClick'
            }
        },
        {
            xtype: 'dutyGridPanel',
            title: '职位列表',
            flex: 2
        }
    ]
});