package com.goinhn.portrait.model.entity.newinfo;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * 企业出资信息(股东（自然人）出资信息)
 *
 * @author goinhn
 */
@Data
@Builder
public class EntContributionNewInfo {

    /**
     * 序号
     */
    private Long eid;

    /**
     * 企业名称
     */
    private String entName;

    /**
     * 投资人类型（股东类型）（19种）
     */
    private String invType;

    /**
     * 出资方式(认缴)（货币、美元）
     */
    private String conForm;

    /**
     * 认缴出资额（认缴额万元）
     */
    private Float subconam;

    /**
     * 持股比例
     */
    private Integer conProp;

    /**
     * 出资日期(认缴)
     */
    private Date conDate;

}
