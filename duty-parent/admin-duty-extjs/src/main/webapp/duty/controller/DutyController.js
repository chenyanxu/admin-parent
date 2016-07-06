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
    /**
     * 机构单击
     * @param view
     * @param record
     * @param item
     * @param index
     * @param e
     */
    onOrgClick: function (view, record, item, index, e) {
        var DutyTreeList = view.findParentByType('panel').findParentByType('panel').items.getAt(1).items.getAt(0);
        var store = DutyTreeList.getStore();
        store.setProxy({
            type: 'ajax',
            url: CONFIG.restRoot + '/camel/rest/dutys/org/' + record.data.id //
        });
        store.reload();
    }
});