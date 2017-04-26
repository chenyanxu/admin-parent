/**
 * 状态机表格
 * @author hqj <br/>
 *         date:2017-4-26
 * @version 1.0.0
 */
Ext.define('kalix.admin.statemachine.view.StateMachineGrid', {
    extend: 'kalix.view.components.common.BaseGrid',
    requires: [
        'kalix.admin.statemachine.controller.StateMachineGridController',
        'kalix.admin.statemachine.store.StateMachineStore',
        'kalix.view.components.common.SecurityGridColumnCommon',
        'kalix.view.components.common.IconColumn'
    ],
    alias: 'widget.stateMachineGrid',
    xtype: 'stateMachineGridPanel',
    controller: {
        type: 'stateMachineGridController',
        cfgForm: 'kalix.admin.statemachine.view.StateMachineWindow',
        cfgViewForm: 'kalix.admin.statemachine.view.StateMachineViewWindow',
        cfgModel: 'kalix.admin.statemachine.model.StateMachineModel'
    },
    store: {
        type: 'stateMachineStore'
    },
    /*viewConfig:{
     //stripeRows: false,//是否隔行换色
     getRowClass : function(record,rowIndex,rowParams,store){
     var type = record.get('available');

     if(type==0){
     return 'x-grid-row-red';
     }
     }
     },*/
    columns: [
        {
            xtype: 'rownumberer'
        },
        {
            text: '编号',
            dataIndex: 'id',
            hidden: true
        },
        {
            text: '所属应用',
            dataIndex: 'app'
        },
        {
            text: '关键字',
            dataIndex: 'key'
        },
        {
            text: '状态机描述',
            dataIndex: 'description'
        },
        {
            text: '创建人',
            dataIndex: 'createBy'
        },
        {
            text: '创建日期',
            dataIndex: 'creationDate',
            flex: 2
        },
        /*{
         text: '状态',
         dataIndex: 'available',
         renderer: function (value) {
         if (value != null && value == "1") {
         return "启用";
         }
         return "停用";
         }
         },*/
        {
            xtype: 'securityGridColumnCommon',
            flex: 0,
            width: 150,
            verifyItems: [{
                iconCls: 'iconfont icon-view-column',
                permission: 'view',
                tooltip: '查看',
                handler: 'onView'
            },
                {
                    iconCls: 'iconfont icon-edit-column',
                    /*getClass: function (v, meta, record) {
                     if (record.data.available == 1) {
                     return 'iconfont icon-edit-column';
                     }
                     else {
                     return 'kalix_hidden';
                     }
                     },*/
                    permission: 'edit',
                    tooltip: '编辑',
                    handler: 'onEdit'
                },
                /*{
                 getClass: function (v, meta, record) {
                 if (record.data.available==1) {
                 return 'iconfont icon-stop-using';
                 }
                 else{
                 return 'iconfont icon-start-using';
                 }
                 },
                 getTip: function (value, metadata, record, row, col, store) {
                 if (record.data.available==1) {
                 return '停用';
                 }
                 else{
                 return '启用';
                 }
                 },
                 permission: 'startStopUsing',
                 handler: 'onStartStopUsing'
                 },*/
                {
                    iconCls: 'iconfont icon-delete',
                    permission: 'delete',
                    tooltip: '删除',
                    handler: 'onDelete'
                }
            ]
        }
    ]
    ,
    tbar: {
        xtype: 'securityToolbar',
        verifyItems: [
            {
                text: '添加',
                xtype: 'button',
                permission: 'add',
                iconCls: 'iconfont icon-add',
                handler: 'onAdd'
            }
        ]
    }

});
