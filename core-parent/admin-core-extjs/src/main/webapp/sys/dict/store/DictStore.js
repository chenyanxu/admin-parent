/**
 * 字典数据仓库
 *
 * @author majian <br/>
 *         date:2015-7-9
 * @version 1.0.0
 */
Ext.define('kalix.sys.dict.store.DictStore', {
    extend: 'kalix.store.BaseStore',
    model: 'kalix.sys.dict.model.DictModel',
    alias: 'store.dictStore',
    xtype: 'dictStore',
    storeId: "dictStore",
    proxyUrl: CONFIG.restRoot + '/camel/rest/dicts'
});