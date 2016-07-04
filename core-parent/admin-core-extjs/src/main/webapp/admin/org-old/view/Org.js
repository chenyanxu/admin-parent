/**
 * 机构组件
 *
 * @author majian <br/>
 *         date:2015-7-21
 * @version 1.0.0
 */
Ext.define('kalix.admin.org.view.Org', {
    extend: 'Ext.panel.Panel',
    requires: [
        'kalix.admin.org.viewModel.OrgViewModel',
        'kalix.admin.org.controller.OrgController'
    ],
    controller: 'orgController',
    xtype: 'orgPanel',
    viewModel: {
        type: 'orgViewModel'
    },
    items: [],
    initComponent: function () {
        var OrgController = this.getController("orgController");

        this.items[0] = OrgController.onInitPanel();

        this.callParent(arguments);
    }
});