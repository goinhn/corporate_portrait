package com.goinhn.portrait.model.entity.label;

import lombok.Data;

/**
 * 信用风险标签
 *
 * @author goinhn
 */
@Data
public class CreditRiskLabel {

    /**
     * 序号
     */
    private Long eid;

    /**
     * 企业名称
     */
    private String entName;

    /**
     * 标签序号
     */
    private Integer label;
}
