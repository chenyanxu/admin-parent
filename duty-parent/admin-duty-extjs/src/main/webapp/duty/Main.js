/**
 * duty main page
 * @author chenyanxu
 */
Ext.define('kalix.admin.duty.Main', {
    extend: 'kalix.view.components.common.AutoHPanel',
    requires: [
        'kalix.admin.duty.controller.DutyController',
        'kalix.admin.org.view.OrgTreeList',
        'kalix.admin.duty.view.DutyGrid',
        'kalix.container.BaseTreeContainer'
    ],
    xtype: 'dutyPanel',
    controller: 'dutyController',
    items: [
        {
            xtype:'baseTreeContainer',
            title:'机构列表',
            iconCls: 'iconfont icon-organization-management',
            width:400,
            childItemMargin:0,
            tree: {
                xtype: 'orgTreeList',
                title:'',
                iconCls:'',
                reference:'dutyOrgTreeList',
                listeners: {
                    itemClick: 'onItemClick'
                },
                tbar:null,
            }
        },
        {
            xtype: 'dutyGridPanel',
            title: '职位列表',
            flex: 1
        }
    ]
});