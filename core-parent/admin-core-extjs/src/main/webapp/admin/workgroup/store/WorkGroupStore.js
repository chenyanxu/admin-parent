/**
 * 工作组数据仓库
 *
 * @author majian <br/>
 *         date:2015-7-24
 * @version 1.0.0
 */
Ext.define('kalix.admin.workgroup.store.WorkGroupStore', {
    extend: 'kalix.store.BaseStore',
    model: 'kalix.admin.workgroup.model.WorkGroupModel',
    alias: 'store.workgroupStore',
    xtype: 'workgroupStore',
    storeId: "workgroupStore",
    proxyUrl: CONFIG.restRoot + '/camel/rest/workgroups'
});