/**
 * @author chenyanxu
 */
Ext.define('kalix.admin.adminDict.component.AdminDictCombobox', {
    extend: 'kalix.dict.component.DictCombobox',
    alias: 'widget.adminDictCombobox',
    xtype: 'adminDictCombobox',
    storeClass:'kalix.admin.adminDict.store.AdminDictCacheStore'
});