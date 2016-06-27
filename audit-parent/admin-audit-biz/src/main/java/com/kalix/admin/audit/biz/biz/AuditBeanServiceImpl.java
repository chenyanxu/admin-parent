package com.kalix.admin.audit.biz.biz;



import com.kalix.admin.audit.api.biz.IAuditBeanService;
import com.kalix.admin.audit.api.dao.IAuditBeanDao;
import com.kalix.admin.audit.entities.AuditBean;
import com.kalix.framework.core.api.persistence.JsonStatus;
import com.kalix.framework.core.impl.biz.GenericBizServiceImpl;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

/**
 * @类描述：公告管理
 * @创建人： zhangqingxin
 * @创建时间：2014/10/10
 * @修改人：
 * @修改时间：
 * @修改备注：
 */
public class AuditBeanServiceImpl extends GenericBizServiceImpl<IAuditBeanDao, AuditBean> implements IAuditBeanService {
    private JsonStatus jsonStatus = new JsonStatus();
    private String uuid;
    public AuditBeanServiceImpl() {
        uuid = UUID.randomUUID().toString();
        super.init(AuditBean.class.getName());
    }

    @Override
    public List<AuditBean> query(String title) {
        return dao.find("select n from auditBean n where n.title LIKE ?1 ", "%" + title + "%");
    }

    @Transactional
    public void test() {
        AuditBean auditBean = new AuditBean();
        auditBean.setAction("dfd");
        auditBean.setAppName("fdfdfdfd");
        super.saveEntity(auditBean);
        throw new RuntimeException("this is text");
    }

}
