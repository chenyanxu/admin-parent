/**
 * 部门表单控制器
 *
 * @author zangyanming <br/>
 *         date:2016-3-10
 * @version 1.0.0
 */
Ext.define('kalix.admin.dutyNoArea.controller.DutyNoAreaFormController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.dutyNoAreaFormController',

    /**
     * 重置操作.
     * @returns {Ext.panel.Panel}
     */
    onAddReset: function () {
        //this.getView().reset();
        var model=this.getView().lookupViewModel().get('rec');
        model.set(model.modified);
    },
    /**
     * 重置操作.
     * @returns {Ext.panel.Panel}
     */
    onEditReset: function () {
        //this.getView().reset();
        var model=this.getView().lookupViewModel().get('rec');
        model.set(model.modified);
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
                    if (action.result.failure) {
                        Ext.MessageBox.alert(CONFIG.ALTER_TITLE_FAILURE, action.result.msg);
                        return;
                    }
                    kalix.core.Notify.success(action.result.msg, CONFIG.ALTER_TITLE_SUCCESS);

                    var grid = Ext.ComponentQuery.query('dutyNoAreaGridPanel')[0];
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
                    if (action.result.failure) {
                        Ext.MessageBox.alert(CONFIG.ALTER_TITLE_FAILURE, action.result.msg);
                        return;
                    }
                    kalix.core.Notify.success(action.result.msg, CONFIG.ALTER_TITLE_SUCCESS);

                    var grid = Ext.ComponentQuery.query('dutyNoAreaGridPanel')[0];
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