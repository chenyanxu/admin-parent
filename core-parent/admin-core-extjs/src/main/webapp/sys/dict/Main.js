/**
 * 用户组件
 *
 * @author majian <br/>
 *         date:2015-6-18
 * @version 1.0.0
 */
Ext.define('kalix.sys.dict.Main', {
    extend: 'kalix.container.BaseContainer',
    requires: [
        'kalix.sys.dict.view.DictGrid',
        'kalix.sys.dict.view.DictSearchForm'
    ],
    storeId: 'dictStore',
    items: [
        {
            title: '字典查询',
            xtype: 'dictSearchForm'
        }, {
            xtype: 'dictGridPanel',
            id: 'dictGridPanel',
            title: '字典列表',
            iconCls: 'x-fa fa-table',
            margin: 10
        }
    ]
});