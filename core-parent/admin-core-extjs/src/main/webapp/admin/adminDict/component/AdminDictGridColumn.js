/**
 * @author chenyanxu
 */
Ext.define('kalix.admin.adminDict.component.AdminDictGridColumn', {
    extend: 'kalix.dict.component.DictGridColumn',
    alias: 'widget.adminDictGridColumn',
    xtype: 'adminDictGridColumn',
    storeClass:'kalix.admin.adminDict.store.AdminDictCacheStore'
});