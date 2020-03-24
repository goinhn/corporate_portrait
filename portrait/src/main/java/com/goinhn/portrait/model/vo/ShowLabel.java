package com.goinhn.portrait.model.vo;

import lombok.Data;

/**
 * 展示数据标签模型
 *
 * @author goinhn
 */
@Data
public class ShowLabel {

    /**
     * 企业名称
     */
    private String entName;

    /**
     *企业背景标签
     */
    private Integer businessBackgroundLabel;

    /**
     *企业经营能力标签
     */
    private Integer businessManagementAbilityLabel;

    /**
     *企业经营风险标签
     */
    private Integer businessManagementRiskLabel;

    /**
     *企业稳定性标签
     */
    private Integer businessStabilityLabel;

    /**
     *信用风险标签
     */
    private Integer creditRiskLabel;

    /**
     *司法风险标签
     */
    private Integer judicialRiskLabel;
}
