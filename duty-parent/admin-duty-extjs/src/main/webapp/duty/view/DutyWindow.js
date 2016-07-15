/**
 *
 * @author chenyanxu
 * @version 1.0.0
 */
Ext.define('kalix.admin.duty.view.DutyWindow', {
    extend: 'kalix.view.components.common.BaseWindow',
    requires: [
        'kalix.controller.BaseWindowController'
    ],
    alias: 'widget.dutyWindow',
    controller: {
        type: 'baseWindowController'
    },
    xtype: 'dutyWindow',
    width: 400,
    items: [{
        xtype: 'baseForm',
        items: [
            {
                fieldLabel: '所属机构',
                editable:false,
                bind:{
                    value:'{orgName}'
                }
            },
            {
                fieldLabel: '职位名称',
                name: 'name',
                bind:{
                    value:'{rec.name}'
                }
            },
            {
                fieldLabel: '职位描述',
                name: 'comment',
                bind:{
                    value:'{rec.comment}'
                }
            }
        ]
    }]
});