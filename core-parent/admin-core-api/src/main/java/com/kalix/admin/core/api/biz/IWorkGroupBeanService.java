package com.kalix.admin.core.api.biz;

import com.kalix.admin.core.entities.WorkGroupBean;
import com.kalix.admin.core.entities.WorkGroupUserBean;
import com.kalix.framework.core.api.biz.IBizService;
import com.kalix.framework.core.api.persistence.JsonStatus;
import com.kalix.framework.core.api.persistence.JsonData;

import java.util.List;

/**
 * 工作组管理服务接口
 * @author majian <br/>
 *         date:2015-7-27
 * @version 1.0.0
 */
public interface IWorkGroupBeanService extends IBizService<WorkGroupBean> {
    /**
     * 查询工作组
     * @param bean
     * @return
     */
    List<WorkGroupBean> query(WorkGroupBean bean);

    /**
     * 返回全部工作组
     * @param page
     * @param limit
     * @return
     */
    JsonData getAllWorkGroup(int page, int limit);

    /**
     * 返回工作组下所有用户
     * @param id
     * @return
     */
    List getUserIdsByWorkGroupId(String id);

    /**
     * 返回工作组下所有角色
     * @param id
     * @return
     */
    List getRoleIdsByWorkGroupId(String id);

    /**
     * 保存工作组与用户关联
     * @return
     */
    JsonStatus saveWorkGroupUsers(List ids);

    /**
     * 保存工作组与角色关联
     * @return
     */
    JsonStatus saveWorkGroupRoles(List ids);

    /**
     * 返回用户下所有关联工作组
     * @param userId
     * @return
     */
    List<WorkGroupUserBean> getWorkGroupUserBeanByUserId(String userId);
}
