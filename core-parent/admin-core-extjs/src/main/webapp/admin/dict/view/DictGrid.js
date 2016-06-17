/**
 * 字典表格
 * @author majian <br/>
 *         date:2015-7-3
 * @version 1.0.0
 */
Ext.define('kalix.admin.dict.view.DictGrid', {
    extend: 'kalix.view.components.common.BaseGrid',
    requires: [
        'kalix.admin.dict.controller.DictGridController',
        'kalix.admin.dict.store.DictStore',
    ],
    alias: 'widget.dictGrid',
    xtype: 'dictGridPanel',
    controller: {
        type: 'dictGridController',
        storeId: 'dictStore',
        cfgForm: 'kalix.admin.dict.view.DictWindow',
        cfgViewForm: 'kalix.admin.dict.view.DictViewWindow',
        cfgModel: 'kalix.admin.dict.model.DictModel'
    },
    store: {
        type: 'dictStore'
    },
    columns: {
        defaults: {flex: 1, renderer: 'addTooltip'},
        items: [
            {
                xtype: "rownumberer",
                text: "行号",
                width: 50,
                flex: 0,
                align: 'center',
                renderer: this.update
            },
            {text: '编号', dataIndex: 'id', hidden: true},
            {text: '字典名称', dataIndex: 'dictName'},
            {text: '标签名', dataIndex: 'label'},
            {text: '数据值', dataIndex: 'value'},
            {text: '类型', dataIndex: 'type'},
            {text: '排序', dataIndex: 'sort'},
            {text: '创建人', dataIndex: 'createBy', flex: 1},
            {
                text: '创建日期', dataIndex: 'creationDate', flex: 1
            },
            {
                xtype: 'securityGridColumnRUD',
                permissions: [
                    'admin:sysModule:dictMenu:view',
                    'admin:sysModule:dictMenu:edit',
                    'admin:sysModule:dictMenu:delete'
                ]
            }]
    },
    tbar: {
        xtype: 'securityToolbar',
        verifyItems: [
            {
                text: '添加',
                tooltip: '添加字典',
                xtype: 'button',
                permission: 'admin:sysModule:dictMenu:add',
                iconCls:'iconfont icon-add',
                handler: 'onAdd'
            }
        ]
    }

});