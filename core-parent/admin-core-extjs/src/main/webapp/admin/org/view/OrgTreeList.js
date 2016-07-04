/**
 * 机构列表
 * @author zangyanming <br/>
 *         date:2016-3-10
 * @version 1.0.0
 */
Ext.define('kalix.admin.org.view.OrgTreeList', {
    extend: 'Ext.tree.Panel',
    requires: [
        'kalix.admin.org.viewModel.OrgViewModel',
        'kalix.admin.org.controller.OrgGridController',
        'kalix.admin.org.store.OrgStore'
    ],
    alias: 'widget.orgTreeList',
    xtype: 'orgTreeList',
    controller: 'orgGridController',
    viewModel: {
        type: 'orgViewModel'
    },
    store: {
        type: 'orgStore',
        proxy:{
            type:'ajax',
            url: CONFIG.restRoot + '/camel/rest/orgs/'
        }
    },
    //store:'orgStore',
    autoLoad:true,
    collapsible: true,
    autoScroll: true,
    rootVisible: false,
    title: '机构列表',
    iconCls: 'iconfont icon-organization-management',
    tbar: [
        {
            tooltip: '刷新',
            iconCls:'iconfont icon-refresh',
            handler: 'onRefersh'
        },
        {
            tooltip: '展开',
            iconCls: 'iconfont icon-tree-expand',
            handler: 'onOrgExpandAll'
        },
        {
            tooltip: '收起',
            iconCls: 'iconfont icon-tree-collapse',
            handler: 'onOrgCollapseAll'
        }]
});