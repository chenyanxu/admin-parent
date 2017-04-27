/**
 * 审计首页
 *
 * @author
 * @version 1.0.0
 */
Ext.define('kalix.sys.audit.Main', {
  extend: 'kalix.container.BaseContainer',
  requires: [
    'kalix.sys.audit.view.AuditGrid',
    'kalix.sys.audit.view.AuditSearchForm',
    'kalix.sys.audit.viewModel.AuditViewModel'
  ]
  ,
  storeId: 'auditStore',
  viewModel: 'auditViewModel',
  items: [
    {
      title: '审计查询',
      xtype: 'auditSearchForm'
    }, {
      xtype: 'auditGridPanel',
      id: 'auditGridPanel',
      title: '审计列表'
    }
  ]
});
