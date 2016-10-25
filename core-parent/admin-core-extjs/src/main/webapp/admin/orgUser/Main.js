/**
 * duty main page
 * @author chenyanxu
 */
Ext.define('kalix.admin.orgUser.Main', {
    extend: 'kalix.view.components.common.AutoHPanel',
    requires: [
        'kalix.admin.orgUser.controller.OrgUserController',
        'kalix.admin.org.view.OrgTreeList',
        'kalix.admin.orgUser.view.OrgUserGrid',
        'kalix.container.BaseTreeContainer'
    ],
    xtype: 'orgUserPanel',
    controller: 'orgUserController',
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
            xtype: 'orgUserGridPanel',
            title: '用户列表',
            flex: 1
        }
    ]
});