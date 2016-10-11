/**
 * 职位数据仓库
 *
 * @author
 * @version 1.0.0
 */
Ext.define('kalix.app.duty.store.DutyStore', {
    extend: 'kalix.store.BaseStore',
    model: 'kalix.app.duty.model.DutyModel',
    alias: 'store.dutyStore',
    xtype: 'dutyStore',
    storeId: "dutyStore",
    proxyUrl: CONFIG.restRoot + '/camel/rest/dutys'
});