package com.goinhn.portrait.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author goinhn
 */
@Data
public class BusinessManagementRisk implements Serializable {

    /**
     * eid：序号
     */
    private Long eid;

    /**
     * entName：公司名
     */
    private String entName;

    /**
     * priclasecam: 主债权数额
     */
    private Double priclasecam;

    /**
     * encodeGuaranperiod: 保证期限
     */
    private Double encodeGuaranperiod;

    /**
     * encodeGatype: 保证方式
     */
    private Double encodeGatype;

    /**
     * isRange: 保证范围
     */
    private Double isRage;

    /**
     * subPefperfromto: 履行债务时间
     */
    private Double subPefperfromto;

    /**
     * unpaidsocialines: 失业保险欠缴额
     */
    private Double unpaidsocialins;

    /**
     * isBra: 经营异常
     */
    private Double isBra;

    /**
     * isBrap: 行政处罚记录
     */
    private Double isBrap;

    /**
     * pledgeNum: 出质股权次数
     */
    private Double pledgeNum;

    /**
     * taxunpaidNum: 累计欠税额
     */
    private Double taxunpaidNum;

    /**
     * isExcept: 是否被列为异常
     */
    private Double isExcept;

}
