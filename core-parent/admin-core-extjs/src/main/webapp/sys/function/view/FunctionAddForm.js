/**
 * 功能添加表单
 *
 * @author majian <br/>
 *         date:2015-7-21
 * @version 1.0.0
 */
Ext.define('kalix.admin.function.view.FunctionAddForm', {
    extend: 'Ext.FormPanel',
    requires: [
        'kalix.admin.function.viewModel.FunctionViewModel',
        'kalix.admin.function.controller.FunctionFormController'
    ],
    alias: 'widget.functionAddForm',
    viewModel: {
        type: 'functionViewModel'
    },
    data: {
        parentPermission: null
    },
    controller: 'functionFormController',
    xtype: 'functionAddForm',
    labelAlign: 'center',
    labelWidth: 75,
    autoWidth: true,
    autoHeight: true,
    jsonSubmit: true,
    bodyStyle: 'padding:15px',
    frame: true,
    buttonAlign: 'center',
    defaultType: 'textfield',
    items: [
        {xtype: 'hiddenfield', name: 'parentId', itemId: 'parentIdId', value: '-1'},
        {xtype: 'hiddenfield', name: 'isLeaf', value: '1'},
        {xtype: 'hiddenfield', itemId: 'permissionId', name: 'permission', value: '-1'},
        {xtype: 'hiddenfield', name: 'applicationId', itemId: 'applicationIdId', value: '-1'},
        {
            fieldLabel: '所属应用',
            itemId: 'applicationName',
            isFormField: false,
            disabled: true,
            beforeLabelTpl: [
                '<span style="color:red;font-weight:bold" data-qtip="必填选项">*</span>'
            ]
        },
        {
            fieldLabel: '上级功能',
            id: 'parentName',
            isFormField: false,
            disabled: true,
            beforeLabelTpl: [
                '<span style="color:red;font-weight:bold" data-qtip="必填选项">*</span>'
            ]
        },
        {
            fieldLabel: '名称',
            id: 'nameId',
            name: 'name',
            allowBlank: false,
            blankText: '名称不能为空!',
            beforeLabelTpl: [
                '<span style="color:red;font-weight:bold" data-qtip="必填选项">*</span>'
            ]
        },
        {
            fieldLabel: '功能代码',
            id: 'codeId',
            name: 'code',
            allowBlank: false,
            blankText: '功能代码不能为空!',
            beforeLabelTpl: [
                '<span style="color:red;font-weight:bold" data-qtip="必填选项">*</span>'
            ]
        },
        {
            fieldLabel: '数据权限主键',
            name: 'dataPermissionKey'

        }, {
            xtype: 'combobox',
            editable: false,
            valueField: 'key',
            displayField: 'name',
            // fieldStyle: 'font-size:15px;text-align:center;background:transparent;',
            store: {
                data: [
                    {'name': '是', 'key': true},
                    {'name': '否', 'key': false}
                ]
            },
            fieldLabel: '权限是否生效',
            name: 'dataPermission'
        },

        {
            xtype: 'textarea',
            fieldLabel: '备注',
            itemId: 'remarkId',
            name: 'remark',
            beforeLabelTpl: [
                '<span  >&nbsp;&nbsp;</span>'
            ]
        }
    ],
    buttons: [
        {
            text: '保存',
            type: 'submit',
            iconCls: 'iconfont icon-save iconfont-btn-small',
            handler: 'onSave'
        },
        {
            text: '重置',
            iconCls: 'iconfont icon-reset iconfont-btn-small',
            handler: 'onAddReset'
        }
    ]
});