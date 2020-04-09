package com.goinhn.portrait.model.entity.newinfo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

/**
 * 企业年报对外担保
 *
 * @author goinhn
 */
@Data
public class EntGuaranteeNewInfo {

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
     * 主债权种类
     */
    @JsonProperty("priclaseckind")
    private String priclaseckind;

    /**
     * 履行债务的期限自
     */
    @JsonProperty("pefperfrom")
    @JsonFormat
    private Date pefperFrom;

    /**
     * 是否公示此担保信息1是2否
     */
    @JsonProperty("iftopub")
    private Integer iftopub;

    /**
     * 主债权数额
     */
    @JsonProperty("priclasecam")
    private Float priclasecam;

    /**
     * 履行债务的期限至
     */
    @JsonProperty("pefperto")
    @JsonFormat
    private Date pefperto;

    /**
     * 保证的期间1期限2未约定
     */
    @JsonProperty("guaranperiod")
    private Integer guaranperiod;

    /**
     * 保证的方式1一般保证2连带保证3未约定
     */
    @JsonProperty("gatype")
    private Integer gaType;

    /**
     * 保证担保的范围
     */
    @JsonProperty("rage")
    private String rage;

}
