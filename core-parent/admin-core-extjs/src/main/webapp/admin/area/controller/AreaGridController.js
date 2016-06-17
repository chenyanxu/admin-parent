/**
 * 区域表格控制器
 *
 * @author majian <br/>
 *         date:2015-7-21
 * @version 1.0.0
 */
Ext.define('kalix.admin.area.controller.AreaGridController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.areaGridController',

    /**
     * 刷新.
     * @returns {Ext.panel.Panel}
     */
    onRefersh: function () {
      console.log('refresh')
        var grid = this.getView();
        var store = grid.getStore();
        console.log(store)
        store.reload();
    },
    /**
     * 打开新增操作.
     * @returns {Ext.panel.Panel}
     */
    onAdd: function () {
        var rows = this.getView().getSelectionModel().getSelection();
        var addFormPanel = Ext.create('kalix.admin.area.view.AreaAddForm', {
            url: CONFIG.restRoot + '/camel/rest/areas'
        });
        if (rows != null && rows.length > 0) {
            if (rows[0] != null) {
                addFormPanel.down("#parentName").setValue(rows[0].data.name);
                addFormPanel.down("#parentIdId").setValue(rows[0].data.id);
            }
        } else {
            addFormPanel.down("#parentName").setValue("根区域");
            addFormPanel.down("#parentIdId").setValue(-1);
        }
        var win = Ext.create('Ext.Window', {
            width: 400,
            //height: 280,
            border: false,
            modal: true,
            iconCls: 'iconfont icon-add',
            title: '新增区域',
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
        var editFormPanel = Ext.create('kalix.admin.area.view.AreaEditForm', {
            url: CONFIG.restRoot + '/camel/rest/areas'
        });
        editFormPanel.down("#parentName").setValue(rec.data.parentName);
        editFormPanel.loadRecord(rec);

        var win = Ext.create('Ext.Window', {
            width: 400,
            border: false,
            modal: true,
            iconCls: 'iconfont icon-edit',
            title: '编辑区域',
            items: [editFormPanel]
        });

        win.show();
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