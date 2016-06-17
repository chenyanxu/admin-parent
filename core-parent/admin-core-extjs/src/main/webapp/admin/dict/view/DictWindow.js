/**
 * 字典新增和修改表单
 *
 * @author
 * @version 1.0.0
 */
Ext.define('kalix.admin.dict.view.DictWindow', {
    extend: 'kalix.view.components.common.BaseWindow',
    requires: [
        'kalix.admin.dict.viewModel.DictViewModel',
        'kalix.controller.BaseWindowController',
        'kalix.admin.user.store.UserStore'
    ],
    alias: 'widget.dictWindow',
    viewModel: 'dictViewModel',
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
                fieldLabel: '字典名称',
                allowBlank: false,
                bind: {
                    value: '{rec.dictName}'
                }
            },
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