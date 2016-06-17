/**
 * 部门新增表单
 *
 * @author zangyanming <br/>
 *         date:2016-3-10
 * @version 1.0.0
 */
Ext.define('kalix.admin.dutyNoArea.view.DutyNoAreaAddForm', {
    extend: 'Ext.FormPanel',
    requires: [
        'kalix.admin.dutyNoArea.viewModel.DutyNoAreaViewModel',
        'kalix.admin.dutyNoArea.controller.DutyNoAreaFormController'
    ],
    alias: 'widget.dutyNoAreaAddForm',
    viewModel: {
        type: 'dutyNoAreaViewModel'
    },
    controller: 'dutyNoAreaFormController',
    xtype: "dutyNoAreaAddForm",
    labelAlign: 'center',
    labelWidth: 75,
    autoWidth: true,
    autoHeight: true,
    jsonSubmit: true,
    bodyStyle: "padding:15px",
    frame: true,
    buttonAlign: "center",
    defaultType: 'textfield',
    url: CONFIG.restRoot + '/camel/rest/dutys',
    items: [
        {
            fieldLabel: '部门编号',
            name: "depid",
            editable:false,
            beforeLabelTpl: [
                '<span style="color:red;font-weight:bold" data-qtip="必填选项">*</span>'
            ],
            bind:{
                value:'{rec.depid}'
            }
        },
        {
            fieldLabel: '所属部门',
            editble:false,
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
            text: '保存',
            type: 'submit',
            iconCls:'iconfont icon-save iconfont-btn-small',
            handler: 'onSave'
        },
        {
            text: '重置',
            iconCls:'iconfont icon-reset iconfont-btn-small',
            handler: 'onAddReset'
        }
    ]
});