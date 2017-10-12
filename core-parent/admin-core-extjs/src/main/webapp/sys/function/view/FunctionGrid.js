/**
 * 功能表格
 * @author majian <br/>
 *         date:2015-7-21
 * @version 1.0.0
 */
Ext.define('kalix.admin.function.view.FunctionGrid', {
    extend: 'Ext.tree.Panel',
    requires: [
        'kalix.view.components.common.SecurityToolbar',
        'kalix.view.components.common.SecurityGridColumnRUD',
        'kalix.admin.function.viewModel.FunctionViewModel',
        'kalix.admin.function.controller.FunctionGridController',
        'kalix.plugin.AutoHeightPlugin'
    ],
    plugins: ['autoheightplugin'],
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
        items: [
            {text: '编号', dataIndex: 'id', hidden: true},
            {text: '上级功能', dataIndex: 'parentName', hidden: true},
            {text: '权限代码', dataIndex: 'permission', hidden: true},
            {xtype: 'treecolumn', text: '名称', dataIndex: 'name'},
            {text: '功能代码', dataIndex: 'code'},
            {
                xtype: 'booleancolumn',
                text: '数据权限是否生效',
                trueText: '是',
                falseText: '否',
                dataIndex: 'dataPermission',
                renderer: null
            },
            {text: '数据权限主键', dataIndex: 'dataPermissionKey'},
            {text: '创建人', dataIndex: 'createBy'},
            {
                text: '创建日期', dataIndex: 'creationDate'
            },
            {
                xtype: 'securityGridColumnRUD',
                permissions: ['edit', 'delete']
            }
        ]
    },
    tbar: {
        xtype: 'securityToolbar',
        verifyItems: [
            {
                text: '添加',
                iconCls: 'iconfont icon-add',
                permission: 'add',
                handler: 'onAdd'
            }, {
                text: '刷新',
                iconCls: 'iconfont icon-refresh',
                permission: '',
                handler: 'onRefresh'
            }
        ]
    }
});