/**
 * 字典新增和修改表单
 *
 * @author
 * @version 1.0.0
 */
Ext.define('kalix.sys.dict.view.DictWindow', {
    extend: 'kalix.view.components.common.BaseWindow',
    requires: [
        'kalix.controller.BaseWindowController'
    ],
    alias: 'widget.dictWindow',
    controller: {
        type: 'baseWindowController',
        storeId: 'dictStore'
    },
    xtype: "dictWindow",
    width: 400,
    items: [{
        xtype: 'baseForm',
        items: [
            {
                fieldLabel: '标签名',
                allowBlank: false,
                bind: {
                    value: '{rec.label}'
                }
            },
            {
                fieldLabel: '数据值',
                xtype : 'numberfield',
                allowBlank: false,
                bind: {
                    value: '{rec.value}'
                }
            }, {
                fieldLabel: '类型',
                allowBlank: false,
                bind: {
                    value: '{rec.type}'
                }
            },
            {
                xtype : 'numberfield',
                fieldLabel: '排序',
                allowBlank: false,
                bind: {
                    value: '{rec.sort}'
                }
            },
            {
                fieldLabel: '备注',
                xtype: 'textarea',
                bind: {
                    activeError: '{validation.content}',
                    value: '{rec.description}'
                }
            }]
    }]
});