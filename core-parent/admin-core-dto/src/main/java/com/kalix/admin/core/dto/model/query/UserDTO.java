package com.kalix.admin.core.dto.model.query;

import com.kalix.framework.core.api.web.model.QueryDTO;

/**
 * 用户查询
 *
 * @author majian <br/>
 *         date:2015-6-18
 * @version 1.0.0
 */
public class UserDTO extends QueryDTO {
    private String loginName; //登录名
    private String name; //姓名
    private int available=-1; //状态

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }
}
