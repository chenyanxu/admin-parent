/**
 * 机构列表
 * @author majian <br/>
 *         date:2015-7-23
 * @version 1.0.0
 */
Ext.define('kalix.admin.org.view.OrgTreeList', {
    extend: 'Ext.tree.Panel',
    requires: [
        'kalix.admin.org.viewModel.OrgViewModel',
        'kalix.admin.org.controller.OrgGridController'
    ],
    alias: 'widget.orgTreeList',
    xtype: 'orgTreeList',
    controller: 'orgGridController',
    viewModel: {
        type: 'orgViewModel'
    },
    constructor:function(){
        this.callParent(arguments);
        this.store.on('load',function(target,records, successful, operation, eOpts){
            var grid=this.findParentByType('panel').items.getAt(2).items.getAt(0);
            if(grid){
                grid.store.proxy.url = CONFIG.restRoot + '/camel/rest/deps/org/-1';
                grid.store.load();
            }
        },this);
    },
    collapsible: true,
    autoScroll: true,
    /*rootProperty:{
     id:'-1',
     name:'根机构'
     },*/
    rootVisible: false
});