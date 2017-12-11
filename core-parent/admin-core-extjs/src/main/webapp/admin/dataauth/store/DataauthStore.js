/**
 * 角色数据仓库
 *
 * @author majian <br/>
 *         date:2015-7-10
 * @version 1.0.0
 */
Ext.define('kalix.admin.dataauth.store.DataauthStore', {
    extend: 'kalix.store.BaseStore',
    model: 'kalix.admin.dataauth.model.DataauthModel',
    alias: 'store.dataauthStore',
    xtype: 'dataauthStore',
    storeId: 'dataauthStore',
    proxyUrl: CONFIG.restRoot + '/camel/rest/dataauths'
});