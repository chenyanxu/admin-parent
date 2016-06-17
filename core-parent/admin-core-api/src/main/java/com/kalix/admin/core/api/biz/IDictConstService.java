package com.kalix.admin.core.api.biz;

/**
 * Created by Dell on 14-8-13.
 */
public interface IDictConstService {
    String CONST_TYPE = "area_relation"; //type const
    String ACCIDENT_TYPE = "accident_type"; //事故类别
    String ACCIDENT_LEVEL = "accident_level"; //事故级别
    String INDUSTRY_CATEGORY = "industry_category"; //行业类别
    String ACCIDENT_PRELIMINARY_ANALYSIS = "accident_preliminary_analysis"; //事故原因初步分析
    String EQUIPMENT_PROPERTY = "equipment_property"; //物资装备属性
    String REGISTRATION_TYPE = "registration_type"; //物资装备登记类型
    String EQUIPMENT_SOURCES = "equipment_sources"; //物资装备来源
    String EQUIPMENT_TYPE = "equipment_type"; //物资装备类型
    String AQJGSZQK = "AQJGSZQK"; //企业是否有专门安全管理部门
    String ZYBBJ = "ZYBBJ"; //企业是否有职业危害因素
    String ZDXFDW_BJ = "ZDXFDW_BJ"; //企业是否重点消防单位标记
    String YAZLQY_BJ = "YAZLQY_BJ"; //企业是否液氨制冷企业标记
    String ENTERPRISE_GMQK = "ENTERPRISE_GMQK"; //规模情况
    String ENTERPRISE_XJQYBJ = "ENTERPRISE_XJQYBJ"; //企业是否有下级单位
    String ENTERPRISE_JGFL = "ENTERPRISE_JGFL"; //监管分类
    String ORGANIZATION_JGBMBJ = "ORGANIZATION_JGBMBJ"; //机关部门标记
    String ORGANIZATION_JGLXBJ = "ORGANIZATION_JGLXBJ"; //机构类型标记
    String ORGANIZATION_ZFBJ = "ORGANIZATION_ZFBJ"; //作废标记
    String DRILL_CONFIRM = "DRILL_CONFIRM"; //应急演练确认状态
    String EXPERT_TYPE = "EXPERT_TYPE";              //专家类别
    String DIGITALPLAN_LEVEL = "DIGITALPLAN_LEVEL";    //监管机构预案级别
    String DIGITALPLAN_TYPE = "DIGITALPLAN_TYPE";              //监管机构预案类型
}
