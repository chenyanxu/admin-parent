/**
 * 字典数据仓库
 *
 * @author chenyanxu
 */
Ext.define('kalix.admin.adminDict.store.AdminDictNoPageStore', {
    extend: 'kalix.store.BaseStore',
    model: 'kalix.dict.model.DictModel',
    alias: 'store.adminDictNoPageStore',
    xtype: 'adminDictNoPageStore',
    storeId: 'adminDictNoPageStore',
    pageSize:0,
    singleton: true,
    proxyUrl: CONFIG.restRoot + '/camel/rest/dicts'
});