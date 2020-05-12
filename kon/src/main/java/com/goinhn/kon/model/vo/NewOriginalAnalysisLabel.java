package com.goinhn.kon.model.vo;

import com.goinhn.kon.model.entity.analysis.*;
import com.goinhn.kon.model.entity.label.*;
import com.goinhn.kon.model.entity.original.*;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * 前端传回的分析和标签模型
 *
 * @author goinhn
 */

@ApiModel("原始数据、分析数据和标签数据模型")
@Data
public class NewOriginalAnalysisLabel {

    /**
     * 企业背景分析
     */
    private BusinessBackgroundOriginal businessBackgroundOriginal;

    /**
     * 企业经营能力分析
     */
    private BusinessManagementAbilityOriginal businessManagementAbilityOriginal;

    /**
     * 企业经营风险分析
     */
    private BusinessManagementRiskOriginal businessManagementRiskOriginal;

    /**
     * 企业稳定性分析
     */
    private BusinessStabilityOriginal businessStabilityOriginal;

    /**
     * 信用风险分析
     */
    private CreditRiskOriginal creditRiskOriginal;

    /**
     * 司法风险分析
     */
    private JudicialRiskOriginal judicialRiskOriginal;


    /**
     * 企业背景分析
     */
    private BusinessBackgroundAnalysis businessBackgroundAnalysis;

    /**
     * 企业经营能力分析
     */
    private BusinessManagementAbilityAnalysis businessManagementAbilityAnalysis;

    /**
     * 企业经营风险分析
     */
    private BusinessManagementRiskAnalysis businessManagementRiskAnalysis;

    /**
     * 企业稳定性分析
     */
    private BusinessStabilityAnalysis businessStabilityAnalysis;

    /**
     * 信用风险分析
     */
    private CreditRiskAnalysis creditRiskAnalysis;

    /**
     * 司法风险分析
     */
    private JudicialRiskAnalysis judicialRiskAnalysis;


    /**
     * 企业背景标签
     */
    private BusinessBackgroundLabel businessBackgroundLabel;

    /**
     * 企业经营能力标签
     */
    private BusinessManagementAbilityLabel businessManagementAbilityLabel;

    /**
     * 企业经营风险标签
     */
    private BusinessManagementRiskLabel businessManagementRiskLabel;

    /**
     * 企业稳定性标签
     */
    private BusinessStabilityLabel businessStabilityLabel;

    /**
     * 企业信用风险标签
     */
    private CreditRiskLabel creditRiskLabel;

    /**
     * 企业司法风险标签
     */
    private JudicialRiskLabel judicialRiskLabel;

}
