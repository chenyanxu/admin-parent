/**
 * 字典数据仓库
 *
 * @author majian <br/>
 *         date:2015-7-9
 * @version 1.0.0
 */
Ext.define('kalix.admin.adminDict.store.AdminDictStore', {
    extend: 'kalix.store.BaseStore',
    model: 'kalix.dict.model.DictModel',
    alias: 'store.adminDictStore',
    xtype: 'adminDictStore',
    storeId: 'adminDictStore',
    proxyUrl: CONFIG.restRoot + '/camel/rest/dicts'
});