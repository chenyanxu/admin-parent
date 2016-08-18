/**
 * @author chenyanxu
 */
Ext.define('kalix.admin.user.controller.UserKeyWindowController', {
    extend: 'kalix.admin.user.controller.UserWindowController',
    alias: 'controller.userKeyWindowController',
    onClose:function(){
        var model=this.getView().lookupViewModel();

        model.set(model.modified);
    }
});
