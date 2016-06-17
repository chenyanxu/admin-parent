/**
 * 机构编辑表单
 *
 * @author majian <br/>
 *         date:2015-7-21
 * @version 1.0.0
 */
Ext.define('kalix.admin.org.view.OrgEditForm', {
    extend: 'Ext.FormPanel',
    requires: [
        'kalix.admin.org.viewModel.OrgViewModel',
        'kalix.admin.org.controller.OrgFormController'
    ],
    alias: 'widget.orgEditForm',
    viewModel: {
        type: 'orgViewModel'
    },
    controller: 'orgFormController',
    xtype: 'orgEditForm',
    labelAlign: 'center',
    labelWidth: 75,
    autoWidth: true,
    autoHeight: true,
    bodyStyle: "padding:15px",
    frame: true,
    jsonSubmit: true,
    method: "PUT",
    defaultType: 'textfield',
    buttonAlign: "center",
    items: [
        {xtype: 'hiddenfield', name: 'id'},
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
            itemId: "parentName",
            isFormField: false,
            disabled:true,
            beforeLabelTpl: [
                '<span style="color:red;font-weight:bold" data-qtip="必填选项">*</span>'
            ]
        },
        {
            fieldLabel: '名称',
            itemId: 'nameId',
            name: 'name',
            allowBlank: false,
            blankText: '名称不能为空!',
            beforeLabelTpl: [
                '<span style="color:red;font-weight:bold" data-qtip="必填选项">*</span>'
            ]
        },
        {
            fieldLabel: '机构代码',
            itemId: 'codeId',
            name: 'code',
            allowBlank: false,
            blankText: '机构不能为空!',
            beforeLabelTpl: [
                '<span style="color:red;font-weight:bold" data-qtip="必填选项">*</span>'
            ]
        },
        {
            fieldLabel: '中心代码',
            itemId: 'centerCodeId',
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
            text: '保存', glyph: 'xf0c7@FontAwesome', type: 'submit', handler: 'onUpdate'
        },
        {
            text: '重置', glyph: 'xf0e2@FontAwesome', handler: 'onEditReset'
        }
    ]
});