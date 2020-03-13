package com.goinhn.portrait.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author goinhn
 */
@Data
public class CreditRisk implements Serializable {

    /**
     * eid：序号
     */
    private Long eid;

    /**
     * entName：公司名
     */
    private String entName;

    /**
     * isPunish: 行政处罚
     */
    private Double isPunish;

    /**
     * isKcont: 守合同重信用企业
     */
    private Double isKcont;

    /**
     * creditGrade: 信用等级
     */
    private Double creditGrade;

    /**
     * isJusticeCreditaic: 失信企业
     */
    private Double isJusticeCreditaic;

}
