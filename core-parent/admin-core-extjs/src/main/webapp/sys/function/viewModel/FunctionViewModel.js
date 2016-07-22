/**
 * 功能视图模型
 *
 * @author majian <br/>
 *         date:2015-7-21
 * @version 1.0.0
 */
Ext.define('kalix.admin.function.viewModel.FunctionViewModel', {
    extend: 'Ext.app.ViewModel',
    requires: [
        'kalix.admin.function.store.FunctionStore'
    ],
    alias: 'viewmodel.functionViewModel',
    data: {
        addTitle: '新增功能',
        editTitle: '编辑功能',
        url: CONFIG.restRoot + '/camel/rest/functions'
    }
});