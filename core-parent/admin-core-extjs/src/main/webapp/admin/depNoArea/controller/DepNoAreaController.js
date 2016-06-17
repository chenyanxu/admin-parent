/**
 * 部门模块控制器
 *
 * @author zangyanming <br/>
 *         date:2016-3-10
 * @version 1.0.0
 */
Ext.define('kalix.admin.depNoArea.controller.DepNoAreaController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.depNoAreaController',
    requires: [
        'kalix.admin.orgNoArea.view.OrgNoAreaTreeList',
        'kalix.admin.depNoArea.view.DepNoAreaGrid'
    ],
    /**
     * 机构单击
     * @param view
     * @param record
     * @param item
     * @param index
     * @param e
     */
    onOrgClick: function (view, record, item, index, e) {
        var DepTreeList=view.findParentByType('panel').findParentByType('panel').items.getAt(1).items.getAt(0);
        var store = DepTreeList.getStore();
        store.setProxy({
            type: 'ajax',
            url: CONFIG.restRoot + '/camel/rest/org/' + record.data.id+'/depts'
        });
        store.load();
    }
});