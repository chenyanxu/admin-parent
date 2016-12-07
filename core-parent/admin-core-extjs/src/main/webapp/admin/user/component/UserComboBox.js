/**
 * 用户选择下拉comboBox
 *
 * @author chenyanxu <br/>
 *         date:2015-6-18
 * @version 1.0.0
 */
Ext.define('kalix.admin.user.component.UserComboBox', {
    extend:'kalix.view.components.common.BaseComboBox',
    requires: [
        'kalix.admin.user.store.UserStore'
    ],
    alias: 'widget.userComboBox',
    allowBlank: false,
    labelAlign: 'right',
    xtype: 'userCombobox',
    queryMode: 'remote',
    valueField: 'name',
    displayField: 'name',
    queryParam: '%name%',
    typeAhead:true,
    store: {
        type: 'userStore',
        autoLoad: true
    }
    //,
    //getParams: function (queryString) {
    //    var params = {},
    //        param = this.queryParam;
    //
    //    if (param) {
    //        params[param] = '{"%' + this.displayField + '%":"' + queryString + '"}';
    //    }
    //
    //    return params;
    //}
});
