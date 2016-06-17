/**
 * 部门数据仓库
 *
 * @author majian <br/>
 *         date:2015-7-23
 * @version 1.0.0
 */
Ext.define('kalix.admin.dep.store.DepStore', {
    extend: 'Ext.data.TreeStore',
    alias: 'store.depStore',
    xtype: 'depStore',
    storeId: 'depStore',
    //model: 'kalix.admin.dep.model.DepModel',
    autoLoad: true
});