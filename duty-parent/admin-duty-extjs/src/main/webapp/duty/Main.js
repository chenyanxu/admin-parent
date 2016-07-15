/**
 * duty main page
 * @author chenyanxu
 */
Ext.define('kalix.admin.duty.Main', {
    extend: 'kalix.view.components.common.AutoHPanel',
    requires: [
        'kalix.admin.duty.controller.DutyController',
        'kalix.admin.org.view.OrgTreeList',
        'kalix.admin.duty.view.DutyGrid'
    ],
    xtype: 'dutyPanel',
    controller: 'dutyController',
    items: [
        {
            xtype: 'orgTreeList',
            flex: 1,
            listeners: {
                itemClick: 'onItemClick'
            }
        },
        {
            xtype: 'dutyGridPanel',
            title: '职位列表',
            flex: 3
        }
    ]
});