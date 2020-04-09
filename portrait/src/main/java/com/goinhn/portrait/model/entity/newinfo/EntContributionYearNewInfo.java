package com.goinhn.portrait.model.entity.newinfo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty("entname")
    private String entName;

    /**
     * 认缴币种
     */
    @JsonProperty("subconcurrency")
    private String subconcurrency;

    /**
     * 实缴出资时间
     */
    @JsonProperty("accondate")
    @JsonFormat
    private Date acconDate;

    /**
     * 认缴出资方式
     */
    @JsonProperty("subconform")
    private String subconForm;

    /**
     * 行业分类
     */
    @JsonProperty("anchetype")
    private String ancheType;

    /**
     * 认缴出资时间
     */
    @JsonProperty("subcondate")
    @JsonFormat
    private Date subconDate;

    /**
     * 实缴币种
     */
    @JsonProperty("acconcurrency")
    private String acconcurrency;

    /**
     * 实缴出资方式
     */
    @JsonProperty("acconform")
    private String acconForm;

    /**
     * 累计实缴额
     */
    @JsonProperty("liacconam")
    private Float liacconam;

    /**
     * 累计认缴额
     */
    @JsonProperty("lisubconam")
    private Float lisubconam;

}
