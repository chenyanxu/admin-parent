/**
 * 添加用户组件
 *
 * @author majian <br/>
 *         date:2015-7-28
 * @version 1.0.0
 */
Ext.define('kalix.admin.user.view.UserAddItemSelector', {
    extend: 'Ext.ux.form.ItemSelector',
    xtype: 'userAddItemSelector',
    anchor: '100%',
    //imagePath: '/core-web/ext-5.1.0/ux/css/images/',
    displayField: 'name',
    valueField: 'id',
    //allowBlank: false,
    //blankText: '请选择用户!',
    msgTarget: 'side',
    fromTitle: '可选用户',
    toTitle: '已选用户',
    items: []
});