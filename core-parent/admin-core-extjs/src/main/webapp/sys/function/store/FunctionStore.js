/**
 * 功能数据仓库
 *
 * @author majian <br/>
 *         date:2015-7-31
 * @version 1.0.0
 */
Ext.define('kalix.admin.function.store.FunctionStore', {
    extend: 'Ext.data.TreeStore',
    /*model: 'kalix.admin.function.model.FunctionModel',
    require:'kalix.admin.function.model.FunctionModel',*/
    alias: 'store.functionStore',
    xtype: 'functionStore',
    storeId: 'functionStore'

});