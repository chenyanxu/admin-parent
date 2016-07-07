/**
 * 机构表格
 * @author zangyanming <br/>
 *         date:2016-3-10
 * @version 1.0.0
 */
Ext.define('kalix.admin.org.view.OrgGrid', {
    extend: 'Ext.tree.Panel',
    requires: [
        'kalix.admin.org.viewModel.OrgViewModel',
        'kalix.admin.org.controller.OrgGridController'
    ],
    alias: 'widget.orgGrid',
    xtype: 'orgGridPanel',
    controller: 'orgGridController',
    viewModel: {
        type: 'orgViewModel'
    },
    expandId: -1,
    isLoad: true,
    store: Ext.create('kalix.admin.org.store.OrgStore'),
    autoLoad:true,
    stripeRows: true,
    singleExpand: true,
    manageHeight: true,
    rootVisible : false,
    title: '机构列表',
    iconCls: 'iconfont icon-organization-management',
    listeners:{
        'load': function(root) {
            var node = root.getNodeById(this.expandId);
            if (node) {
                this.isLoad = false;
                node.expand(node);
            }
        },
        'afteritemexpand': function(root) {
            if (this.isLoad) {
                this.expandId = root.id;
                //alert(root.id);
            } else {
                this.isLoad = true;
            }
        }
    },
    columns: {
        defaults: {flex: 1},
        items:
        [{text: '编号', dataIndex: 'id',hidden:true },
        {text: '上级机构', dataIndex: 'parentName',hidden:true },
        {xtype : 'treecolumn', text: '名称',dataIndex: 'name'},
        {text: '机构代码', dataIndex: 'code'},
        {text: '创建人', dataIndex: 'createBy'},
        {
            text: '创建日期', dataIndex: 'creationDate'
        },
        {
            header: '操作',
            //width: 60,
            xtype: "actioncolumn",
            items: [{
                iconCls:'iconfont icon-edit-column',
                tooltip: '编辑',
                handler: 'onEdit',
                isDisabled: function(view, rowIdx, colIdx, item, record) {
                    return record.data.name=="根机构"?true:false;
                }
            }, {
                iconCls:'iconfont icon-delete',
                tooltip: '删除',
                handler: 'onDelete',
                isDisabled: function(view, rowIdx, colIdx, item, record) {
                    return record.data.name=="根机构"?true:false;
                }
            }, {
                iconCls:'iconfont icon-add-user-column',
                tooltip: '添加用户',
                handler: 'onAddUser',
                isDisabled: function(view, rowIdx, colIdx, item, record) {
                    return record.data.name=="根机构"?true:false;
                }
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