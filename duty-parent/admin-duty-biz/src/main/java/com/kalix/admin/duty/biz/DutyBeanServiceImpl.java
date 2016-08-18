package com.kalix.admin.duty.biz;

import com.kalix.admin.core.api.dao.IUserBeanDao;
import com.kalix.admin.core.entities.UserBean;
import com.kalix.admin.duty.api.biz.IDutyBeanService;
import com.kalix.admin.duty.api.dao.IDutyBeanDao;
import com.kalix.admin.duty.api.dao.IDutyUserBeanDao;
import com.kalix.admin.duty.entities.DutyBean;
import com.kalix.admin.duty.entities.DutyUserBean;
import com.kalix.framework.core.api.persistence.JsonData;
import com.kalix.framework.core.api.persistence.JsonStatus;
import com.kalix.framework.core.impl.biz.ShiroGenericBizServiceImpl;
import com.kalix.framework.core.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @类描述： 
 * @创建人：  
 * @创建时间： 
 * @修改人：
 * @修改时间：
 * @修改备注：
 */
public class DutyBeanServiceImpl extends ShiroGenericBizServiceImpl<IDutyBeanDao, DutyBean> implements IDutyBeanService {
    private JsonStatus jsonStatus = new JsonStatus();
    private IDutyUserBeanDao dutyUserBeanDao;
    private IDutyBeanDao dutyBeanDao;
    private IUserBeanDao userBeanDao;

    public void setDutyUserBeanDao(IDutyUserBeanDao dutyUserBeanDao) {
        this.dutyUserBeanDao = dutyUserBeanDao;
    }

    public void setUserBeanDao(IUserBeanDao userBeanDao) {
        this.userBeanDao = userBeanDao;
    }

    public void setDutyBeanDao(IDutyBeanDao dutyBeanDao) {
        this.dutyBeanDao = dutyBeanDao;
    }

    public DutyBeanServiceImpl() {
        super.init(DutyBean.class.getName());
    }

    @Override
    public JsonData getDutiesByOrgId(long orgId) {
        JsonData jsonData = new JsonData();
        List list=this.dao.findByOrgId(orgId);

        jsonData.setData(list);
        jsonData.setTotalCount((long) list.size());

        return jsonData;
    }

    @Override
    public List getUserIdsByDutyId(long dutyId) {
        return dutyUserBeanDao.findByDutyId(dutyId).stream()
                .filter(n -> n.getUserId() != 0)
                .map(n -> n.getUserId())
                .collect(Collectors.toList());
    }

    @Override
    public JsonStatus saveDutyUsers(List ids) {
        if(ids==null || ids.size()!=2){
            return JsonStatus.failureResult("保存失败!");
        }
        else {
            try {
                long dutyId=Long.valueOf(ids.get(0).toString());
                String userId=ids.get(1).toString();

                dutyUserBeanDao.deleteByDutyId(dutyId);
                DutyBean bean = dao.get(dutyId);
                String userName = getShiroService().getCurrentUserLoginName();
                if (StringUtils.isNotEmpty(userId)) {
                    String[] userIds = userId.split(",");
                    for (String _userId : userIds) {
                        if (StringUtils.isNotEmpty(_userId.trim())) {
                            DutyUserBean dutyUserBean = new DutyUserBean();
                            dutyUserBean.setCreateBy(userName);
                            dutyUserBean.setUpdateBy(userName);
                            dutyUserBean.setOrgId(bean.getOrgid());
                            dutyUserBean.setDutyId(dutyId);
                            dutyUserBean.setUserId(Long.parseLong(_userId));
                            dutyUserBeanDao.save(dutyUserBean);
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                return JsonStatus.failureResult("保存失败!");
            }
        }
        return JsonStatus.successResult("保存成功!");
    }

    @Override
    public JsonStatus deleteDuty(long dutyId) {
        try {
            dutyUserBeanDao.deleteByDutyId(dutyId);
            deleteEntity(dutyId);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonStatus.failureResult("删除失败");
        }
        return JsonStatus.successResult("删除成功!");
    }

    @Override
    public List<String> getUserDutyNameList(){
        Long userId=this.getShiroService().getCurrentUserId();
        List<String> dutyNameList=new ArrayList<>();
        List<DutyUserBean> dutyUserBeenList=dutyUserBeanDao.find("select rub from DutyUserBean rub where rub.userId=?1", userId);

        if(dutyUserBeenList!=null && dutyUserBeenList.size()>0){
            for(DutyUserBean dutyUserBean :dutyUserBeenList){
                DutyBean dutyBean=dao.get(dutyUserBean.getDutyId());

                dutyNameList.add(dutyBean.getName());
            }
        }

        return dutyNameList;
    }

    /**
     * 根据组织id，获得名称为“上级领导”的职务下全部的用户名字
     * 用于工作流
     * @return
     */
    @Override
    public List<String> getUserListByOrg(long orgId, String dutyName){

        List<String> userNameList=new ArrayList<>();
        //获得dutybean列表
        List<DutyBean> dutyBeanList=dutyBeanDao.find("select rub from DutyBean rub where rub.orgid=?1 and rub.name=?2", orgId,dutyName);

        if(dutyBeanList!=null && dutyBeanList.size()>0){
            for(DutyBean dutyBean :dutyBeanList){
                List<DutyUserBean> dutyUserBeanList=dutyUserBeanDao.findByDutyId(dutyBean.getId());
                if(dutyUserBeanList!=null && dutyUserBeanList.size()>0){
                    for(DutyUserBean dutyUserBean:dutyUserBeanList){
                        String userLoginName=userBeanDao.getUser(dutyUserBean.getUserId()).getLoginName();
                        userNameList.add(userLoginName);
                    }
                }
            }
        }

        return userNameList;
    }

    /**
     * 通过用户id获得职位
     * @param userId
     * @return
     */
    @Override
    public List<String>  getDutyByUserId(Long userId){
        List<String> dutyList=new ArrayList<>();
        List<DutyUserBean> dutyUserBeanList = dutyUserBeanDao.findByNativeSql("select * from sys_duty_user where userId=" + userId, DutyUserBean.class);
        if(dutyUserBeanList!=null && dutyUserBeanList.size()>0){
            for (DutyUserBean duty:dutyUserBeanList){
                DutyBean dutyBean=dutyBeanDao.get(duty.getDutyId());
                String dutyName=dutyBean.getName();
                dutyList.add(dutyName);
            }
        }
        return dutyList;
    }


}
