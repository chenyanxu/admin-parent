/**
 * 机构表格
 * @author majian <br/>
 *         date:2015-7-21
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
    data: {
        areaId: null,
        areaName: null
    },
    stripeRows: true,
    /*rootProperty:{
        id:'-1',
        name:'根机构'
    },*/
    manageHeight: true,
    rootVisible : false,
    title: '机构列表',
    iconCls: 'x-fa fa-building',
    columns: {
        defaults: {flex: 1},
        items:
        [{text: '编号', dataIndex: 'id',hidden:true },
        {text: '上级机构', dataIndex: 'parentName',hidden:true },
        {xtype : 'treecolumn', text: '名称',dataIndex: 'name'},
        {text: '机构代码', dataIndex: 'code'},
        {text: '中心代码', dataIndex: 'centerCode'},
        {text: '创建人', dataIndex: 'createBy'},
        {
            text: '创建日期', dataIndex: 'creationDate', renderer: function (value) {
            var createDate = new Date(value);
            return createDate.format("yyyy-MM-dd hh:mm:ss");
        }
        },
        /*{text: '更新人', dataIndex: 'updateBy'},
        {
            text: '更新日期', dataIndex: 'updateDate', renderer: function (value) {
            var updateDate = new Date(value);
            return updateDate.format("yyyy-MM-dd hh:mm:ss");
        }
        }*/
        {
            header: '操作',
            //width: 60,
            xtype: "actioncolumn",
            items: [{
                icon: "admin/resources/images/pencil.png",
                tooltip: '编辑',
                handler: 'onEdit',
                isDisabled: function(view, rowIdx, colIdx, item, record) {
                    return record.data.name=="根机构"?true:false;
                }
            }, {
                icon: "admin/resources/images/cancel.png",
                tooltip: '删除',
                handler: 'onDelete',
                isDisabled: function(view, rowIdx, colIdx, item, record) {
                    return record.data.name=="根机构"?true:false;
                }

            }]
        }
    ]},
    tbar: [
        {
            text: '添加', icon: 'admin/resources/images/script_add.png', handler: 'onAdd'
        }, {
            text: '刷新', icon: 'admin/resources/images/arrow_refresh.png', handler: 'onRefersh'
        }]

});