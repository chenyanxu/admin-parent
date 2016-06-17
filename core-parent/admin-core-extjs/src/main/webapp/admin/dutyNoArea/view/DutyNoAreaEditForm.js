/**
 * 机构编辑表单
 *
 * @author zangyanming <br/>
 *         date:2016-3-10
 * @version 1.0.0
 */
Ext.define('kalix.admin.dutyNoArea.view.DutyNoAreaEditForm', {
    extend: 'Ext.FormPanel',
    requires: [
        'kalix.admin.dutyNoArea.viewModel.DutyNoAreaViewModel',
        'kalix.admin.dutyNoArea.controller.DutyNoAreaFormController'
    ],
    alias: 'widget.dutyNoAreaEditForm',
    viewModel: {
        type: 'dutyNoAreaViewModel'
    },
    controller: 'dutyNoAreaFormController',
    xtype: 'dutyNoAreaEditForm',
    labelAlign: 'center',
    labelWidth: 75,
    autoWidth: true,
    autoHeight: true,
    bodyStyle: "padding:15px",
    frame: true,
    jsonSubmit: true,
    method: "PUT",
    defaultType: 'textfield',
    url: CONFIG.restRoot + '/camel/rest/dutys',
    buttonAlign: "center",
    items: [
        {xtype: 'hiddenfield', name: 'id',bind:'{rec.id}'},
        {xtype: 'hiddenfield', name: 'depid',bind:'{rec.depid}'},
        {
            fieldLabel: '所属部门',
            editable:false,
            beforeLabelTpl: [
                '<span style="color:red;font-weight:bold" data-qtip="必填选项">*</span>'
            ],
            bind:{
                value:'{rec.depName}'
            }
        },
        {
            fieldLabel: '职位名称',
            name: 'name',
            allowBlank: false,
            blankText: '名称不能为空!',
            beforeLabelTpl: [
                '<span style="color:red;font-weight:bold" data-qtip="必填选项">*</span>'
            ],
            bind:{
                value:'{rec.name}'
            }
        },
        {
            fieldLabel: '职位描述',
            name: 'comment',
            bind:{
                value:'{rec.comment}'
            }
        }
    ],
    buttons: [
        {
            text: '保存', iconCls:'iconfont icon-save iconfont-btn-small', type: 'submit', handler: 'onUpdate'
        },
        {
            text: '重置', iconCls:'iconfont icon-reset iconfont-btn-small', handler: 'onEditReset'
        }
    ]
});