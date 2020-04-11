package com.goinhn.portrait.model.entity.label;

import lombok.Builder;
import lombok.Data;

/**
 * 司法风险标签
 *
 * @author goinhn
 */
@Data
public class JudicialRiskLabel {

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
