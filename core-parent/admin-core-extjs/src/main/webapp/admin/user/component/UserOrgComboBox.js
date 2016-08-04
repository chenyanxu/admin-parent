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
    valueField: 'name',
    displayField: 'name',
    queryParam: 'jsonStr',
    minChars: 0,
    typeAhead:true,
    store: {
        type: 'userOrgStore'
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
