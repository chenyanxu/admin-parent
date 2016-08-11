/**
 * 应用表格控制器
 *
 * @author
 * @version 1.0.0
 */
Ext.define('kalix.admin.application.controller.ApplicationGridController', {
    extend: 'kalix.controller.BaseGridController',
    alias: 'controller.applicationGridController',
    onAppStartStop: function () {
        var store = arguments[0].getStore()
        var model = arguments[5];
        var url = '';

        if (model.get('status')) {
            url = CONFIG.restRoot + '/camel/rest/osgi/bundle/stop?id=' + model.get('code');
        }
        else {
            url = CONFIG.restRoot + '/camel/rest/osgi/bundle/start?id=' + model.get('code');
        }

        Ext.Ajax.request({
            url: url,
            scope: {store: store},
            success: function (response, opts) {
                var obj = Ext.decode(response.responseText);

                store.load();
                kalix.Notify.success(obj.msg, CONFIG.ALTER_TITLE_SUCCESS);
            },
            failure: function (response, opts) {
                var obj = Ext.decode(response.responseText);

                kalix.Notify.alert(obj.msg,CONFIG.ALTER_TITLE_ERROR );
            }
        });
    }
});