/**
 * 角色表格
 * @author majian <br/>
 *         date:2015-7-10
 * @version 1.0.0
 */
Ext.define('kalix.admin.duty.view.DutyGrid', {
  extend: 'kalix.view.components.common.BaseGrid',
  requires: [
    'kalix.admin.duty.store.DutyStore',
    'kalix.admin.duty.controller.DutyGridController'
  ],
  alias: 'widget.dutyGrid',
  xtype: 'dutyGridPanel',
  store: {
    type: 'dutyStore'
  },
  controller: {
    type: 'dutyGridController',
    cfgForm: 'kalix.admin.duty.view.DutyWindow',
    cfgModel: 'kalix.admin.duty.model.DutyModel'
  },
  autoLoad: false,
  columns: {
    defaults: {flex: 1, renderer: 'addTooltip'},
    items: [
      {
        xtype: 'rownumberer',
        text: "行号",
        width: 50,
        align: 'center',
        flex: 0,
        renderer: this.update
      },
      {text: '编号', dataIndex: 'id', hidden: true},
      {text: '职务名称', dataIndex: 'name'},
      {text: '职务描述', dataIndex: 'comment'},
      {
        text: '所属机构', xtype: 'templatecolumn',
        tpl:'',
        renderer: this.update
      },
      {text: '创建人', dataIndex: 'createBy'},
      {
        text: '创建日期', dataIndex: 'creationDate'
      },
      {
        xtype: 'securityGridColumnCommon',
        verifyItems: [
          {
            iconCls: 'iconfont icon-edit-column',
            permission: 'edit',
            tooltip: '编辑',
            handler: 'onEdit'
          }, {
            iconCls: 'iconfont icon-delete',
            permission: 'delete',
            tooltip: '删除',
            handler: 'onDelete'
          }, {
            iconCls: 'iconfont icon-add-user-column',
            permission: 'addUser',
            tooltip: '添加用户',
            handler: 'onAddUser'
          }
        ]
      }]
  },
  tbar: {
    xtype: 'securityToolbar',
    verifyItems: [
      {
        text: '添加',
        tooltip: '添加职务',
        xtype: 'button',
        permission: 'add',
        iconCls: 'iconfont icon-add',
        handler: 'onAdd'
      }
    ]
  }

});