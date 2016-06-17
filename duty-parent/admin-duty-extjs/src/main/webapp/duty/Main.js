/**
 * 职位首页
 *
 * @author
 * @version 1.0.0
 */
Ext.define('kalix.app.duty.Main', {
    extend: 'kalix.container.BaseContainer',
    requires: [
        'kalix.app.duty.view.DutyGrid',
        'kalix.app.duty.view.DutySearchForm',
        'kalix.app.duty.viewModel.DutyViewModel'
    ],
    storeId: 'dutyStore',
    viewModel: 'dutyViewModel',
    items: [
        {
            title: '职位查询',
            iconCls: 'x-fa fa-search',
            xtype: 'dutySearchForm'
        }, {
            xtype: 'dutyGridPanel',
            id: 'dutyGridPanel',
            title: '职位列表',
            iconCls: 'x-fa fa-cutlery',
            margin: 10
        }
    ]
});
