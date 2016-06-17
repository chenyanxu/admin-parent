/**
 * 功能数据仓库
 *
 * @author majian <br/>
 *         date:2015-7-31
 * @version 1.0.0
 */
Ext.define('kalix.app.function.store.FunctionStore', {
    extend: 'Ext.data.TreeStore',
    /*model: 'kalix.app.function.model.FunctionModel',
    require:'kalix.app.function.model.FunctionModel',*/
    alias: 'store.functionStore',
    xtype: 'functionStore',
    storeId: 'functionStore'

});