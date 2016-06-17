/**
 * 区域表格
 * @author majian <br/>
 *         date:2015-7-24
 * @version 1.0.0
 */
Ext.define('kalix.admin.area.view.AreaGrid', {
    extend: 'Ext.tree.Panel',
    requires: [
        'kalix.admin.area.viewModel.AreaViewModel',
        'kalix.admin.area.controller.AreaGridController',
        'kalix.view.components.common.SecurityToolbar',
        'kalix.view.components.common.SecurityGridColumnCommon'
    ],
    alias: 'widget.areaGrid',
    xtype: 'areaGridPanel',
    iconCls: 'icon-area-management',
    autoLoad: true,
    stripeRows: true,

    controller: 'areaGridController',
    viewModel: 'areaViewModel',
    columns: [{
        text: '编号',
        dataIndex: 'id',
        flex: 1,
        hidden: true
    }, {
        text: '上级区域',
        dataIndex: 'parentName',
        flex: 1,
        hidden: true
    }, {
        xtype: 'treecolumn',
        text: '名称',
        dataIndex: 'name',
        flex: 3
        //width: 255
    }, {
        text: '区域代码',
        dataIndex: 'code',
        flex: 1
        //width: 60
    }, {
        text: '中心代码',
        dataIndex: 'centerCode',
        flex: 1
        //width: 60
    }, {
        text: '经度',
        dataIndex: 'jd',
        flex: 1
        //width: 60
    }, {
        text: '纬度',
        dataIndex: 'wd',
        flex: 1
        //width: 60
    }, {
        text: '创建人',
        dataIndex: 'createBy',
        flex: 1
        //width: 60
    }, {
        text: '创建日期',
        dataIndex: 'creationDate',
        flex: 2
    }, /*{
     text: '更新人',
     dataIndex: 'updateBy',
     flex:1,
     //width: 60
     }, {
     text: '更新日期',
     dataIndex: 'updateDate',
     flex:2,
     //width: 160,
     renderer: function (value) {
     var updateDate = new Date(value);
     return updateDate.format("yyyy-MM-dd hh:mm:ss");
     }
     }, */{
        header: '操作',
        width: 60,
        xtype: "securityGridColumnCommon",
        items: [{
            iconCls:'iconfont icon-edit-column',
            tooltip: '编辑',
            handler: 'onEdit',
            permission: 'admin:constructModule:areaMenu:edit',
            isDisabled: function (view, rowIdx, colIdx, item, record) {
                return record.data.name == "根机构" ? true : false;
            }
        }, {
            iconCls:'iconfont icon-delete',
            tooltip: '删除',
            handler: 'onDelete',
            permission: 'admin:constructModule:areaMenu:delete',
            isDisabled: function (view, rowIdx, colIdx, item, record) {
                return record.data.name == "根机构" ? true : false;
            }

        }
        ]
    }
    ],
    tbar: {
        xtype: 'securityToolbar',
        verifyItems: [
            {
                text: '添加',
                permission: 'admin:constructModule:areaMenu:add',
                iconCls:'iconfont icon-add',
                handler: 'onAdd'
            },
            {
                text: '刷新',
                permission: '',
                iconCls: 'iconfont icon-refresh',
                handler: 'onRefersh'
            }]
    }
})
;
