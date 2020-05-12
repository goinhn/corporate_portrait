package com.goinhn.kon.model.entity.newinfo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

/**
 * 企业出资信息(股东（自然人）出资信息)
 *
 * @author goinhn
 */
@Data
public class EntContributionNewInfo {

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
     * 投资人类型（股东类型）（19种）
     */
    @JsonProperty("invtype")
    private String invType;

    /**
     * 出资方式(认缴)（货币、美元）
     */
    @JsonProperty("conform")
    private String conForm;

    /**
     * 认缴出资额（认缴额万元）
     */
    @JsonProperty("subconam")
    private Float subconam;

    /**
     * 持股比例
     */
    @JsonProperty("conprop")
    private Integer conProp;

    /**
     * 出资日期(认缴)
     */
    @JsonProperty("condate")
    @JsonFormat
    private Date conDate;

}
