/**
 * 职位表格控制器
 *
 * @author zangyanming <br/>
 *         date:2016-3-10
 * @version 1.0.0
 */
Ext.define('kalix.admin.duty.controller.DutyGridController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.dutyGridController',
    mixins: {
        userRelation: 'kalix.admin.common.relation.UserRelation'
    },
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
        var OrgTreeList = this.getView().findParentByType('panel').items.getAt(0).items.getAt(0);
        var org_selected_row = OrgTreeList.getSelectionModel().getSelection();
        if(org_selected_row.length<=0){
            Ext.Msg.alert(CONFIG.ALTER_TITLE_FAILURE, '请选择一个机构!');
            return;
        }
        var addFormPanel = Ext.create('kalix.admin.duty.view.DutyAddForm');
        var model=Ext.create('Ext.data.Model');
        addFormPanel.lookupViewModel().set('rec',model);
        model.set('orgid',org_selected_row[0].data.id);
        model.set('orgName',org_selected_row[0].data.name);
        model.modified = {};
        model.dirty = false;
        var win = Ext.create('Ext.Window', {
            width: 400,
            border: false,
            modal: true,
            iconCls: 'iconfont icon-add',
            title: this.getView().getViewModel().get('addTitle'),
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
        var editFormPanel = Ext.create('kalix.admin.duty.view.DutyEditForm');
        var model=Ext.create('Ext.data.Model');
        editFormPanel.lookupViewModel().set('rec',model);
        model.set('id',rec.data.id);
        model.set('orgid',rec.data.orgid);
        var OrgTreeList = this.getView().findParentByType('panel').items.getAt(0).items.getAt(0);
        var org_selected_row = OrgTreeList.getSelectionModel().getSelection();
        model.set('orgName',org_selected_row[0].data.name);
        model.set('name',rec.data.name);
        model.set('comment',rec.data.comment);
        model.modified = {};
        model.dirty = false;
        var win = Ext.create('Ext.Window', {
            width: 400,
            border: false,
            modal: true,
            iconCls: 'iconfont icon-edit',
            title: this.getView().getViewModel().get('editTitle'),
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
        var deleteUrl = this.getView().getViewModel().get('url');
        Ext.Msg.confirm('警告', '确定要删除吗？', function (button) {
            if (button == 'yes') {
                Ext.Ajax.request({
                    url: deleteUrl + '/' + rec.id,
                    method: 'DELETE',
                    callback: function (options, success, response) {
                        var resp = Ext.JSON.decode(response.responseText);
                        if (resp != null && resp.success) {
                            kalix.Notify.success(resp.msg, CONFIG.ALTER_TITLE_SUCCESS);
                            var store = grid.getStore();
                            store.reload();
                        } else {
                            Ext.Msg.alert(CONFIG.ALTER_TITLE_FAILURE, resp.msg);
                        }
                    }
                });
            }
        });
    }
});