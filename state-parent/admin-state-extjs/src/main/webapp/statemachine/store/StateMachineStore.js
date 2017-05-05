/**
 * 状态机数据仓库
 *
 * @author
 * @version 1.0.0
 */
Ext.define('kalix.admin.statemachine.store.StateMachineStore', {
    extend: 'kalix.store.BaseStore',
    model: 'kalix.admin.statemachine.model.StateMachineModel',
    alias: 'store.stateMachineStore',
    xtype: 'stateMachineStore',
    storeId: "stateMachineStore",
    proxyUrl: CONFIG.restRoot + '/camel/rest/statemachines'
});