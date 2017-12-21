package com.kalix.admin.core.biz;

import com.kalix.admin.core.api.biz.IFunctionBeanService;
import com.kalix.admin.core.api.dao.IFunctionBeanDao;
import com.kalix.admin.core.dto.model.AuthorizationDTO;
import com.kalix.admin.core.dto.model.FunctionDTO;
import com.kalix.admin.core.entities.FunctionBean;
import com.kalix.admin.core.entities.RoleFunctionBean;
import com.kalix.framework.core.api.persistence.JsonStatus;
import com.kalix.framework.core.impl.biz.ShiroGenericBizServiceImpl;
import com.kalix.framework.core.util.Assert;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * 功能服务实现
 * @author majian <br/>
 *         date:2015-7-27
 * @version 1.0.0
 */
public class FunctionBeanServiceImpl extends ShiroGenericBizServiceImpl<IFunctionBeanDao, FunctionBean> implements IFunctionBeanService {
    private static final String FUNCTION_NAME = "功能";

    public FunctionBeanServiceImpl() {
        super.init(FunctionBean.class.getName());
    }

    public void removeChildren(Long id){
        List<FunctionBean> functionBeans = dao.find("select ob from FunctionBean ob where ob.parentId = ?1", id);
        if(functionBeans!=null&&functionBeans.size()>0){
            for(FunctionBean functionBean:functionBeans){
                if(functionBean.getIsLeaf()==0){ //存在子节点
                    removeChildren(functionBean.getId());
                }
                dao.remove(functionBean.getId());
            }
        }
    }

    @Override
    @Transactional
    public void doDelete(long entityId, JsonStatus jsonStatus) {
        List<FunctionBean> functionBeans = dao.find("select ob from FunctionBean ob where ob.id = ?1", entityId);
        if(functionBeans!=null&&!functionBeans.isEmpty()) {
            removeChildren(entityId);
            FunctionBean function=functionBeans.get(0);
            dao.remove(entityId);
            updateParent(function.getParentId());
            jsonStatus.setSuccess(true);
            jsonStatus.setMsg("删除" + FUNCTION_NAME + "成功！");
        }else{
            jsonStatus.setSuccess(true);
            jsonStatus.setMsg(FUNCTION_NAME + "已经被删除！");
        }
    }

    /**
     * 如果父节点下再没有子节点,将更新父节点状态
     * @param parentId
     */
    public void updateParent(Long parentId){
        List<FunctionBean> functionBeans = dao.find("select ob from FunctionBean ob where ob.id = ?1", parentId); //获得父节点
        if(functionBeans!=null&&functionBeans.size()>0){
            List<FunctionBean> children = dao.find("select ob from FunctionBean ob where ob.parentId = ?1", parentId); //获得父节点
            if(children==null||children.isEmpty()) {
                FunctionBean parent = functionBeans.get(0);
                parent.setIsLeaf(1L);
                dao.save(parent);
            }
        }
    }

    @Override
    @Transactional
    public void doUpdate(FunctionBean entity, JsonStatus jsonStatus) {
        Assert.notNull(entity, "实体不能为空.");
        FunctionBean oldBean = dao.get(entity.getId());
        if(oldBean!=null){
            FunctionBean functionBean=(FunctionBean)entity;
            oldBean.setName(functionBean.getName());
            oldBean.setRemark(functionBean.getRemark());
            oldBean.setUpdateBy(functionBean.getUpdateBy());
            //修改功能代码后再更新permission字段
            oldBean.setPermission(oldBean.getPermission().replace(oldBean.getCode(),functionBean.getCode()));
            //更新下所有子功能
            updatePermission(oldBean.getCode(),functionBean.getCode(),oldBean);
            oldBean.setCode(functionBean.getCode());
            dao.save(oldBean);
            jsonStatus.setSuccess(true);
            jsonStatus.setMsg("修改成功！");
        }
    }

    @Override
    public void afterSaveEntity(FunctionBean entity, JsonStatus status) {
        Assert.notNull(entity, "实体不能为空.");
        FunctionBean bean=(FunctionBean)entity;
        if(bean.getParentId()!=-1){
            FunctionBean parentFunctionBean = dao.get(bean.getParentId());
            if(parentFunctionBean!=null&&parentFunctionBean.getIsLeaf()==1){
                parentFunctionBean.setIsLeaf(0L);
                dao.save(parentFunctionBean);
            }
        }
    }

    private void updatePermission(String oldTopCode,String newTopCode,FunctionBean parentFunction){
        //如果下面有叶子节点
        if(parentFunction.getIsLeaf()==0){
            List<FunctionBean> childFunction = dao.find("select fb from FunctionBean fb where fb.parentId=?1", parentFunction.getId());
            if(childFunction!=null&&!childFunction.isEmpty()){
                for(FunctionBean functionBean:childFunction){
                    functionBean.setPermission(functionBean.getPermission().replace(oldTopCode,newTopCode));
                    dao.save(functionBean);
                    updatePermission(oldTopCode,newTopCode,functionBean);
                }
            }
        }
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
    public boolean isUpdate(FunctionBean entity, JsonStatus status) {
        Assert.notNull(entity, "实体不能为空.");
        FunctionBean bean=(FunctionBean)entity;
        List<FunctionBean> beans = dao.find("select ob from FunctionBean ob where ob.name = ?1 and ob.applicationId=?2", bean.getName(), bean.getApplicationId());
        if(beans!=null&&beans.size()>0){
            FunctionBean functionBean=beans.get(0);
            if(functionBean.getId()!=entity.getId()) {
                status.setFailure(true);
                status.setMsg(FUNCTION_NAME + "已经存在,请检查名称！");
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean isSave(FunctionBean entity, JsonStatus status) {
        Assert.notNull(entity, "实体不能为空.");
        FunctionBean bean=(FunctionBean)entity;
        List<FunctionBean> beans = dao.find("select ob from FunctionBean ob where ob.name = ?1 and ob.applicationId=?2 and ob.parentId=?3", bean.getName(), bean.getApplicationId(), bean.getParentId());
        if(beans!=null&&beans.size()>0){
            status.setFailure(true);
            status.setMsg(FUNCTION_NAME + "已经存在,请检查名称！");
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public void deleteByApplicationId(long id) {
        dao.update("delete from FunctionBean fb where fb.applicationId=?1", id);
    }

    /**
     * 递归函数加载子节点
     *
     * @param root
     * @param elements
     */
    public void getChilden(FunctionDTO root, List<FunctionBean> elements, Mapper mapper) {
        List<FunctionDTO> children = new ArrayList<FunctionDTO>();

        for (FunctionBean functionBean : elements) {
            if (!root.getId().equals(-1L) && (root.getId().equals(functionBean.getParentId()))) {
                FunctionDTO functionDTO = mapper.map(functionBean, FunctionDTO.class);
                functionDTO.setLeaf(functionBean.getIsLeaf().equals(0L) ? false : true);
                functionDTO.setParentName(root.getName());
                functionDTO.setText(functionBean.getName());
                children.add(functionDTO);
                if (functionBean.getIsLeaf().equals(0L)) {
                    getChilden(functionDTO, elements, mapper);
                }
            }
        }
        root.setChildren(children);
    }

    /**
     * 递归函数加载子节点
     *
     * @param root
     * @param elements
     */
    public void getChilden(AuthorizationDTO root, List<FunctionBean> elements, Mapper mapper) {
        List<AuthorizationDTO> children = new ArrayList<AuthorizationDTO>();

        for (FunctionBean functionBean : elements) {
            if (root.getId() != -1 && (root.getId() == functionBean.getParentId())) {
                AuthorizationDTO functionDTO = mapper.map(functionBean, AuthorizationDTO.class);
                functionDTO.setLeaf(functionBean.getIsLeaf() == 0 ? false : true);
                functionDTO.setParentName(root.getName());
                functionDTO.setChecked(true);
                functionDTO.setExpanded(true);
                functionDTO.setText(functionBean.getName());
                children.add(functionDTO);
                if(functionBean.getIsLeaf()==0) {
                    getChilden(functionDTO, elements, mapper);
                }
            }
        }
        root.setChildren(children);
    }


    /**
     * 递归函数加载子节点,并设置选中状态
     *
     * @param root
     * @param elements
     */
    public void getChilden(AuthorizationDTO root, List<FunctionBean> elements, Mapper mapper, List<RoleFunctionBean> roleFunctionBeans, boolean defaultChecked) {
        List<AuthorizationDTO> children = new ArrayList<>();

        elements.stream().filter(func -> root.getId() != -1 && func.getParentId() == root.getId())
                .forEach(func -> {
                    AuthorizationDTO functionDTO = mapper.map(func, AuthorizationDTO.class);
                    functionDTO.setLeaf(func.getIsLeaf() != 0);
                    functionDTO.setParentName(root.getName());
                    functionDTO.setChecked(defaultChecked);
                    functionDTO.setExpanded(true);
                    functionDTO.setText(func.getName());
                    if (roleFunctionBeans != null) {
                        roleFunctionBeans.stream().filter(self -> self.getFunctionId() == func.getId())
                                .forEach(self -> functionDTO.setChecked(true));
                    }
                    if (func.getIsLeaf() == 0) {
                        getChilden(functionDTO, elements, mapper,roleFunctionBeans, defaultChecked);
                    }

                    children.add(functionDTO);
                });
        root.setChildren(children);
    }

    public Boolean getDataAuth(String appName, String funKey) {
        FunctionBean dto = null;
        List<FunctionBean> funBeans = this.dao.find("select d from FunctionBean d, ApplicationBean u " +
                "where d.applicationId = u.id and u.code = ?1 and d.dataPermissionKey = ?2", appName, funKey);
        if (funBeans != null && funBeans.size() > 0) {
            return funBeans.get(0).getDataPermission();
        }
        return false;
    }
    public FunctionDTO getAllByApplicationId(long id) {
        List<FunctionBean> beans = dao.find("select ob from FunctionBean ob where ob.applicationId = ?1", id);
        FunctionDTO root=new FunctionDTO();
        root.setId(-1L);
        if(beans!=null&&beans.size()>0){
            List<FunctionBean> rootElements = getRootElements(beans);
            if(rootElements!=null&&rootElements.size()>0) {
                for(FunctionBean rootElement:rootElements){
                    Mapper mapper = new DozerBeanMapper();
                    FunctionDTO functionDTO = mapper.map(rootElement, FunctionDTO.class);
                    functionDTO.setLeaf(rootElement.getIsLeaf().equals(0L) ? false : true);
                    functionDTO.setParentName("根功能");
                    functionDTO.setText(rootElement.getName());
                    getChilden(functionDTO, beans, mapper);
                    root.getChildren().add(functionDTO);
                }
            }
        }
        return root;
    }
    /**
     * 获得所有根节点
     * @param elements
     * @return
     */
    public List<FunctionBean> getRootElements(List<FunctionBean> elements) {
        List<FunctionBean> roots=new ArrayList<FunctionBean>();
        for (FunctionBean element : elements) {
            if (element.getParentId().equals(-1L)) {
                roots.add(element);
            }
        }
        return roots;
    }
}