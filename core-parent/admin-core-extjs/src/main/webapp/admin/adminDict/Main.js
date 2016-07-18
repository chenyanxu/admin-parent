/**
 *
 * Dict extJs use new base dict components
 * @author
 */
Ext.define('kalix.admin.adminDict.Main', {
    extend: 'kalix.dict.Main',
    requires: [
        'kalix.dict.view.DictGrid',
        'kalix.dict.view.DictSearchForm',
        'kalix.admin.adminDict.store.AdminDictStore'
    ]
});