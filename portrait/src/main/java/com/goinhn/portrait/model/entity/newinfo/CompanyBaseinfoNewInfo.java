package com.goinhn.portrait.model.entity.newinfo;


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
    private String entName;

    /**
     * 注册资本
     */
    private Float regcap;

    /**
     * 从业人数
     */
    private Integer empNum;

    /**
     * 成立日期
     */
    private Date estDate;

    /**
     * 注销时间
     */
    private Date canDate;

    /**
     * 吊销时间
     */
    private Date revDate;

    /**
     * 企业状态
     */
    private String entStatus;

    /**
     * 经营(驻在)期限至
     */
    private Date opto;

    /**
     * 企业(机构)类型
     */
    private String entType;

    /**
     * 企业类别
     */
    private String entCat;

    /**
     * 企业门类
     */
    private String industryphy;

    /**
     * 注册资本(金)币种
     */
    private String regcapcur;

    /**
     * 产业类别
     */
    private String industryco;

    /**
     * 经营(驻在)期限自
     */
    private Date opFrom;

}
