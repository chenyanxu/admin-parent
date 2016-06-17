/**
 * 区域列表
 * @author majian <br/>
 *         date:2015-7-23
 * @version 1.0.0
 */
Ext.define('kalix.admin.area.view.AreaTreeList', {
    extend: 'Ext.tree.Panel',
    requires: [
        'kalix.admin.area.viewModel.AreaViewModel',
        'kalix.admin.area.controller.AreaGridController'
    ],
    alias: 'widget.areaTreeList',
    xtype: 'areaTreeList',
    controller: 'orgGridController',
    viewModel: {
        type: 'orgViewModel'
    },
    collapsible: true,
    autoScroll: true,
    /*rootProperty:{
     id:'-1',
     name:'根机构'
     },*/
    rootVisible: false
});