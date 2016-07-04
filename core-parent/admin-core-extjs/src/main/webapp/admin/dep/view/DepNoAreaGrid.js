/**
 * 部门表格
 * @author zangyanming <br/>
 *         date:2016-3-10
 * @version 1.0.0
 */
Ext.define('kalix.admin.depNoArea.view.DepNoAreaGrid', {
    extend: 'Ext.tree.Panel',
    requires: [
        'kalix.admin.depNoArea.viewModel.DepNoAreaViewModel',
        'kalix.admin.depNoArea.controller.DepNoAreaGridController'
    ],
    alias: 'widget.depNoAreaGrid',
    xtype: 'depNoAreaGridPanel',
    controller: 'depNoAreaGridController',
    viewModel: {
        type: 'depNoAreaViewModel'
    },
    data: {
        depId: null,
        depName: null
    },
    title: '部门列表',
    iconCls: 'iconfont icon-department-management',
    stripeRows: true,
    manageHeight: true,
    rootVisible: false,
    defaults: {
        flex: 1
    },
    columns: {
        defaults: {flex: 1},
        items: [
            {text: '编号', dataIndex: 'id', hidden: true},
            {text: '上级部门', dataIndex: 'parentName', hidden: true},
            {xtype: 'treecolumn', text: '名称', dataIndex: 'name'},
            {text: '部门代码', dataIndex: 'code'},
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
                    handler: 'onEdit',
                    isDisabled: function (view, rowIdx, colIdx, item, record) {
                        return record.data.name == "根部门" ? true : false;
                    }
                }, {
                    iconCls:'iconfont icon-delete',
                    tooltip: '删除',
                    handler: 'onDelete',
                    isDisabled: function (view, rowIdx, colIdx, item, record) {
                        return record.data.name == "根机构" ? true : false;
                    }

                }, {
                    iconCls:'iconfont icon-add-user-column',
                    tooltip: '添加用户',
                    handler: 'onAddUser'
                }]
            }
        ]
    },
    tbar: [
        {
            text: '添加',
            iconCls:'iconfont icon-add',
            handler: 'onAdd'
        }, {
            text: '刷新',
            iconCls:'iconfont icon-refresh',
            handler: 'onRefersh'
        }
    ]
});