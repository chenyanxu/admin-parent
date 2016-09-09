/**
 * 部门模块控制器
 *
 * @author zangyanming <br/>
 *         date:2016-3-10
 * @version 1.0.0
 */
Ext.define('kalix.admin.duty.controller.DutyController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.dutyController',
    onItemClick: function (view, record, item, index, e) {
        var dutyGrid=this.getView().items.getAt(1);
        var store =dutyGrid.store;

        store.proxy.url=CONFIG.restRoot +'/camel/rest/orgs/'+record.data.id+'/dutys';
        store.reload();
    }
});