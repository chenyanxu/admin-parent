/**
 * 用户组件
 *
 * @author majian
 *         date:2015-6-18
 * @version 1.0.0
 */
Ext.define('kalix.admin.audit.view.Audit', {
    extend: 'Ext.panel.Panel',
    requires: [
        'kalix.admin.audit.viewModel.AuditViewModel',
        'kalix.admin.audit.controller.AuditController'
    ],
    controller: 'auditController',
    viewModel: {
        type: 'auditViewModel'
    },
    items: [],
    initComponent: function () {
        var auditController = this.getController("auditController");

        this.items[0] = auditController.onInitPanel();
        /*var grid = Ext.getCmp("auditDataGrid");
         var store = grid.getStore();
         store.load({params:{start:0, limit:10}});
         grid.getView().refresh();*/
        this.callParent(arguments);
    }
});