/**
 * 插件模型
 *
 * @author
 * @version 1.0.0
 */

Ext.define('kalix.app.plugin.model.PluginModel', {
    extend: 'kalix.model.BaseModel',

    fields: [{
        name: 'name',
    }, {
        name: 'code',
    }, {
        name: 'remark',
    }, {
        name: 'status',
    }
    ]
});
