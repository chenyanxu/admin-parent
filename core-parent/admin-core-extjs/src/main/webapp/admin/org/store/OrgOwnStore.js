/**
 * Created by Administrator on 2016/8/4.
 */
Ext.define('kalix.admin.org.store.OrgOwnStore', {
    extend: 'kalix.store.BaseStore',
    model: 'kalix.admin.org.model.OrgModel',
    alias: 'store.orgOwnStore',
    xtype: 'orgOwnStore',
    storeId: 'orgOwnStore',
    pageSize:0,
    autoLoad:true,
    singleton: true,
    proxyUrl: CONFIG.restRoot + '/camel/rest/orgs/users/own'
});