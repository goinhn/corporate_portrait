package com.goinhn.portrait.model.entity.newinfo;

import lombok.Data;

import java.util.Date;

/**
 * 司法风险-裁判文书数据
 *
 * @author goinhn
 */
@Data
public class JusticeJudgeNewNewInfo {

    /**
     * 序号
     */
    private Long eid;

    /**
     * 企业名称
     */
    private String entName;

    /**
     * 时间
     */
    private Date time;

    /**
     * 标题
     */
    private String title;

    /**
     * 案件类型
     */
    private String caseType;

    /**
     * 判决结果
     */
    private String judgeResult;

    /**
     * 案由
     */
    private String caseCause;

    /**
     * 案由编码类型
     */
    private String evidence;

    /**
     * 依据
     */
    private String courtRank;

    /**
     * 法院等级
     */
    private String dataType;

    /**
     * 司法类型
     */
    private String laTypes;

}
