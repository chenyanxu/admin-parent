/**
 * 功能表格控制器
 *
 * @author majian <br/>
 *         date:2015-7-21
 * @version 1.0.0
 */
Ext.define('kalix.app.function.controller.FunctionGridController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.functionGridController',
    /**
     * 刷新.
     * @returns {Ext.panel.Panel}
     */
    onRefersh: function () {
        var grid = this.getView();
        var store = grid.getStore();
        store.reload();
    },
    /**
     * 打开新增操作.
     * @returns {Ext.panel.Panel}
     */
    onAdd: function () {

        if (this.getView().applicationId == null || this.getView().applicationName == null || this.getView().applicationCode == null) {
            Ext.Msg.alert(CONFIG.ALTER_TITLE_FAILURE, "请选择一个应用!");
            return;
        }
        var rows = this.getView().getSelectionModel().getSelection();
        var addFormPanel = Ext.create('kalix.app.function.view.FunctionAddForm', {
            url: this.getView().getViewModel().get("url")
        });
        addFormPanel.parentPermission = this.getView().applicationCode;
        addFormPanel.down("#applicationIdId").setValue(this.getView().applicationId);
        addFormPanel.down("#applicationName").setValue(this.getView().applicationName);
        if (rows != null && rows.length > 0) {
            if (rows[0] != null) {
                addFormPanel.parentPermission = rows[0].data.permission;
                addFormPanel.down("#parentName").setValue(rows[0].data.name);
                addFormPanel.down("#parentIdId").setValue(rows[0].data.id);
            }
        } else {
            addFormPanel.down("#parentName").setValue("根功能");
            addFormPanel.down("#parentIdId").setValue(-1);
        }
        var win = Ext.create('Ext.Window', {
            width: 400,
            //height: 285,
            border: false,
            modal: true,
            iconCls: 'iconfont icon-add',
            title: this.getView().getViewModel().get("addTitle"),
            items: [addFormPanel]
        });

        win.show();
    },
    /**
     * 打开编辑操作.
     * @param grid
     * @param rowIndex
     * @param colIndex
     */
    onEdit: function (grid, rowIndex, colIndex) {
        var rec = grid.getStore().getAt(rowIndex);
        var editFormPanel = Ext.create('kalix.app.function.view.FunctionEditForm', {
            url: this.getView().getViewModel().get("url")
        });
        editFormPanel.getComponent("applicationIdId").setValue(this.getView().applicationId);
        editFormPanel.getComponent("applicationName").setValue(this.getView().applicationName);
        editFormPanel.getComponent("parentName").setValue(rec.data.parentName);
        editFormPanel.loadRecord(rec);

        var win = Ext.create('Ext.Window', {
            width: 400,
            //height: 285,
            border: false,
            modal: true,
            iconCls: 'iconfont icon-edit',
            title: this.getView().getViewModel().get("editTitle"),
            items: [editFormPanel]
        });

        win.show();
    },
    /**
     * 批量删除操作.
     */
    onDeleteAll: function () {
        var selModel = Ext.getCmp("userDataGrid").getSelectionModel();
        if (selModel.hasSelection()) {
            Ext.Msg.confirm("警告", "确定要删除吗？", function (button) {
                if (button == "yes") {
                    var rows = selModel.getSelection();
                    var ids = "";
                    for (var i = 0; i < rows.length; i++) {
                        if (rows[i] != null && rows[i].id != null) {
                            ids += rows[i].id;
                            if (i + 1 != rows.length) {
                                ids += "_";
                            }
                        }
                    }
                    Ext.Ajax.request({
                        url: "/userDeleteAllServlet?ids=" + ids,
                        method: "GET",
                        callback: function (options, success, response) {
                            var resp = Ext.JSON.decode(response.responseText);
                            Ext.MessageBox.alert(CONFIG.ALTER_TITLE_INFO, resp.msg);
                            if (resp.success) {
                                //var username = Ext.getCmp("username").getValue();
                                //var name = Ext.getCmp("name").getValue();
                                //var sex = Ext.getCmp("sex").getValue();
                                //var status = Ext.getCmp("status").getValue();
                                //var grid = Ext.getCmp("userDataGrid");
                                //var store = grid.getStore();
                                //store.reload({
                                //    params: {
                                //        start: 0,
                                //        limit: pageSize,
                                //        username: username,
                                //        name: name,
                                //        sex: sex,
                                //        status: status
                                //    }
                                //});
                            }
                        }
                    });
                }
            });
        } else {
            Ext.Msg.alert(CONFIG.ALTER_TITLE_ERROR, "请选择要删除的记录！");
        }
    },
    /**
     * 删除单个操作.
     * @param grid
     * @param rowIndex
     * @param colIndex
     */
    onDelete: function (grid, rowIndex, colIndex) {
        var rec = grid.getStore().getAt(rowIndex);
        var deleteUrl = this.getView().getViewModel().get("url");
        Ext.Msg.confirm("警告", "确定要删除吗？", function (button) {
            if (button == "yes") {
                Ext.Ajax.request({
                    url: deleteUrl + "?id=" + rec.id,
                    method: 'DELETE',
                    callback: function (options, success, response) {
                        var resp = Ext.JSON.decode(response.responseText);
                        Ext.MessageBox.alert(CONFIG.ALTER_TITLE_INFO, resp.msg);
                        if (resp.success) {
                            var store = grid.getStore();
                            store.reload();
                        }
                    }
                });
            }
        });
    }
});