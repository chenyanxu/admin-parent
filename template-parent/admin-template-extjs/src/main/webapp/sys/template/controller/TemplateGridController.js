/**
 * 模板表格控制器
 *
 * @author
 * @version 1.0.0
 */
Ext.define('kalix.sys.template.controller.TemplateGridController', {
    extend: 'kalix.controller.BaseGridController',
    alias: 'controller.templateGridController',
    onItemClick: function (view, record, index, eOpts ) {
        var references = this.getView().findParentByType('baseContainer').findParentByType('baseContainer').controller.getReferences();
        var searchForm = references.myform;
        var templateId = searchForm.items.getAt(0);
        templateId.originalValue = record.data.id;
        templateId.setValue(record.data.id);

        var grid = references.mygrid;
        grid.store.reload();
    }
});