/**
 * 区域新增表单
 *
 * @author majian <br/>
 *         date:2015-7-24
 * @version 1.0.0
 */
Ext.define('kalix.admin.area.view.AreaAddForm', {
    extend: 'kalix.view.components.common.FormPanel',
    requires: [
        'kalix.admin.area.viewModel.AreaViewModel',
        'kalix.admin.area.controller.AreaFormController'
    ],
    alias: 'widget.areaAddForm',
    viewModel: {
        type: 'areaViewModel'
    },
    controller: 'areaFormController',
    xtype: "areaAddForm",
    labelAlign: 'center',
    labelWidth: 75,
    autoWidth: true,
    autoHeight: true,
    jsonSubmit: true,
    bodyStyle: "padding:15px",
    frame: true,
    buttonAlign: "center",
    //layout: 'form',
    defaultType: 'textfield',
    items: [
        {xtype: 'hiddenfield', name: 'parentId', id: 'parentIdId', value: '-1'},
        {xtype: 'hiddenfield', name: 'isLeaf', value: '1'},
        {
            fieldLabel: '上级区域',
            itemId: "parentName",
            isFormField: false,
            disabled: true,

        },
        {
            fieldLabel: '名称',
            itemId: 'nameId',
            name: 'name',
            allowBlank: false,
            blankText: '名称不能为空!',

        },
        {
            fieldLabel: '区域代码',
            itemId: 'codeId',
            name: 'code',
            allowBlank: false,
            blankText: '区域不能为空!',

        },
        {
            fieldLabel: '中心代码',
            itemId: 'centerCodeId',
            name: 'centerCode',
            allowBlank: false,
            blankText: '中心代码不能为空!',

        },
        {
            fieldLabel: '纬度',
            itemId: 'wdId',
            name: 'wd'
        },
        {
            fieldLabel: '经度',
            itemId: 'jdId',
            name: 'jd'
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