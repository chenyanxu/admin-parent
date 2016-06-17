package com.kalix.admin.duty.api.query;

import com.kalix.framework.core.api.web.model.QueryDTO;

/**
 * Created by sunlf on 2015/11/5.
 */
public class DutyDTO extends QueryDTO {
    private String name; //项目名称

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}