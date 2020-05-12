package com.goinhn.kon.constant.enums;

/**
 * 输入信息的分类
 *
 * @author goinhn
 */
public enum NewInfoKind {

    //企业变更信息输入信息
    CHANGE_INFO,

    //企业基本信息
    COMPANY_BASE_INFO,

    //企业出资信息(股东（自然人）出资信息)
    ENT_CONTRIBUTION,

    //企业年报出资信息
    ENT_CONTRIBUTION_YEAR,

    //单位参保信息查询（养老单位参保信息）
    ENTERPRISE_INSURANCE,

    //企业年报对外担保
    ENT_GUARANTEE,

    //年报社保信息（参保状态/年报五险一金欠税额）
    ENT_SOCIAL_SECURITY,

    //济南市信用信息
    JN_CREDIT_INFO,

    //司法风险—开庭公告数据
    JUSTICE_DECLARE,

    //司法风险—被执行人数据
    JUSTICE_ENFORCED,

    //司法风险-裁判文书数据
    JUSTICE_JUDGE,

    //单条数据
    ONE_DATA

}
