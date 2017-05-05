/**
 * 模板表格控制器
 *
 * @author
 * @version 1.0.0
 */
Ext.define('kalix.sys.template.controller.TemplateWindowController', {
    extend: 'kalix.controller.BaseWindowController',
    alias: 'controller.templateWindowController',

    onPress: function (target, e, eOpts) {
        var model = this.getView().lookupViewModel().get('rec');

        if (e.keyCode == 18) {
            var panel = Ext.create('Ext.grid.Panel', {
                x: 100,
                y: 100,
                renderTo: Ext.getBody(),//x,y,renderTo:Ext.getBody()初始化panel的位置
                floating: true,//true
                viewModel: Ext.create('Ext.app.ViewModel', {rec: model}),
                frame: true,//圆角边框
                width: 125,
                height: 300,
                closable: true,
                store: Ext.create('kalix.sys.templateconfig.store.TemplateConfigStore', {
                    autoLoad: true,
                    proxyUrl: CONFIG.restRoot + '/camel/rest/templateconfigs'
                }),
                columns: [
                    {
                        text: '属性名称',
                        dataIndex: 'fieldName'
                    }
                ],
                listeners: {
                    itemclick: function (target, record, item, index, e, eOpts) {
                        var model = this.getViewModel().config.rec;
                        var value = "${" + record.get("fieldDesc") + "}";
                        model.set('content',model.get('content')+ value);

                        //var twi = Ext.getCmp('TemplateWindowId');
                        //var ta = twi.lastValue;
                        //var oriValue = ta.getValue().toString();
                        //ta.setValue(oriValue.substring(0, twi.selectionStart) + value + oriValue.substring(this.selectionEnd));
                        //twi.selectionStart += value.length;
                        //twi.selectionEnd += value.length;

                        this.close();
                    }
                }
            }
            ).show();
        }
    }
});