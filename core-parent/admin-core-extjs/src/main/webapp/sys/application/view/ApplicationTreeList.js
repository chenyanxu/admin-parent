/**
 * 应用列表
 * @author majian <br/>
 *         date:2015-7-23
 * @version 1.0.0
 */
Ext.define('kalix.admin.application.view.ApplicationTreeList', {
    extend: 'Ext.tree.Panel',
    requires: [
        'kalix.admin.application.controller.ApplicationGridController'
    ],
    alias: 'widget.applicationTreeList',
    xtype: 'applicationTreeList',
    controller: 'applicationGridController',
    collapsible: true,
    autoScroll: true,
    border: true,
    /*rootProperty:{
     id:'-1',
     name:'根机构'
     },*/
    rootVisible: false
});