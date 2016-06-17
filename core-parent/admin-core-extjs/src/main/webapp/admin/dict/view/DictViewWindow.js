/**
 * 字典查看表单
 *
 * @author
 * @version 1.0.0
 */

Ext.define('kalix.admin.dict.view.DictViewWindow', {
    extend: 'kalix.view.components.common.BaseWindow',
    requires: [
        'kalix.admin.dict.viewModel.DictViewModel',
        'kalix.admin.user.store.UserStore'
    ],
    alias: 'widget.dictViewWindow',
    viewModel: 'dictViewModel',
    xtype: "dictViewWindow",
    width: 400,
    items: [{
        defaults: {readOnly: true},
        xtype: 'baseForm',
        items: [
            {
                fieldLabel: '字典名称',
                bind: {
                    value: '{rec.dictName}'
                }
            },
            {
                fieldLabel: '标签名',
                bind: {
                    value: '{rec.label}'
                }
            },
            {
                fieldLabel: '数据值',
                bind: {
                    value: '{rec.value}'
                }
            }, {
                fieldLabel: '类型',
                bind: {
                    value: '{rec.type}'
                }
            },
            {
                xtype : 'numberfield',
                fieldLabel: '排序',
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