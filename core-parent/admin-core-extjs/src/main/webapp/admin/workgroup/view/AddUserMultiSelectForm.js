/**
 * 添加用户组件
 *
 * @author majian <br/>
 *         date:2015-7-28
 * @version 1.0.0
 */
Ext.define('kalix.admin.workgroup.view.AddUserMultiSelectForm', {
    extend: 'Ext.container.Container',
    xtype: 'multi-selector',
    width: 300,
    //height: 300,
    requires: [
        'Ext.view.MultiSelector',
        'kalix.admin.user.model.UserModel'
    ],
    layout: 'fit',
    items: [{
        xtype: 'multiselector',
        title: 'Selected Dx',
        fieldName: 'mobile',
        viewConfig: {
            deferEmptyText: false,
            emptyText: 'No Dx selected'
        },
        search: {
            field: 'mobile',
            store: {
                model: 'kalix.admin.user.model.UserModel',
                sorters: 'mobile',
                autoLoad: true,
                proxy: {
                    type: 'ajax',
                    limitParam: null,
                    url: CONFIG.restRoot + '/camel/rest/users/all',
                    reader: {
                        type: "json",
                        root: "data",
                        totalProperty: 'totalCount'
                    }
                }
            }
        }
    }]
});