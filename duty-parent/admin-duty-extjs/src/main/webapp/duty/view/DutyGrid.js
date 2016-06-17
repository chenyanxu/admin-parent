/**
 * 职位表格
 * @author
 * @version 1.0.0
 */
Ext.define('kalix.app.duty.view.DutyGrid', {
    extend: 'kalix.view.components.common.BaseGrid',
    requires: [
        'kalix.app.duty.controller.DutyGridController',
        'kalix.app.duty.store.DutyStore'
    ],
    alias: 'widget.dutyGrid',
    xtype: 'dutyGridPanel',
    controller: {
        type: 'dutyGridController',
        storeId: 'dutyStore',
        cfgForm: 'kalix.app.duty.view.DutyWindow',
        cfgViewForm: 'kalix.app.duty.view.DutyViewWindow',
        cfgModel: 'kalix.app.duty.model.DutyModel'
    },
    store: {
        type: 'dutyStore'
    },

    //todo 在此修改grid显示列
    columns: {
        defaults: {flex: 1,renderer: 'addTooltip'},
        items: [
            {
                xtype: "rownumberer",
                text: "行号",
                width: 50,
                align: 'center',
                flex:0,
                renderer:this.update
            },
            {
                text: '编号',
                dataIndex: 'id',
                hidden: true
            },
            {
                text: '职位名称',
                dataIndex: 'name'
            },
            {
                text: '所在部门',
                dataIndex: 'department'
            }, {
                text: '职位描述',
                dataIndex: 'comment'
            },
            {
                xtype: 'securityGridColumnRUD',
                //todo change permission
                permissions: [
                    'admin:constructModule:dutyMenu:view',
                    'admin:constructModule:dutyMenu:edit',
                    'admin:constructModule:dutyMenu:delete'
                ]
            }
        ]
    },
    tbar: {
        xtype: 'securityToolbar',
        verifyItems: [
            {
                text: '添加',
                xtype: 'button',
                //todo change permission
                permission: 'admin:constructModule:dutyMenu:add',
                bind: {icon: '{add_image_path}'},
                handler: 'onAdd'
            }
        ]
    }
});
