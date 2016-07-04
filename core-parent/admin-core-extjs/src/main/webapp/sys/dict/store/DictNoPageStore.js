/**
 * 字典数据仓库
 *
 * @author majian <br/>
 *         date:2015-7-9
 * @version 1.0.0
 */
Ext.define('kalix.sys.dict.store.DictNoPageStore', {
    extend: 'kalix.store.BaseStore',
    model: 'kalix.sys.dict.model.DictModel',
    alias: 'store.dictNoPageStore',
    xtype: 'dictNoPageStore',
    storeId: "dictNoPageStore",
    pageSize:0,
    singleton: true,
    proxyUrl: CONFIG.restRoot + '/camel/rest/dicts/list'
});