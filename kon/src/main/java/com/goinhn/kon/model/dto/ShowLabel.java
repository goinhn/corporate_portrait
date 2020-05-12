package com.goinhn.kon.model.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * 展示数据标签模型
 *
 * @author goinhn
 */
@Data
@Builder
public class ShowLabel implements Serializable {

    /**
     * 企业名称
     */
    private String entName;

    /**
     *企业背景标签
     */
    private String businessBackgroundLabel;

    /**
     *企业经营能力标签
     */
    private String businessManagementAbilityLabel;

    /**
     *企业经营风险标签
     */
    private String businessManagementRiskLabel;

    /**
     *企业稳定性标签
     */
    private String businessStabilityLabel;

    /**
     *信用风险标签
     */
    private String creditRiskLabel;

    /**
     *司法风险标签
     */
    private String judicialRiskLabel;

}
