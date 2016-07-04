/**
 * 机构列表
 * @author zangyanming <br/>
 *         date:2016-3-10
 * @version 1.0.0
 */
Ext.define('kalix.admin.duty.view.DutyTreeList', {
    extend: 'Ext.tree.Panel',
    requires: [
        'kalix.admin.duty.viewModel.DutyViewModel',
        'kalix.admin.duty.controller.DutyGridController'
    ],
    alias: 'widget.dutyTreeList',
    xtype: 'dutyTreeList',
    controller: 'dutyGridController',
    viewModel: {
        type: 'dutyViewModel'
    },
    constructor:function(){
        this.callParent(arguments);
        this.store.on('load',function(target,records, successful, operation, eOpts){
            var grid=this.findParentByType('panel').items.getAt(1).items.getAt(0);
            if(grid){
                grid.store.proxy.url = CONFIG.restRoot + '/camel/rest/deps';
                grid.store.load();
            }
        },this);
    },
    collapsible: true,
    autoScroll: true,
    rootVisible: false
});