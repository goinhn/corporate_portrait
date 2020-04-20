package com.goinhn.portrait.model.entity.original;

import lombok.Data;

/**
 * 企业经营稳定心原始分析数据
 *
 * @author goinhn
 */
@Data
public class BusinessStabilityOriginal {

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
