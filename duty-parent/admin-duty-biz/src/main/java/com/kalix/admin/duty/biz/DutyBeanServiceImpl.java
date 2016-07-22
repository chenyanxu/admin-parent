package com.kalix.admin.duty.biz;

import com.kalix.admin.duty.api.biz.IDutyBeanService;
import com.kalix.admin.duty.api.dao.IDutyBeanDao;
import com.kalix.admin.duty.api.dao.IDutyUserBeanDao;
import com.kalix.admin.duty.entities.DutyBean;
import com.kalix.admin.duty.entities.DutyUserBean;
import com.kalix.framework.core.api.persistence.JsonData;
import com.kalix.framework.core.api.persistence.JsonStatus;
import com.kalix.framework.core.impl.biz.ShiroGenericBizServiceImpl;
import com.kalix.framework.core.util.StringUtils;

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

    public void setDutyUserBeanDao(IDutyUserBeanDao dutyUserBeanDao) {
        this.dutyUserBeanDao = dutyUserBeanDao;
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
                String userName = getShiroService().getCurrentUserName();
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

    //    @Override
//    public JsonData getUserAll(long orgId) {
//        JsonData jsonData = new JsonData();
//        List<PersistentEntity> list = userDao.findByUserId(organizationUserDao.findByOrgId(orgId).stream()
//                .map(OrganizationUserBean::getUserId)
//                .collect(Collectors.toList()), true).stream()
//                .map(n -> (PersistentEntity) n).collect(Collectors.toList());
//
//        jsonData.setData(list);
//        jsonData.setTotalCount((long) list.size());
//        return jsonData;
//    }

//    @Override
//    public JsonData getUserAllAndDutyUsers(long dutyId) {
//        JsonData jsonData = new JsonData();
//        DutyBean bean = dao.get(dutyId);
//        if (bean != null) {
//            List<PersistentEntity> list = userDao.findByUserId(organizationUserDao.findByOrgId(bean.getOrgid()).stream()
//                    .map(OrganizationUserBean::getUserId)
//                    .collect(Collectors.toList()), true).stream()
//                    .map(n -> (PersistentEntity) n).collect(Collectors.toList());
//
//            list.addAll(userDao.findByUserId(dutyUserBeanDao.findByDutyId(dutyId).stream()
//                    .map(n -> n.getUserId())
//                    .collect(Collectors.toList()), true).stream()
//                    .map(n -> (PersistentEntity) n).collect(Collectors.toList()));
//            jsonData.setData(list);
//            jsonData.setTotalCount((long) list.size());
//        }
//        return jsonData;
//    }
}
