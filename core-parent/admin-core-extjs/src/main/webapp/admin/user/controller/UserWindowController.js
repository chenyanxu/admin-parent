/**
 * @author chenyanxu
 */
Ext.define('kalix.admin.user.controller.UserWindowController', {
    extend: 'kalix.controller.BaseWindowController',
    alias: 'controller.userWindowController',
    change:function(target, newValue, oldValue, eOpts ){
        var model=this.getView().lookupViewModel();
        var saveBtn = this.getView().dockedItems.getAt(1).items.getAt(0);

        if(target.fieldLabel=='密码'){
            if( newValue.trim()=='' ||
                model.get('rec').get('confirmPassword')==undefined ||
                model.get('rec').get('confirmPassword').trim()==''){
                saveBtn.disable();
                model.set('passwordRight',false);
            }
            else{
                if(newValue.trim()==model.get('rec').get('confirmPassword').trim()){
                    saveBtn.enable();
                    model.set('passwordRight',true);
                }
                else{
                    saveBtn.disable();
                    model.set('passwordRight',false);
                }
            }
        }
        else{
            if( newValue.trim()=='' ||
              model.get('rec').get('password')==undefined ||
              model.get('rec').get('password').trim()==''){
                saveBtn.disable();
                model.set('passwordRight',false);
            }
            else{
                if(newValue.trim()==model.get('rec').get('password').trim()){
                    saveBtn.enable();
                    model.set('passwordRight',true);
                }
                else{
                    saveBtn.disable();
                    model.set('passwordRight',false);
                }
            }
        }
    },
    onClose:function(){
        var model=this.getView().lookupViewModel();

        if(model.get('passwordRight')==null || model.get('passwordRight')){
            this.callParent(arguments);
        }
        else{
            Ext.Msg.alert(CONFIG.ALTER_TITLE_FAILURE, '密码不一致');
        }
    }
});
