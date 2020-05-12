package com.goinhn.kon.model.entity.newinfo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty("entname")
    private String entName;

    /**
     * 时间
     */
    @JsonProperty("time")
    @JsonFormat
    private Date time;

    /**
     * 标题
     */
    @JsonProperty("title")
    private String title;

    /**
     * 案件类型
     */
    @JsonProperty("casetype")
    private String caseType;

    /**
     * 判决结果
     */
    @JsonProperty("judgeresult")
    private String judgeResult;

    /**
     * 案由
     */
    @JsonProperty("casecause")
    private String caseCause;

    /**
     * 案由编码类型
     */
    @JsonProperty("evidence")
    private String evidence;

    /**
     * 依据
     */
    @JsonProperty("courtrank")
    private String courtRank;

    /**
     * 法院等级
     */
    @JsonProperty("datatype")
    private String dataType;

    /**
     * 司法类型
     */
    @JsonProperty("latypes")
    private String laTypes;

}
