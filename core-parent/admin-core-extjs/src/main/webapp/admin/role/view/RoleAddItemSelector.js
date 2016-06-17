/**
 * 添加角色组件
 *
 * @author majian <br/>
 *         date:2015-7-28
 * @version 1.0.0
 */
Ext.define('kalix.admin.role.view.RoleAddItemSelector', {
    extend: 'Ext.ux.form.ItemSelector',
    xtype: 'roleAddItemSelector',
    anchor: '100%',
    displayField: 'name',
    valueField: 'id',
    //allowBlank: false,
    //blankText: '请选择用户!',
    msgTarget: 'side',
    fromTitle: '可选角色',
    toTitle: '已选角色',
    items: []
});