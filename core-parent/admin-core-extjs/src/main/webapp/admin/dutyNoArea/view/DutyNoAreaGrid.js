/**
 * 部门表格
 * @author zangyanming <br/>
 *         date:2016-3-10
 * @version 1.0.0
 */
Ext.define('kalix.admin.dutyNoArea.view.DutyNoAreaGrid', {
    extend: 'Ext.tree.Panel',
    requires: [
        'kalix.admin.dutyNoArea.viewModel.DutyNoAreaViewModel',
        'kalix.admin.dutyNoArea.controller.DutyNoAreaGridController'
    ],
    alias: 'widget.dutyNoAreaGrid',
    xtype: 'dutyNoAreaGridPanel',
    controller: 'dutyNoAreaGridController',
    viewModel: {
        type: 'dutyNoAreaViewModel'
    },
    title: '职位列表',
    iconCls: 'x-fa fa-university',
    stripeRows: true,
    manageHeight: true,
    rootVisible: false,
    defaults: {
        flex: 1
    },
    columns: {
        defaults: {flex: 1},
        items:[
        {text: '编号', dataIndex: 'id'},
        {text: '职位名称', dataIndex: 'name'},
        {text: '职位描述', dataIndex: 'comment'},
        {text: '所属部门', dataIndex: 'depid'},
        {text: '创建人', dataIndex: 'createBy'},
        {
            text: '创建日期', dataIndex: 'creationDate'
        },
        {
            header: '操作',
            xtype: "actioncolumn",
            items: [{
                iconCls:'iconfont icon-edit-column',
                tooltip: '编辑',
                handler: 'onEdit'
            }, {
                iconCls:'iconfont icon-delete',
                tooltip: '删除',
                handler: 'onDelete'
            }, {
                iconCls:'iconfont icon-add-user-column',
                tooltip: '添加用户',
                handler: 'onAddUser'
            }]
        }
    ]},
    tbar: [
        {
            text: '添加',
            iconCls:'iconfont icon-add',
            handler: 'onAdd'
        }, {
            text: '刷新',
            iconCls:'iconfont icon-refresh',
            handler: 'onRefersh'
        }]

});