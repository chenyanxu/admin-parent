package com.kalix.admin.template.biz;



import com.kalix.admin.template.api.biz.ITemplateBeanService;
import com.kalix.admin.template.api.dao.ITemplateBeanDao;
import com.kalix.admin.template.entities.TemplateBean;
import com.kalix.framework.core.api.persistence.JsonStatus;
import com.kalix.framework.core.impl.biz.GenericBizServiceImpl;

import javax.transaction.Transactional;
import java.util.Date;
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
public class TemplateBeanServiceImpl extends GenericBizServiceImpl<ITemplateBeanDao, TemplateBean> implements ITemplateBeanService {
    private JsonStatus jsonStatus = new JsonStatus();
    private String uuid;
    public TemplateBeanServiceImpl() {
        uuid = UUID.randomUUID().toString();
        super.init(TemplateBean.class.getName());
    }

    @Override
    public List<TemplateBean> query(String title) {
        return dao.find("select n from templateBean n where n.title LIKE ?1 ", "%" + title + "%");
    }

    @Transactional
    public void test() {
        TemplateBean templateBean = new TemplateBean();
        templateBean.setCreationDate(new Date());
        super.saveEntity(templateBean);
        throw new RuntimeException("this is text");
    }

    @Override
    public void beforeUpdateEntity(TemplateBean entity, JsonStatus status) {

    }

    @Override
    public void beforeSaveEntity(TemplateBean entity, JsonStatus status) {

    }

    @Override
    public void beforeDeleteEntity(Long id, JsonStatus status) {

    }

}
