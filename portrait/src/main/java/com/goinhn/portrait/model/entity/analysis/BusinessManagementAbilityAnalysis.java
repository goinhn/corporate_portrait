package com.goinhn.portrait.model.entity.analysis;

import lombok.Data;

import java.io.Serializable;

/**
 * 企业经营能力分析
 *
 * @author goinhn
 */
@Data
public class BusinessManagementAbilityAnalysis implements Serializable {

    /**
     * eid：序号
     */
    private Long eid;

    /**
     * entName：公司名
     */
    private String entName;

    /**
     * evaluation: 是否按时足额缴纳股本
     */
    private Double evaluation;

    /**
     * investNum: 对外投资次数
     */
    private Double investNum;

    /**
     * bidNum: 中标次数
     */
    private Double bidNum;

    /**
     * cbzt: 参保状态
     */
    private Double cbzt;

    /**
     * iBrandNum: 商标数据
     */
    private Double iBrandNum;

    /**
     * iCopyNum: 软著著作权
     */
    private Double iCopyNum;

    /**
     * iPatNum: 专利数据
     */
    private Double iPatNum;

    /**
     * iDomNum: 域名数据
     */
    private Double iDomNum;

    /**
     * passPercent: 产品被抽查合格率
     */
    private Double passPercent;

}
