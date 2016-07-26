/**
 * 字典数据仓库
 *
 * @author chenyanxu
 */
Ext.define('kalix.admin.adminDict.store.AdminDictCacheStore', {
    extend: 'kalix.store.BaseStore',
    model: 'kalix.dict.model.DictModel',
    alias: 'store.adminDictCacheStore',
    xtype: 'adminDictCacheStore',
    storeId: 'adminDictCacheStore',
    pageSize:0,
    singleton: true,
    proxyUrl: CONFIG.restRoot + '/camel/rest/dicts/cache/list'
});