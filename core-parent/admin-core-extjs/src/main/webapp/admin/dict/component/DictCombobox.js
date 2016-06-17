/**
 * @author chenyanxu
 */
Ext.define('kalix.admin.dict.component.DictCombobox', {
    extend: 'Ext.form.field.ComboBox',
    alias: 'widget.dictCombobox',
    editable: false,
    xtype: 'dictCombobox',
    queryMode: 'local',
    valueField: 'value',
    displayField: 'label',
    //selectOnFocus:true,
    listeners:{
        beforerender:function(){
            var store = Ext.app.Application.instance.getApplication().getStore('dictNoPageStore');
            var tempStore = Ext.create('Ext.data.Store');

            store.filter('type',this.dictType);
            tempStore.setData(store.getData().clone());
            this.setStore(tempStore);

            return true;
        }
    }
});