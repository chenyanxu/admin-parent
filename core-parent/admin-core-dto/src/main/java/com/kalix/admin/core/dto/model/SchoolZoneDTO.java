package com.kalix.admin.core.dto.model;

import com.kalix.framework.core.api.web.model.BaseDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * 区域模型类
 * @author hqj <br/>
 *         date:2018-9-13
 * @version 1.0.0
 */
public class SchoolZoneDTO extends BaseDTO {
    private String xqdm;   // 校区代码
    private String zwmc;   // 中文名称
    private String zwjc;   // 中文简称
    private String dz;     // 地址
    private String yb;     // 邮编
    private String hcz;    // 火车站

    public String getXqdm() {
        return xqdm;
    }

    public void setXqdm(String xqdm) {
        this.xqdm = xqdm;
    }

    public String getZwmc() {
        return zwmc;
    }

    public void setZwmc(String zwmc) {
        this.zwmc = zwmc;
    }

    public String getZwjc() {
        return zwjc;
    }

    public void setZwjc(String zwjc) {
        this.zwjc = zwjc;
    }

    public String getDz() {
        return dz;
    }

    public void setDz(String dz) {
        this.dz = dz;
    }

    public String getYb() {
        return yb;
    }

    public void setYb(String yb) {
        this.yb = yb;
    }

    public String getHcz() {
        return hcz;
    }

    public void setHcz(String hcz) {
        this.hcz = hcz;
    }
}
