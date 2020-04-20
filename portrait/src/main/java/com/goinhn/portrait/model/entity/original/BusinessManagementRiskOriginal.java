package com.goinhn.portrait.model.entity.original;

import lombok.Data;

/**
 * 企业经营风险原始分析数据
 *
 * @author goinhn
 */
@Data
public class BusinessManagementRiskOriginal {

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
     * isRange: 保证担保范围
     */
    private Double isRage;

    /**
     * subPefperfromto: 履行债务期间
     */
    private Double subPefperfromto;

    /**
     * unpaidsocialines: 单位参加的保险累计欠缴额
     */
    private Double unpaidsocialins;

    /**
     * isBra: 是否列入经营异常
     */
    private Double isBra;

    /**
     * isBrap: 企业行政处罚记录
     */
    private Double isBrap;

    /**
     * pledgeNum: 出质股权次数
     */
    private Double pledgeNum;

    /**
     * taxunpaidNum: 企业累计欠税额
     */
    private Double taxunpaidNum;

    /**
     * isExcept: 是否被列为异常
     */
    private Double isExcept;

}
