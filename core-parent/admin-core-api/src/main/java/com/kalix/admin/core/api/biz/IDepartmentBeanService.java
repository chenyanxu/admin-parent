package com.kalix.admin.core.api.biz;

import com.kalix.admin.core.dto.model.DepartmentDTO;
import com.kalix.admin.core.entities.DepartmentBean;
import com.kalix.framework.core.api.biz.IBizService;
import com.kalix.framework.core.api.persistence.JsonStatus;
import com.kalix.framework.core.api.persistence.JsonData;

import java.util.List;

/**
 * 部门管理服务接口
 * @author majian <br/>
 *         date:2015-7-23
 * @version 1.0.0
 */
public interface IDepartmentBeanService extends IBizService<DepartmentBean> {

    void deleteByOrgId(String orgId);

    DepartmentDTO getAll();

    DepartmentDTO getAllByOrgId(String orgId);

    List getUsersByDepartmentId(String id);

    JsonData getUserAll();

    JsonData getUserAllAndDepartmentUsers(String orgId);

    JsonStatus saveDepartmentUsers(String orgId, String userIds);
}
