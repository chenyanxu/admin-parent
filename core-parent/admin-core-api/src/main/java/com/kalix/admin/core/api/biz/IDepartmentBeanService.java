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

    void deleteByOrgId(Long orgId);

    DepartmentDTO getAll();

    DepartmentDTO getAllByOrgId(Long orgId);

    List getUsersByDepartmentId(long id);

    JsonData getUserAll();

    JsonData getUserAllAndDepartmentUsers(long orgId);

    JsonStatus saveDepartmentUsers(long orgId, String userIds);
}
