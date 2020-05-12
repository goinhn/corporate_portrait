package com.goinhn.kon.model.entity.original;

import lombok.Data;

import javax.validation.constraints.NotNull;


/**
 * 企业经营能力原始分析数据
 *
 * @author goinhn
 */
@Data
public class BusinessManagementAbilityOriginal {

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
    @NotNull
    private Double ibrandNum;

    /**
     * iCopyNum: 软著著作权
     */
    @NotNull
    private Double icopyNum;

    /**
     * iPatNum: 专利数据
     */
    @NotNull
    private Double ipatNum;

    /**
     * iDomNum: 域名数据
     */
    @NotNull
    private Double idomNum;

    /**
     * passPercent: 产品被抽查合格率
     */
    private Double passPercent;

}
