/**
 * 机构列表
 * @author zangyanming <br/>
 *         date:2016-3-10
 * @version 1.0.0
 */
Ext.define('kalix.admin.org.view.UserOrgTreeList', {
    extend: 'kalix.view.components.common.BaseTreeList',
    alias: 'widget.userorgtreelist',
    xtype: 'userorgtreelist',
    title: '机构列表',
    store: Ext.create('kalix.admin.org.store.UserOrgStore'),
    iconCls: 'iconfont icon-organization-management'
});