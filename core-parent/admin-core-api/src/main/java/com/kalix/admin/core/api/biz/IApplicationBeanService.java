package com.kalix.admin.core.api.biz;

import com.kalix.admin.core.dto.model.ApplicationDTO;
import com.kalix.admin.core.dto.model.AuthorizationDTO;
import com.kalix.admin.core.entities.ApplicationBean;
import com.kalix.framework.core.api.biz.IBizService;
import com.kalix.framework.core.api.persistence.JsonData;

/**
 * 应用服务接口.
 * @author majian <br/>
 *         date:2015-7-30
 * @version 1.0.0
 */
public interface IApplicationBeanService extends IBizService<ApplicationBean> {
    /**
     * 返回应用树
     * @return
     */
    ApplicationDTO getTreesByAllApplications();

    /**
     * 返回授权树
     * @return
     */
    AuthorizationDTO getAuthorizationTree();

    /**
     * 从配置文件读取应用
     */
    JsonData getApplicationsFromConfig();
}
