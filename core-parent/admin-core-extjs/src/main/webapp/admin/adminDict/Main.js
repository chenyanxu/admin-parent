/**
 *
 * Dict extJs use new base dict components
 * @author chenyanxu
 */
Ext.define('kalix.admin.adminDict.Main', {
    extend: 'kalix.dict.Main',
    requires: [
        'kalix.admin.adminDict.store.AdminDictStore'
    ]
});