/**
 * 部门新增表单
 *
 * @author zangyanming <br/>
 *         date:2016-3-10
 * @version 1.0.0
 */
Ext.define('kalix.admin.depNoArea.view.DepNoAreaAddForm', {
    extend: 'Ext.FormPanel',
    requires: [
        'kalix.admin.depNoArea.viewModel.DepNoAreaViewModel',
        'kalix.admin.depNoArea.controller.DepNoAreaFormController'
    ],
    alias: 'widget.depNoAreaAddForm',
    viewModel: {
        type: 'depNoAreaViewModel'
    },
    controller: 'depNoAreaFormController',
    xtype: "depNoAreaAddForm",
    labelAlign: 'center',
    labelWidth: 75,
    autoWidth: true,
    autoHeight: true,
    jsonSubmit: true,
    bodyStyle: "padding:15px",
    frame: true,
    buttonAlign: "center",
    defaultType: 'textfield',
    url:CONFIG.restRoot + '/camel/rest/deps',
    items: [
        {xtype: 'hiddenfield', name: 'parentId', bind:{value:'{rec.parentId}'}},
        {xtype: 'hiddenfield', name: 'orgId',bind:{value:'{rec.orgId}'}},
        {xtype: 'hiddenfield', name: 'isLeaf',value:'1'},
        {xtype: 'hiddenfield', name: 'dept',value:true},
        {
            fieldLabel: '所属机构',
            editable:false,
            beforeLabelTpl: [
                '<span style="color:red;font-weight:bold" data-qtip="必填选项">*</span>'
            ],
            bind:{
                value:'{rec.orgName}'
            }
        },
        {
            fieldLabel: '上级部门',
            editable:false,
            beforeLabelTpl: [
                '<span style="color:red;font-weight:bold" data-qtip="必填选项">*</span>'
            ],
            bind:{
                value:'{rec.parentName}'
            }
        },
        {
            fieldLabel: '部门名称',
            name: 'name',
            allowBlank: false,
            blankText: '部门名称不能为空!',
            beforeLabelTpl: [
                '<span style="color:red;font-weight:bold" data-qtip="必填选项">*</span>'
            ],
            bind:{value:'{rec.name}'}
        },
        {
            fieldLabel: '机构代码',
            name: 'code',
            allowBlank: false,
            blankText: '机构不能为空!',
            beforeLabelTpl: [
                '<span style="color:red;font-weight:bold" data-qtip="必填选项">*</span>'
            ],
            bind:{value:'{rec.code}'}
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