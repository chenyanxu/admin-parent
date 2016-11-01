/**
 * 用户选择下拉comboBox
 *
 * @author chenyanxu <br/>
 *         date:2015-6-18
 * @version 1.0.0
 */
Ext.define('kalix.admin.user.component.UserTagField', {
    extend: 'Ext.form.field.Tag',
    requires: [
        'kalix.admin.user.store.UserStore'
    ],
    alias: 'widget.userTagField',
    labelAlign: 'right',
    xtype: 'userTagField',
    queryMode: 'remote',
    valueField: 'name',
    displayField: 'name',
    queryParam: 'jsonStr',
    minChars: 1,
    typeAhead: false,
    filterPickList: true,
    autoSelect:false,
    store: {
        type: 'userStore'
    },
    getParams: function (queryString) {
        var params = {},
            param = this.queryParam;

        if (param) {
            params[param] = '{"%' + this.displayField + '%":"' + queryString + '"}';
        }

        return params;
    },
    listeners:{
        change:function () {
            this.ariaEl.dom.value='';
            this.collapse();
        }
    }
});
