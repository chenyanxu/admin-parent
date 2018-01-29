package com.kalix.admin.core.api.biz;

import com.kalix.framework.core.api.IService;
import com.kalix.framework.core.api.persistence.JsonData;
import com.kalix.framework.core.api.persistence.JsonStatus;

/**
 * Created by Administrator_ on 2018/1/26.
 */
public interface IConfigDbService extends IService {

    JsonData getDBInfo();
    JsonStatus configureDB(String content);
    JsonData configureDbinfo(String id);
}
