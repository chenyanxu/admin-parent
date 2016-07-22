/**
 *
 * Dict extJs use new base dict components
 * @author chenyanxu
 */
Ext.define('kalix.admin.adminDict.Main', {
    extend: 'kalix.dict.Main',
    requires: [
        'kalix.dict.view.DictGrid',
        'kalix.dict.view.DictSearchForm',
        'kalix.admin.adminDict.store.AdminDictStore'
    ]
});