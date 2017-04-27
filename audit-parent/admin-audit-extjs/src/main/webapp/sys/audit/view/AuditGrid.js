/**
 * 审计表格
 * @author
 * @version 1.0.0
 */
Ext.define('kalix.sys.audit.view.AuditGrid', {
  extend: 'kalix.view.components.common.BaseGrid',
  requires: [
    'kalix.sys.audit.controller.AuditGridController',
    'kalix.sys.audit.store.AuditStore'
  ],
  alias: 'widget.auditGrid',
  xtype: 'auditGridPanel',
  controller: {
    type: 'auditGridController',
    cfgViewForm: 'kalix.sys.audit.view.AuditViewWindow',
    cfgModel: 'kalix.sys.audit.model.AuditModel'
  },
  store: {
    type: 'auditStore'
  },
  forceFit: true,
  selModel: {selType: 'checkboxmodel', mode: 'simple'},
  columns: [
    {
      xtype: 'rownumberer'
    },
    {text: '编号', dataIndex: 'id', hidden: true},
    {text: '应用名称', dataIndex: 'appName'},
    {text: '功能名称', dataIndex: 'funName'},
    {text: '操作人', dataIndex: 'actor'},
    {text: '操作', dataIndex: 'action'},
    {text: '操作内容', dataIndex: 'content', flex: 2},
    {
      text: '操作时间',
      dataIndex: 'creationDate'
    },
    {
      xtype: 'securityGridColumnCommon',
      verifyItems: [
        {
          iconCls: 'iconfont icon-view-column',
          permission: 'view',
          tooltip: '查看',
          handler: 'onView'
        }, {
          iconCls: 'iconfont icon-delete',
          permission: 'delete',
          tooltip: '删除',
          handler: 'onDelete'
        }]
    }
  ]
  ,
  tbar: {
    xtype: 'securityToolbar',
    verifyItems: [
      {
        text: '批量删除',
        xtype: 'button',
        permission: 'batchDelete',
        iconCls: 'iconfont icon-delete',
        handler: 'onBatchDelete'
      }
    ]
  }
});