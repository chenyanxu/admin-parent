/**
 * @author chenyanxu
 */
Ext.define('kalix.admin.dict.component.DictGridColumn', {
    extend: 'Ext.grid.column.Template',
    tpl: "",
    alias: 'widget.dictGridColumn',
    xtype: 'dictGridColumn',
    colorConfig: null,
    listeners: {
        beforerender: function () {
            var store = Ext.app.Application.instance.getApplication().getStore('dictNoPageStore');

            store.filter('type', this.dictType);

            var data = store.getData().clone().items;

            if (data.length > 0) {
                var tplStr = '';

                for (var idx = 0; idx < data.length; ++idx) {
                    var tempValue = data[idx].data.value;
                    var tempLabel = data[idx].data.label;

                    if (this.colorConfig) {
                        if (this.colorConfig[tempLabel]) {
                            tplStr += '<tpl if="' + this.dataIndex + '==' + tempValue + '"><span style="color:' + this.colorConfig[tempLabel] + '">' + tempLabel + '</span></tpl>';
                        }
                        else {
                            tplStr += '<tpl if="' + this.dataIndex + '==' + tempValue + '"><span style="color:' + this.colorConfig['default'] + '">' + tempLabel + '</span></tpl>';
                        }
                    }
                    else {
                        tplStr += '<tpl if="' + this.dataIndex + '==' + tempValue + '">' + tempLabel + '</tpl>';
                    }
                }

                var tpl = new Ext.XTemplate(tplStr);
                this.tpl = tpl;
            }
            else {
                this.tpl = "<tpl>{" + this.dataIndex + "}</tpl>"
            }

            return true;
        }
    }
});