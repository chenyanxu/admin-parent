/**
 * 用户组件
 *
 * @author majian <br/>
 *         date:2015-6-18
 * @version 1.0.0
 */
Ext.define('kalix.app.function.Main', {
    extend: 'Ext.panel.Panel',
    requires: [
        'kalix.app.function.viewModel.FunctionViewModel',
        'kalix.app.function.controller.FunctionController'
    ],
    controller: 'functionController',
    xtype: 'functionPanel',
    viewModel: {
        type: 'functionViewModel'
    },
    items: [],
    initComponent: function () {
        var functionController = this.getController("functionController");

        this.items[0] = functionController.onInitPanel();

        this.callParent(arguments);
    }
});
