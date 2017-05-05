package com.kalix.admin.state.biz;

import com.kalix.admin.state.api.biz.IStateMachineBeanService;
import com.kalix.admin.state.api.dao.IStateMachineBeanDao;
import com.kalix.admin.state.entities.StateMachineBean;
import com.kalix.framework.core.api.persistence.JsonData;
import com.kalix.framework.core.api.persistence.JsonStatus;
import com.kalix.framework.core.impl.biz.ShiroGenericBizServiceImpl;
import com.kalix.framework.core.util.Assert;
import com.kalix.framework.core.util.SerializeUtil;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;

/**
 * 状态机管理服务实现
 *
 * @author hqj date:2017-4-25
 * @version 1.0.0
 */
public class StateMachineBeanServiceImpl extends ShiroGenericBizServiceImpl<IStateMachineBeanDao, StateMachineBean> implements IStateMachineBeanService {

    private static final String FUNCTION_NAME = "状态机";

    public StateMachineBeanServiceImpl() {
        super.init(StateMachineBean.class.getName());
    }

    @Override
    public void beforeSaveEntity(StateMachineBean entity, JsonStatus status) {
        Assert.notNull(entity, "实体不能为空.");

        String userName = shiroService.getCurrentUserLoginName();
        if (userName != null) {
            entity.setCreateBy(userName);
            entity.setUpdateBy(userName);
        }
    }

    @Override
    public boolean isUpdate(StateMachineBean entity, JsonStatus status) {
        Assert.notNull(entity, "实体不能为空.");

        // 校验状态机key
        if (entity.getKey() != null) {
            if (isHasKey(entity.getKey())) {
                status.setSuccess(false);
                status.setMsg(FUNCTION_NAME + "key已经存在！");
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean isSave(StateMachineBean entity, JsonStatus status) {
        Assert.notNull(entity, "实体不能为空.");

        // 校验状态机key
        if (entity.getKey() == null) {
            status.setSuccess(false);
            status.setMsg(FUNCTION_NAME + "key必填！");
            return false;
        } else {
            if (isHasKey(entity.getKey())) {
                status.setSuccess(false);
                status.setMsg(FUNCTION_NAME + "key已经存在！");
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean isDelete(Long entityId, JsonStatus status) {
        if (dao.get(entityId) == null) {
            status.setFailure(true);
            status.setMsg(FUNCTION_NAME + "已经被删除！");
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public JsonStatus updateEntity(StateMachineBean entity) {
        JsonStatus jsonStatus = new JsonStatus();
        try {
            if (isUpdate(entity, jsonStatus)) {
                entity.setUpdateBy(shiroService.getCurrentUserLoginName());
                super.updateEntity(entity);
                jsonStatus.setSuccess(true);
                jsonStatus.setMsg("更新" + FUNCTION_NAME + "成功！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            jsonStatus.setFailure(true);
            jsonStatus.setMsg("更新" + FUNCTION_NAME + "失败！");
        }
        return jsonStatus;
    }

    // 校验状态机key是否存在
    private boolean isHasKey(String key) {
        if (key != null) {
            Map<String, String> params = new HashMap<String, String>();
            params.put("key", key);
            String jsonStr = SerializeUtil.serializeJson(params);
            JsonData jsonData = super.getAllEntityforReport(jsonStr);
            if (jsonData.getTotalCount() > 0) {
                return true;
            }
        }
        return false;
    }
}