/**
 * 功能组件
 *
 * @author majian <br/>
 *         date:2015-7-31
 * @version 1.0.0
 */
Ext.define('kalix.sys.function.view.Function', {
    extend: 'Ext.panel.Panel',
    requires: [
        'kalix.sys.function.viewModel.FunctionViewModel',
        'kalix.sys.function.controller.FunctionController'
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