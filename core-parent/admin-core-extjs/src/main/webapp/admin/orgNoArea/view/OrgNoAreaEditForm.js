/**
 * 机构编辑表单
 *
 * @author zangyanming <br/>
 *         date:2016-3-10
 * @version 1.0.0
 */
Ext.define('kalix.admin.orgNoArea.view.OrgNoAreaEditForm', {
    extend: 'Ext.FormPanel',
    requires: [
        'kalix.admin.orgNoArea.viewModel.OrgNoAreaViewModel',
        'kalix.admin.orgNoArea.controller.OrgNoAreaFormController'
    ],
    alias: 'widget.orgNoAreaEditForm',
    viewModel: {
        type: 'orgNoAreaViewModel'
    },
    controller: 'orgNoAreaFormController',
    xtype: 'orgNoAreaEditForm',
    labelAlign: 'center',
    labelWidth: 75,
    autoWidth: true,
    autoHeight: true,
    bodyStyle: "padding:15px",
    frame: true,
    jsonSubmit: true,
    method: "PUT",
    defaultType: 'textfield',
    url:CONFIG.restRoot + '/camel/rest/orgs/',
    buttonAlign: "center",
    items: [
        {xtype: 'hiddenfield', name: 'id', bind:{value:'{rec.id}'}},
        {xtype: 'hiddenfield', bind:{value:'{rec.parentId}'}},
        {xtype: 'hiddenfield', name: 'isLeaf',value:'1'},
        {
            fieldLabel: '上级机构',
            editable:false,
            beforeLabelTpl: [
                '<span style="color:red;font-weight:bold" data-qtip="必填选项">*</span>'
            ],
            bind:{
                value:'{rec.parentName}'
            }
        },
        {
            fieldLabel: '机构名称',
            name: 'name',
            allowBlank: false,
            blankText: '机构名称不能为空!',
            beforeLabelTpl: [
                '<span style="color:red;font-weight:bold" data-qtip="必填选项">*</span>'
            ],
            bind:{
                value:'{rec.name}'
            }
        },
        {
            fieldLabel: '机构代码',
            name: 'code',
            allowBlank: false,
            blankText: '机构不能为空!',
            beforeLabelTpl: [
                '<span style="color:red;font-weight:bold" data-qtip="必填选项">*</span>'
            ],
            bind:{
                value:'{rec.code}'
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