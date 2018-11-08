package com.kalix.uuidtest.biz;

import com.kalix.framework.core.impl.biz.ShiroGenericUUIDBizServiceImpl;
import com.kalix.uuidtest.api.biz.IColumnBeanService;
import com.kalix.uuidtest.api.dao.IColumnBeanDao;
import com.kalix.uuidtest.entities.ColumnBean;


/**
 * Created by Administrator on 2018/5/13.
 */
public class ColumnBeanServiceImpl extends ShiroGenericUUIDBizServiceImpl<IColumnBeanDao, ColumnBean> implements IColumnBeanService {
}
