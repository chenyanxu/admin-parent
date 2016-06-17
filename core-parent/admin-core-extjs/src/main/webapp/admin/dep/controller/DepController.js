/**
 * 部门模块控制器
 *
 * @author majian <br/>
 *         date:2015-7-23
 * @version 1.0.0
 */
Ext.define('kalix.admin.dep.controller.DepController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.depController',
    requires: [
        'kalix.admin.area.view.AreaTreeList',
        'kalix.admin.org.view.OrgTreeList',
        'kalix.admin.dep.view.DepGrid'
    ],
    /**
     * 初始化面板.
     * @returns {Ext.panel.Panel}
     */
    onInitPanel: function () {

        var panel = Ext.create('Ext.panel.Panel', {
            layout: 'hbox',
            itemId: 'mainPanel',
            autoScroll: true,
            items: [
                {xtype: 'container', padding: 10, flex: 1, items: [this.onInitAreaTreeList()]},
                {xtype: 'container', padding:'10 10 10 0', flex: 1, items: [this.onInitOrgTreeList()]},
                {xtype: 'container', padding:'10 10 10 0',flex: 3, items: [this.onInitDataGrid()]}
            ]
        });

        return panel;
    },
    /**
     * 机构单击
     * @param view
     * @param record
     * @param item
     * @param index
     * @param e
     */
    onOrgClick: function (view, record, item, index, e) {

        var grid = Ext.ComponentQuery.query('depGridPanel')[0];
        grid.orgId = record.data.id;
        grid.orgName = record.data.name;
        var store = grid.getStore();
        store.setProxy({
            type: 'ajax',
            url: CONFIG.restRoot + '/camel/rest/deps/org/' + record.data.id
        });
        store.reload();
    },
    /**
     * 机构刷新
     */
    onOrgRefersh: function () {
        Ext.ComponentQuery.query('depPanel')[0].down('#mainPanel>#depOrgTreeList').getStore().reload();
    },
    /**
     * 机构展开
     */
    onOrgExpandAll: function () {
        Ext.ComponentQuery.query('depPanel')[0].down('#mainPanel>#depOrgTreeList').expandAll(function () {
        });
    },
    /**
     * 机构收起
     */
    onOrgCollapseAll: function () {
        Ext.ComponentQuery.query('depPanel')[0].down('#mainPanel>#depOrgTreeList').collapseAll(function () {
        });
    },
    /**
     * 区域单击
     */
    onAreaClick: function (view, record, item, index, e) {
        //var OrgTreeList = Ext.ComponentQuery.query('depPanel')[0].down('#mainPanel>#depOrgTreeList');

        var OrgTreeList=view.findParentByType('panel').findParentByType('panel').items.getAt(1).items.getAt(0);

        var store = OrgTreeList.getStore();
        store.setProxy({
            type: 'ajax',
            url: CONFIG.restRoot + '/camel/rest/orgs/area/' + record.data.id
        });
        store.reload();
    },
    /**
     * 区域刷新
     */
    onAreaRefersh: function () {
        Ext.ComponentQuery.query('depPanel')[0].down('#mainPanel>#depAreaTreeList').getStore().reload();
    },
    /**
     * 区域展开
     * @constructor
     */
    onAreaExpandAll: function () {
        Ext.ComponentQuery.query('depPanel')[0].down('#mainPanel>#depAreaTreeList').expandAll(function () {
        });
    },
    /**
     * 区域收起
     * @constructor
     */
    onAreaCollapseAll: function () {
        Ext.ComponentQuery.query('depPanel')[0].down('#mainPanel>#depAreaTreeList').collapseAll(function () {
        });
    },
    /**
     * 初始化区域列表.
     * @returns {Ext.panel.Panel}
     */
    onInitAreaTreeList: function () {
        var areaTreeListPanel = Ext.create('kalix.admin.area.view.AreaTreeList', {
            store: Ext.create('kalix.admin.area.store.AreaStore'),
            region: 'west',
            itemId: 'depAreaTreeList',
            title: '区域列表',
            iconCls: 'x-fa fa-home',
            listeners: {
                itemClick: this.onAreaClick
            },
            tbar: [
                {
                    tooltip: '刷新', icon: 'admin/resources/images/arrow_refresh.png',
                    handler: this.onAreaRefersh
                },
                {
                    tooltip: '展开', icon: 'admin/resources/images/arrow_down.png',
                    handler: this.onAreaExpandAll
                },
                {
                    tooltip: '收起', icon: 'admin/resources/images/arrow_up.png',
                    handler: this.onAreaCollapseAll
                }]
        });
        return areaTreeListPanel;
    },
    /**
     * 初始化机构列表.
     * @returns {Ext.panel.Panel}
     */
    onInitOrgTreeList: function () {
        var orgTreeListPanel = Ext.create('kalix.admin.org.view.OrgTreeList', {
            store: Ext.create('kalix.admin.org.store.OrgStore'),
            itemId: 'depOrgTreeList',
            region: 'west',
            title: '机构列表',
            iconCls: 'x-fa fa-building',
            listeners: {
                itemClick: this.onOrgClick
            },
            tbar: [
                {
                    tooltip: '刷新', icon: 'admin/resources/images/arrow_refresh.png',
                    handler: this.onOrgRefersh
                },
                {
                    tooltip: '展开', icon: 'admin/resources/images/arrow_down.png',
                    handler: this.onOrgExpandAll
                },
                {
                    tooltip: '收起', icon: 'admin/resources/images/arrow_up.png',
                    handler: this.onOrgCollapseAll
                }]
        });
        return orgTreeListPanel;
    },
    /**
     * 初始化数据表格.
     * @returns {Ext.panel.Panel}
     */
    onInitDataGrid: function () {
        //Ext.QuickTips.init();
        var dataGird = Ext.create('kalix.admin.dep.view.DepGrid', {
            store: Ext.create('kalix.admin.dep.store.DepStore')
        });
        return dataGird;
    }
});