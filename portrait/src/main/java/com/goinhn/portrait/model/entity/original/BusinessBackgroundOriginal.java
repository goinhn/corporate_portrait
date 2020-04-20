package com.goinhn.portrait.model.entity.original;

import lombok.Data;

/**
 * 企业背景原始分析数据
 *
 * @author goinhn
 */
@Data
public class BusinessBackgroundOriginal {

    /**
     * eid：序号
     */
    private Long eid;

    /**
     * entName：企业（机构）名称
     */
    private String entName;

    /**
     * empNum: 从业人数
     */
    private Double empNum;


    /**
     * encodeEntStatus: 企业状态
     */
    private Double encodeEntStatus;

    /**
     * shopNum: 网店个数
     */
    private Double shopNum;

    /**
     * branchNum: 分支机构数
     */
    private Double branchNum;

    /**
     * isInfoA: 是否列入驰名商标
     */
    private Double isInfoA;

    /**
     * isInfoB: 是否列入著名商标
     */
    private Double isInfoB;

    /**
     * levelRank: 级别
     */
    private Double levelRank;
}
