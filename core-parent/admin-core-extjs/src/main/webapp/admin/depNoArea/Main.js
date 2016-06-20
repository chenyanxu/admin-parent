/**
 * 部门组件
 *
 * @author zangyanming <br/>
 *         date:2016-3-10
 * @version 1.0.0
 */
Ext.define('kalix.admin.depNoArea.Main', {
    extend: 'kalix.view.components.common.AutoHPanel',
    requires: [
        'kalix.admin.depNoArea.viewModel.DepNoAreaViewModel',
        'kalix.admin.depNoArea.controller.DepNoAreaController'
    ],
    xtype: 'depNoAreaPanel',
    controller: 'depNoAreaController',
    viewModel: {
        type: 'depNoAreaViewModel'
    },
    items: [{
            xtype: 'orgNoAreaTreeList',
            flex: 1,
            listeners: {
                itemClick: 'onOrgClick'
            }
        },
        {
            xtype: 'depNoAreaGridPanel',
            flex: 2,
            store: Ext.create('kalix.admin.depNoArea.store.DepNoAreaStore')
        }
    ]
});