/**
 * 机构新增表单
 *
 * @author majian <br/>
 *         date:2015-7-21
 * @version 1.0.0
 */
Ext.define('kalix.admin.org.view.OrgAddForm', {
    extend: 'Ext.FormPanel',
    requires: [
        'kalix.admin.org.viewModel.OrgViewModel',
        'kalix.admin.org.controller.OrgFormController'
    ],
    alias: 'widget.orgAddForm',
    viewModel: {
        type: 'orgViewModel'
    },
    controller: 'orgFormController',
    xtype: "orgAddForm",
    labelAlign: 'center',
    labelWidth: 75,
    autoWidth: true,
    autoHeight: true,
    jsonSubmit: true,
    bodyStyle: "padding:15px",
    frame: true,
    buttonAlign: "center",
    defaultType: 'textfield',
    items: [
        {xtype: 'hiddenfield', name: 'parentId', itemId: 'parentIdId', value: '-1'},
        {xtype: 'hiddenfield', name: 'isLeaf',value:'1'},
        {xtype: 'hiddenfield', name: 'areaId', itemId: 'areaIdId', value: '-1'},
        {
            fieldLabel: '所属区域',
            itemId: "areaName",
            isFormField: false,
            disabled: true,
            beforeLabelTpl: [
                '<span style="color:red;font-weight:bold" data-qtip="必填选项">*</span>'
            ]
        },
        {
            fieldLabel: '上级机构',
            id:"parentName",
            isFormField: false,
            disabled:true,
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
            fieldLabel: '机构代码',
            id: 'codeId',
            name: 'code',
            allowBlank: false,
            blankText: '机构不能为空!',
            beforeLabelTpl: [
                '<span style="color:red;font-weight:bold" data-qtip="必填选项">*</span>'
            ]
        },
        {
            fieldLabel: '中心代码',
            id: 'centerCodeId',
            name: 'centerCode',
            allowBlank: false,
            blankText: '中心代码不能为空!',
            beforeLabelTpl: [
                '<span style="color:red;font-weight:bold" data-qtip="必填选项">*</span>'
            ]
        }
    ],
    buttons: [
        {
            text: '保存',
            type: 'submit',
            glyph: 'xf0c7@FontAwesome',
            handler: 'onSave'
        },
        {
            text: '重置',
            glyph: 'xf0e2@FontAwesome',
            handler: 'onAddReset'
        }
    ]
});