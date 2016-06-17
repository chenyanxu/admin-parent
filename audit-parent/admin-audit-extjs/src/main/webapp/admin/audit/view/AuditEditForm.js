/**
 * 用户编辑表单
 *
 * @author majian
 *         date:2015-6-18
 * @version 1.0.0
 */
Ext.define('kalix.admin.audit.view.AuditEditForm', {
    extend: 'Ext.FormPanel',
    requires: [
        'kalix.admin.audit.viewModel.AuditViewModel',
        'kalix.admin.audit.controller.AuditFormController'
    ],
    alias: 'widget.auditEditForm',
    viewModel: {
        type: 'auditViewModel'
    },
    currentAudit: null,
    config: {
        currentAudit: null
    },

    bind: {
        currentAudit: "{currentAudit}",
    },
    controller: 'auditFormController',
    id: "auditEditForm",
    xtype: 'auditEditForm',
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
        {xtype: 'hiddenfield', name: 'id', bind: {value: '{currentAudit.id}'}},

        {
            fieldLabel: '标题',
            id: 'titleId',
            name: 'title',
            allowBlank: false,
            blankText: '标题不能为空!',
            beforeLabelTpl: [
                '<span style="color:red;font-weight:bold" data-qtip="必填选项">*</span>'
            ]
        },
        {
            fieldLabel: '内容',
            id: 'contentId',
            xtype: 'textarea',
            name: 'content',
            allowBlank: false,
            blankText: '内容不能为空!',
            beforeLabelTpl: [
                '<span style="color:red;font-weight:bold" data-qtip="必填选项">*</span>'
            ]
        },

    ],
    buttons: [
        {
            text: '保存', glyph: 0xf0c7, type: 'submit', handler: 'onUpdate',
        },
        {
            text: '重置', iconCls:'iconfont icon-reset iconfont-btn-small', handler: 'onEditReset'
        }
    ]
});