package com.goinhn.portrait.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author goinhn
 */
@Data
public class BusinessManagementAbility implements Serializable {

    /**
     * eid：序号
     */
    private Long eid;

    /**
     * entName：公司名
     */
    private String entName;

    /**
     * evaluation: 实缴-认缴
     */
    private Double evaluation;

    /**
     * investNum: 对外投资数
     */
    private Double investNum;

    /**
     * bidNum: 中标次数
     */
    private Double bidNum;

    /**
     * cbzt: 险种标志
     */
    private Double cbzt;

    /**
     * iBrandNum: 知识产权申请
     */
    private Double iBrandNum;

    /**
     * iCopyNum: 软著登记
     */
    private Double iCopyNum;

    /**
     * iPatNum: 专利申请
     */
    private Double iPatNum;

    /**
     * iDomNum: 域名的知识产权
     */
    private Double iDomNum;

}
