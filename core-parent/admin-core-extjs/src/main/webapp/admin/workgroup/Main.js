/**
 * 用户组件
 *
 * @author majian <br/>
 *         date:2015-6-18
 * @version 1.0.0
 */
Ext.define('kalix.admin.workgroup.Main', {
    extend: 'kalix.container.BaseContainer',
    requires: [
        'kalix.admin.workgroup.view.WorkGroupGrid',
        'kalix.admin.workgroup.view.WorkGroupSearchForm',
        'kalix.admin.workgroup.viewModel.WorkGroupViewModel'
    ],
    storeId: 'workgroupStore',
    viewModel: 'workgroupViewModel',
    items: [
        {
            title: '工作组查询',
            xtype: 'workgroupSearchForm'
        }, {
            xtype: 'workgroupGridPanel',
            id: 'workgroupGridPanel',
            title: '工作组列表',
            margin: 10
        }
    ]
});

