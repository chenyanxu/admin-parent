/**
 * 机构模块控制器
 *
 * @author majian <br/>
 *         date:2015-7-21
 * @version 1.0.0
 */
Ext.define('kalix.admin.org.controller.OrgController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.orgController',
    requires: [
        'kalix.admin.area.view.AreaTreeList',
        'kalix.admin.org.view.OrgTreeList',
        'kalix.admin.org.view.OrgGrid'
    ],
    /**
     * 初始化面板.
     * @returns {Ext.panel.Panel}
     */
    onInitPanel: function () {
        var panel = Ext.create('Ext.panel.Panel', {
            border: false,
            layout: 'hbox',
            autoScroll: true,
            itemId: 'mainPanel',
            items: [
                {xtype:'container', padding:10, flex: 1, items:[this.onInitAreaTreeList()]},
                {xtype:'container', flex: 3, padding:'10 10 10 0',items:[this.onInitDataGrid()]}]
        })

        return panel;
    },
    /**
     * 区域单击
     */
    onAreaClick: function (view, record, item, index, e) {

        var grid = Ext.ComponentQuery.query('orgGridPanel')[0];
        grid.areaId = record.data.id;
        grid.areaName = record.data.name;
        var store = grid.getStore();
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
        Ext.ComponentQuery.query('orgPanel')[0].down('#mainPanel>#orgAreaTreeList').getStore().reload();
    },
    /**
     * 区域展开
     * @constructor
     */
    onAreaAxpandAll: function () {
        Ext.ComponentQuery.query('orgPanel')[0].down('#mainPanel>#orgAreaTreeList').expandAll(function () {
        });
    },
    /**
     * 区域收起
     * @constructor
     */
    onAreaCollapseAll: function () {
        Ext.ComponentQuery.query('orgPanel')[0].down('#mainPanel>#orgAreaTreeList').collapseAll(function () {
        });
    },
    /**
     * 初始化区域列表.
     * @returns {Ext.panel.Panel}
     */
    onInitAreaTreeList: function () {
        var areaListPanel = Ext.create('kalix.admin.area.view.AreaTreeList', {
            store: Ext.create('kalix.admin.area.store.AreaStore'),
            itemId: 'orgAreaTreeList',
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
                    handler: this.onAreaAxpandAll
                },
                {
                    tooltip: '收起', icon: 'admin/resources/images/arrow_up.png',
                    handler: this.onAreaCollapseAll
                }]
        });
        return areaListPanel;
    },
    /**
     * 初始化数据表格.
     * @returns {Ext.panel.Panel}
     */
    onInitDataGrid: function () {
        var dataGird = Ext.create('kalix.admin.org.view.OrgGrid', {
            store: Ext.create('kalix.admin.org.store.OrgStore')
        });
        return dataGird;
    }
});