/**
 * @author chenyanxu
 */
Ext.define('kalix.admin.org.view.OrgForm', {
    extend: 'kalix.view.components.common.BaseTreeForm',
    requires: [
        'kalix.admin.org.controller.OrgFormController'
    ],
    alias: 'widget.orgForm',
    xtype: 'orgForm',
    controller: {
        type: 'orgFormController'
    },
    items: [
        {xtype: 'hiddenfield', name: 'id', bind:{value:'{rec.id}'}},
        {xtype: 'hiddenfield', name: 'parentId', bind:{value:'{rec.parentId}'}},
        {xtype: 'hiddenfield', name: 'isLeaf',value:'1'},
        {
            fieldLabel: '上级机构',
            editable:false,
            bind:{
                value:'{rec.parentName}'
            }
        },
        {
            fieldLabel: '机构名称',
            name:'name',
            allowBlank: false,
            blankText: '机构名称不能为空!',
            bind:{
                value:'{rec.name}'
            }
        }
        /*{
            fieldLabel: '机构代码',
            name: 'code',
            allowBlank: false,
            blankText: '机构不能为空!',
            bind:{
                value:'{rec.code}'
            }
         }*/
    ]
});