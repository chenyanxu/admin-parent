/**
 * 审计模型
 *
 * @author
 * @version 1.0.0
 */

Ext.define('kalix.admin.audit.viewModel.AuditViewModel', {
    extend: 'Ext.app.ViewModel',
    alias: 'viewmodel.auditViewModel',
    data: {
        // batchDeleteUrl为执行批量删除服务的地址
        batchDeleteUrl:'/kalix/camel/rest/audits/remove',
        rec: null,
        validation: {},  //验证错误信息
        icon: '',
        title: '',
        view_operation: false,
        view_title: '查看审计',
        add_title: '添加审计',
        edit_title: '修改审计',
        add_image_path: CONFIG.restRoot + '/roffice/audit/resources/images/audit_add.png',
        view_image_path: CONFIG.restRoot + '/roffice/audit/resources/images/audit_view.png',
        edit_image_path: CONFIG.restRoot + '/roffice/audit/resources/images/audit_edit.png',
    }
});