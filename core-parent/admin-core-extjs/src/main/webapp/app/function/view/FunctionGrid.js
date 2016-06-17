/**
 * 功能表格
 * @author majian <br/>
 *         date:2015-7-21
 * @version 1.0.0
 */
Ext.define('kalix.app.function.view.FunctionGrid', {
    extend: 'Ext.tree.Panel',
    requires: [
        'kalix.app.function.viewModel.FunctionViewModel',
        'kalix.app.function.controller.FunctionGridController'
    ],
    alias: 'widget.functionGrid',
    xtype: 'functionGridPanel',
    controller: 'functionGridController',
    viewModel: {
        type: 'functionViewModel'
    },
    data: {
        applicationId: null,
        applicationName: null,
        applicationCode: null
    },
    border: true,
    stripeRows: true,
    /*rootProperty:{
     id:'-1',
     name:'根功能'
     },*/
    manageHeight: true,
    rootVisible: false,
    title: '功能列表',
    iconCls: 'iconfont icon-function-management',
    columns: {
        defaults: {flex: 1},
        items:[
        {text: '编号', dataIndex: 'id', hidden: true},
        {text: '上级功能', dataIndex: 'parentName', hidden: true},
        {text: '权限代码', dataIndex: 'permission', hidden: true},
        {xtype: 'treecolumn', text: '名称', dataIndex: 'name'},
        {text: '功能代码', dataIndex: 'code'},
        {text: '创建人', dataIndex: 'createBy'},
        {
            text: '创建日期', dataIndex: 'creationDate'
        },
        /*{text: '更新人', dataIndex: 'updateBy'},
        {
            text: '更新日期', dataIndex: 'updateDate', renderer: function (value) {
            var updateDate = new Date(value);
            return updateDate.format("yyyy-MM-dd hh:mm:ss");
        }
        },*/
        {
            header: '操作',
            flex:0.5,
            xtype: "actioncolumn",
            items: [{
                iconCls: 'iconfont icon-edit-column',
                tooltip: '编辑',
                handler: 'onEdit',
                isDisabled: function (view, rowIdx, colIdx, item, record) {
                    return record.data.name == "根功能" ? true : false;
                }
            }, {
                iconCls: 'iconfont icon-delete',
                tooltip: '删除',
                handler: 'onDelete',
                isDisabled: function (view, rowIdx, colIdx, item, record) {
                    return record.data.name == "根功能" ? true : false;
                }

            }]
        }
    ]},
    tbar: [
        {
            text: '添加',
            iconCls: 'iconfont icon-add',
            handler: 'onAdd'
        }, {
            text: '刷新',
            iconCls:'iconfont icon-refresh',
            handler: 'onRefersh'
        }]

});