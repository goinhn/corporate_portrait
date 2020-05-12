package com.goinhn.kon.model.entity.analysis;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * 企业信用风险分析
 *
 * @author goinhn
 */
@Data
public class CreditRiskAnalysis {

    /**
     * eid：序号
     */
    private Long eid;

    /**
     * entName：公司名
     */
    private String entName;

    /**
     * isPunish: 公司是否有过行政处罚
     */
    private Double isPunish;

    /**
     * isKcont: 是否列为守合同重信用企业
     */
    private Double isKcont;

    /**
     * creditGrade: 信用等级 N+、B-、A、C、N、A-
     */
    private Double creditGrade;

    /**
     * isJusticeCreditaic: 是否列入失信企业（工商部）
     */
    private Double isJusticeCreditaic;

}
