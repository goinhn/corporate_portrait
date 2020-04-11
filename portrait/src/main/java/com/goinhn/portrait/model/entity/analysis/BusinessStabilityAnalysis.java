package com.goinhn.portrait.model.entity.analysis;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * 企业经营稳定心分析
 *
 * @author goinhn
 */
@Data
public class BusinessStabilityAnalysis {

    /**
     * eid：序号
     */
    private Long eid;

    /**
     * entName：公司名
     */
    private String entName;

    /**
     * altTime: 变更次数
     */
    private Double altTime;

}
