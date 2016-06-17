/**
 * 区域组件
 *
 * @author majian <br/>
 *         date:2015-7-24
 * @version 1.0.0
 */
Ext.define('kalix.admin.area.Main', {
    extend: 'Ext.container.Container',
    requires: [
        //'kalix.admin.area.view.AreaViewModel',
        //'kalix.admin.area.controller.AreaController'
        'kalix.admin.area.view.AreaGrid',
        'kalix.admin.area.store.AreaStore'
    ],
    //controller: 'areaController',
    //viewModel: 'areaViewModel',
    items: [{
        xtype: 'areaGridPanel',
        title: '区域列表',
        iconCls: 'iconfont icon-area-management',
        margin: 10,
        manageHeight: true,
        rootVisible: false,
        store: Ext.create('kalix.admin.area.store.AreaStore')
    }]
});