package com.kalix.admin.core.api.biz;

import com.kalix.admin.core.entities.RoleFunctionBean;
import com.kalix.admin.core.dto.model.AuthorizationDTO;
import com.kalix.admin.core.dto.model.FunctionDTO;
import com.kalix.admin.core.entities.FunctionBean;
import com.kalix.framework.core.api.biz.IBizService;
import org.dozer.Mapper;

import java.util.List;

/**
 * 功能服务接口.
 * @author majian <br/>
 *         date:2015-7-30
 * @version 1.0.0
 */
public interface IFunctionBeanService extends IBizService<FunctionBean> {
    /**
     * 返回指定应用下所有功能节点
     * @param id
     * @return
     */
    FunctionDTO getAllByApplicationId(long id);

    /**
     * 删除指定应用下所有功能
     * @param id
     */
    void deleteByApplicationId(long id);

    /**
     * 返回所有根节点
     * @param elements
     * @return
     */
    List<FunctionBean> getRootElements(List<FunctionBean> elements);

    /**
     * 返回子节点
     * @param root
     * @param elements
     * @param mapper
     */
    void getChilden(AuthorizationDTO root, List<FunctionBean> elements, Mapper mapper);

    /**
     * 返回子节点并设置选中状态
     * @param root
     * @param elements
     * @param mapper
     */
    void getChilden(AuthorizationDTO root, List<FunctionBean> elements, Mapper mapper, List<RoleFunctionBean> roleFunctionBeans, boolean defaultChecked);
}
