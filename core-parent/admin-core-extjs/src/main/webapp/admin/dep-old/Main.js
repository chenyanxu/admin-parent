/**
 * 部门组件
 *
 * @author majian <br/>
 *         date:2015-7-21
 * @version 1.0.0
 */
Ext.define('kalix.admin.dep.Main', {
    extend: 'Ext.panel.Panel',
    requires: [
        'kalix.admin.dep.viewModel.DepViewModel',
        'kalix.admin.dep.controller.DepController'
    ],
    xtype: 'depPanel',
    controller: 'depController',
    viewModel: {
        type: 'depViewModel'
    },
    items: [],
    initComponent: function () {
        var depController = this.getController("depController");

        this.items[0] = depController.onInitPanel();

        this.callParent(arguments);
    }
});