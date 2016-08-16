/**
 * 部门选择下拉comboBox
 *
 * @author zangyanming <br/>
 *         date:2016-8-4
 * @version 1.0.0
 */
Ext.define('kalix.admin.user.component.UserOrgComboBox', {
    extend: 'Ext.form.field.ComboBox',
    requires: [
        'kalix.admin.user.store.UserOrgStore'
    ],
    alias: 'widget.userOrgComboBox',
    allowBlank: false,
    labelAlign: 'right',
    xtype: 'userOrgComboBox',
    queryMode: 'remote',
    valueField: 'orgId',
    displayField: 'orgName',
    queryParam: 'jsonStr',
    minChars: 0,
    typeAhead:false,
    editable: false,
    store: {
        type: 'userOrgStore'
    },
    constructor:function(){
        this.callParent(arguments);

        this.store.on('load',function(target,records){
            if(records.length > 0){
                this.select(records[0]);
            }
        },this);
    },
    getParams: function (queryString) {
        var params = {},
            param = this.queryParam;

        if (param) {
            params[param] = '{' + this.displayField + ':"' + queryString + '"}';
        }

        return params;
    }
});
