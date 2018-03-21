package com.kalix.admin.core.biz;

import com.kalix.admin.core.api.biz.IApplicationBeanService;
import com.kalix.admin.core.api.biz.IFunctionBeanService;
import com.kalix.admin.core.api.biz.IRoleBeanService;
import com.kalix.admin.core.api.biz.IWorkGroupBeanService;
import com.kalix.admin.core.api.dao.IApplicationBeanDao;
import com.kalix.admin.core.api.dao.IFunctionBeanDao;
import com.kalix.admin.core.api.dao.IRoleApplicationBeanDao;
import com.kalix.admin.core.dto.model.ApplicationDTO;
import com.kalix.admin.core.dto.model.AuthorizationDTO;
import com.kalix.admin.core.entities.ApplicationBean;
import com.kalix.admin.core.entities.FunctionBean;
import com.kalix.framework.core.api.persistence.JsonData;
import com.kalix.framework.core.api.persistence.JsonStatus;
import com.kalix.framework.core.impl.biz.ShiroGenericBizServiceImpl;
import com.kalix.framework.core.util.Assert;
import com.kalix.framework.core.util.ConfigUtil;
import com.kalix.framework.core.util.SerializeUtil;
import com.kalix.framework.osgi.api.IBundleService;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.osgi.framework.Bundle;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 应用服务实现
 *
 * @author majian <br/>
 *         date:2015-7-27
 * @version 1.0.0
 */
public class ApplicationBeanServiceImpl extends ShiroGenericBizServiceImpl<IApplicationBeanDao, ApplicationBean> implements IApplicationBeanService {
    private static final String FUNCTION_NAME = "应用";
    //    private IApplicationBeanDao dao;
    private IFunctionBeanService functionBeanService;
    private IFunctionBeanDao functionBeanDao;
    private IRoleBeanService roleBeanService;
    private IRoleApplicationBeanDao roleApplicationBeanDao;
    private IWorkGroupBeanService workGroupBeanService;
    private IBundleService bundleService;

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
    public void afterDeleteEntity(Long id, JsonStatus status) {
        functionBeanService.deleteByApplicationId(id);
    }

    @Override
    public void afterUpdateEntity(ApplicationBean entity, JsonStatus status) {
        ApplicationBean applicationBean = (ApplicationBean) entity;
        List<FunctionBean> functionBeans = functionBeanDao.find("select fb from FunctionBean fb where fb.applicationId=?1", entity.getId());
        if (functionBeans != null && !functionBeans.isEmpty()) {
            for (FunctionBean functionBean : functionBeans) {
                if (functionBean.getPermission() != null && functionBean.getPermission().indexOf(":") != -1) {
                    //更新应用时,将应用代码再更新到各个功能的permission中
                    functionBean.setPermission(applicationBean.getCode() + functionBean.getPermission().substring(functionBean.getPermission().indexOf(":"), functionBean.getPermission().length()));
                    functionBeanDao.save(functionBean);
                }
            }
        }
    }

    @Override
    public boolean isUpdate(ApplicationBean entity, JsonStatus status) {
        Assert.notNull(entity, "实体不能为空.");
        ApplicationBean bean = (ApplicationBean) entity;
        List<ApplicationBean> beans = dao.find("select ob from ApplicationBean ob where ob.name = ?1", bean.getName());
        if (beans != null && beans.size() > 0) {
            ApplicationBean applicationBean = beans.get(0);
            if (applicationBean.getId() != entity.getId()) {
                status.setFailure(true);
                status.setMsg(FUNCTION_NAME + "已经存在,请检查名称！");
                return false;
            }
        }
        beans = dao.find("select ob from ApplicationBean ob where ob.code = ?1", bean.getCode());
        if (beans != null && beans.size() > 0) {
            ApplicationBean applicationBean = beans.get(0);
            if (applicationBean.getId() != entity.getId()) {
                status.setFailure(true);
                status.setMsg(FUNCTION_NAME + "已经存在,请检查代码！");
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean isSave(ApplicationBean entity, JsonStatus status) {
        Assert.notNull(entity, "实体不能为空.");
        ApplicationBean bean = (ApplicationBean) entity;
        List<ApplicationBean> beans = dao.find("select ob from ApplicationBean ob where ob.name = ?1 ", bean.getName());
        if (beans != null && beans.size() > 0) {
            status.setFailure(true);
            status.setMsg(FUNCTION_NAME + "已经存在,请检查名称！");
            return false;
        }
        beans = dao.find("select ob from ApplicationBean ob where ob.code = ?1 ", bean.getCode());
        if (beans != null && beans.size() > 0) {
            status.setFailure(true);
            status.setMsg(FUNCTION_NAME + "已经存在,请检查代码！");
            return false;
        }
        return true;
    }


    @Override
    public ApplicationDTO getTreesByAllApplications() {
        ApplicationDTO root = new ApplicationDTO();
        root.setId(-1L);
        //List<ApplicationBean> beans = dao.getAll();
        // 从bundle获取applications信息
        List<ApplicationBean> beans = getApplicationsFromConfig(null).getData();
        // 从数据库获取applications信息
        List<ApplicationBean> beansBydb = dao.findByNativeSql("select * from sys_application", ApplicationBean.class, null);

        if (beans != null && !beans.isEmpty()) {
            List<ApplicationBean> newBeans = new ArrayList<>();
            if (beansBydb != null && !beansBydb.isEmpty()) {
                beans.stream().forEach((bean) -> {
                    // 功能管理功能用数据库Id查sys_function
                    beansBydb.stream().forEach(appBean -> {
                        if (bean.getName().equals(appBean.getName())) {
                            bean.setId(appBean.getId());
                        }
                    });
                    newBeans.add(bean);
                });
            }
            for (ApplicationBean applicationBean : newBeans) {
                Mapper mapper = new DozerBeanMapper();
                ApplicationDTO applicationDTO = mapper.map(applicationBean, ApplicationDTO.class);
                applicationDTO.setLeaf(true);
                applicationDTO.setText(applicationBean.getName());
                applicationDTO.setIconCls(applicationBean.getIconCls());
                root.getChildren().add(applicationDTO);
            }
        }
        return root;
    }

    @Override
    public AuthorizationDTO getAuthorizationTree() {
        AuthorizationDTO root = new AuthorizationDTO();
        root.setId(-1L);
        List<ApplicationBean> beans = dao.getAll();
        if (beans != null && beans.size() > 0) {
            if (beans != null && beans.size() > 0) {
                for (ApplicationBean applicationBean : beans) {
                    Assert.notNull(applicationBean, "应用不能为空");
                    Mapper mapper = new DozerBeanMapper();
                    AuthorizationDTO applicationDTO = mapper.map(applicationBean, AuthorizationDTO.class);
                    applicationDTO.setParentId(-1L);
                    applicationDTO.setLeaf(true);
                    applicationDTO.setChecked(true);
                    applicationDTO.setExpanded(true);
                    List<FunctionBean> functionBeans = functionBeanDao.find("select ob from FunctionBean ob where ob.applicationId = ?1", applicationBean.getId());
                    if (functionBeans != null && functionBeans.size() > 0) {
                        applicationDTO.setChecked(true);
                        applicationDTO.setLeaf(false);
                        //返回该应用下所有根功能
                        List<FunctionBean> rootFunctions = functionBeanService.getRootElements(functionBeans);
                        if (rootFunctions != null && rootFunctions.size() > 0) {
                            for (FunctionBean functionBean : rootFunctions) {
                                AuthorizationDTO functionDTO = mapper.map(functionBean, AuthorizationDTO.class);
                                functionDTO.setParentId(applicationBean.getId());
                                functionDTO.setLeaf(functionBean.getIsLeaf() == 0 ? false : true);
                                functionDTO.setText(functionBean.getName());
                                functionDTO.setChecked(true);
                                functionDTO.setExpanded(true);
                                functionBeanService.getChilden(functionDTO, functionBeans, mapper);
                                applicationDTO.getChildren().add(functionDTO);
                            }
                        }
                    }
                    applicationDTO.setText(applicationBean.getName());
                    root.getChildren().add(applicationDTO);
                }
            }
        }
        return root;
    }

    @Override
    public JsonData getApplicationsFromConfig(String jsonStr) {
        Map<String, String> jsonMap = null;
        String filterName = "";
        if (jsonStr != null && !jsonStr.isEmpty()) {
            jsonMap = SerializeUtil.json2Map(jsonStr);
            filterName = jsonMap.get("%name%");
        }
        List<Bundle> bundles = this.bundleService.getBundleList("Bundle-Classifier:IApplication");
        JsonData jsonData = new JsonData();

        for (Bundle bundle : bundles) {
            //String category=bundle.getHeaders().get("Bundle-Category").toString();
            String appName = bundle.getHeaders().get("Bundle-ApplicationId").toString();
            //if (category!=null) {
            if (appName != null) {
                //String[] categorySplit=category.split(" ");
                //if(categorySplit.length==3){
                String configName = "config." + appName + ".app";
                String code = ConfigUtil.getConfigProp("APPLICATION_APP_ID", configName).toString();
                String text = ConfigUtil.getConfigProp("APPLICATION_APP_TEXT", configName).toString();
                String iconCls = ConfigUtil.getConfigProp("APPLICATION_APP_ICONCLS", configName).toString();
                String db_id = ConfigUtil.getConfigProp("APPLICATION_APP_DB_ID", configName).toString();

                if (filterName != null && !filterName.isEmpty()) {
                    if (text.contains(filterName)) {
                        jsonData = makeAppBean(bundle, jsonData, db_id, code, text, iconCls);
                    }
                } else {
                    jsonData = makeAppBean(bundle, jsonData, db_id, code, text, iconCls);
                }
            }
        }

        jsonData.setTotalCount((long) jsonData.getData().size());

        return jsonData;
    }

    private JsonData makeAppBean(Bundle bundle, JsonData jsonData, String db_id, String code, String text, String iconCls) {
        ApplicationBean appBean = new ApplicationBean();
        appBean.setId(Long.parseLong(db_id));
        appBean.setCode(code);
        appBean.setName(text);
        appBean.setIconCls(iconCls);
        if (bundle.getState() == Bundle.ACTIVE) {
            appBean.setActive(true);
        } else {
            appBean.setActive(false);
        }
        jsonData.getData().add(appBean);
        return jsonData;
    }

    public ApplicationBeanServiceImpl() {
        super.init(ApplicationBean.class.getName());
    }

    public void setWorkGroupBeanService(IWorkGroupBeanService workGroupBeanService) {
        this.workGroupBeanService = workGroupBeanService;
    }

    public void setRoleApplicationBeanDao(IRoleApplicationBeanDao roleApplicationBeanDao) {
        this.roleApplicationBeanDao = roleApplicationBeanDao;
    }

    public void setRoleBeanService(IRoleBeanService roleBeanService) {
        this.roleBeanService = roleBeanService;
    }

    public void setFunctionBeanDao(IFunctionBeanDao functionBeanDao) {
        this.functionBeanDao = functionBeanDao;
    }

    public void setFunctionBeanService(IFunctionBeanService functionBeanService) {
        this.functionBeanService = functionBeanService;
    }

    public IBundleService getBundleService() {
        return bundleService;
    }

    public void setBundleService(IBundleService bundleService) {
        this.bundleService = bundleService;
    }
}