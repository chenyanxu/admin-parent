/**
 * 机构表单控制器
 *
 * @author majian <br/>
 *         date:2015-7-21
 * @version 1.0.0
 */
Ext.define('kalix.admin.org.controller.OrgFormController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.orgFormController',

    /**
     * 重置操作.
     * @returns {Ext.panel.Panel}
     */
    onAddReset: function () {
        this.getView().reset();
    },
    /**
     * 重置操作.
     * @returns {Ext.panel.Panel}
     */
    onEditReset: function () {
        this.getView().reset();
    },
    /**
     * 保存操作.
     * @returns {Ext.panel.Panel}
     */
    onSave: function () {
        var form = this.getView();
        if (form.isValid()) {
            form.submit({
                success: function (form, action) {
                    Ext.Msg.alert(CONFIG.ALTER_TITLE_SUCCESS, action.result.msg);
                    var grid = Ext.ComponentQuery.query('orgGridPanel')[0];
                    var store = grid.getStore();
                    store.reload();
                },
                failure: function (form, action) {
                    Ext.Msg.alert(CONFIG.ALTER_TITLE_FAILURE, action.result.msg);
                }
            });
        }
    },
    /**
     * 更新操作.
     * @returns {Ext.panel.Panel}
     */
    onUpdate: function () {
        var form = this.getView();
        if (form.isValid()) {
            form.submit({
                success: function (form, action) {
                    Ext.Msg.alert(CONFIG.ALTER_TITLE_SUCCESS, action.result.msg);
                    var grid = Ext.ComponentQuery.query('orgGridPanel')[0];
                    var store = grid.getStore();
                    store.reload();
                },
                failure: function (form, action) {
                    Ext.Msg.alert(CONFIG.ALTER_TITLE_FAILURE, action.result.msg);
                }
            });
        }
    }
});