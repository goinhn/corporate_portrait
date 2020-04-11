package com.goinhn.portrait.model.entity.newinfo;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

/**
 * 企业基本信息
 *
 * @author goinhn
 */
@Data
public class CompanyBaseinfoNewInfo {

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
     * 注册资本
     */
    @JsonProperty("regcap")
    private Float regcap;

    /**
     * 从业人数
     */
    @JsonProperty("empnum")
    private Integer empNum;

    /**
     * 成立日期
     */
    @JsonProperty("estdate")
    @JsonFormat
    private Date estDate;

    /**
     * 注销时间
     */
    @JsonProperty("candate")
    @JsonFormat
    private Date canDate;

    /**
     * 吊销时间
     */
    @JsonProperty("revdate")
    @JsonFormat
    private Date revDate;

    /**
     * 企业状态
     */
    @JsonProperty("entstatus")
    private String entStatus;

    /**
     * 经营(驻在)期限至
     */
    @JsonProperty("opto")
    @JsonFormat
    private Date opto;

    /**
     * 企业(机构)类型
     */
    @JsonProperty("enttype")
    private String entType;

    /**
     * 企业类别
     */
    @JsonProperty("entcat")
    private String entCat;

    /**
     * 企业门类
     */
    @JsonProperty("industryphy")
    private String industryphy;

    /**
     * 注册资本(金)币种
     */
    @JsonProperty("regcapcur")
    private String regcapcur;

    /**
     * 产业类别
     */
    @JsonProperty("industryco")
    private String industryco;

    /**
     * 经营(驻在)期限自
     */
    @JsonProperty("opfrom")
    @JsonFormat
    private Date opFrom;

}
