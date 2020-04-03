package com.goinhn.portrait.model.entity.newinfo;

import lombok.Data;

import java.util.Date;

/**
 * 企业年报出资信息
 *
 * @author goinhn
 */
@Data
public class EntContributionYearNewInfo {

    /**
     * 序号
     */
    private Long eid;

    /**
     * 企业名称
     */
    private String entName;

    /**
     * 认缴币种
     */
    private String subconcurrency;

    /**
     * 实缴出资时间
     */
    private Date acconDate;

    /**
     * 认缴出资方式
     */
    private String subconForm;

    /**
     * 行业分类
     */
    private String ancheType;

    /**
     * 认缴出资时间
     */
    private Date subconDate;

    /**
     * 实缴币种
     */
    private String acconcurrency;

    /**
     * 实缴出资方式
     */
    private String acconForm;

    /**
     * 累计实缴额
     */
    private Float liacconam;

    /**
     * 累计认缴额
     */
    private Float lisubconam;

}
