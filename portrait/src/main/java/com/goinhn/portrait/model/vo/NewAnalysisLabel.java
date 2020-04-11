package com.goinhn.portrait.model.vo;

import com.goinhn.portrait.model.entity.analysis.*;
import com.goinhn.portrait.model.entity.label.*;
import lombok.Data;

/**
 * 前端差传回的分析和标签模型
 *
 * @author goinhn
 */
@Data
public class NewAnalysisLabel {

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
