/**
 * 字典数据仓库
 *
 * @author chenyanxu
 */
Ext.define('kalix.admin.adminDict.store.AdminDictStore', {
    extend: 'kalix.store.BaseStore',
    model: 'kalix.dict.model.DictModel',
    alias: 'store.adminDictStore',
    xtype: 'adminDictStore',
    storeId: 'adminDictStore',
    proxyUrl: CONFIG.restRoot + '/camel/rest/admin/dicts'
});