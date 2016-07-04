/**
 * 部门列表
 * @author zangyanming <br/>
 *         date:2016-3-10
 * @version 1.0.0
 */
Ext.define('kalix.admin.depNoArea.view.DepNoAreaTreeList', {
    extend: 'Ext.tree.Panel',
    requires: [
        'kalix.admin.depNoArea.viewModel.DepNoAreaViewModel',
        'kalix.admin.depNoArea.controller.DepNoAreaGridController'
    ],
    alias: 'widget.depNoAreaTreeList',
    xtype: 'depNoAreaTreeList',
    controller: 'depNoAreaGridController',
    viewModel: {
        type: 'depNoAreaViewModel'
    },
    autoLoad:false,
    collapsible: true,
    autoScroll: true,
    rootVisible: false,

    store: Ext.create('kalix.admin.depNoArea.store.DepNoAreaStore'),
    title: '部门列表',
    iconCls: 'iconfont icon-department-management',
    tbar: [
        {
            tooltip: '刷新',
            iconCls:'iconfont icon-refresh',
            handler: 'onRefersh'
        },
        {
            tooltip: '展开',
            iconCls: 'iconfont icon-tree-expand',
            handler: 'onExpandAll'
        },
        {
            tooltip: '收起',
            iconCls: 'iconfont icon-tree-collapse',
            handler: 'onCollapseAll'
        }]
});