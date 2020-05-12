package com.goinhn.kon.model.dto;

import lombok.Data;

/**
 * @author goinhn
 */
@Data
public class BusinessDTO {

    /**
     * eid 序号
     */
    private Long eid;

    /**
     * 企业名称
     */
    private String entName;

    /**
     * 企业状态
     */
    private String entStatus;

    /**
     * 企业类型
     */
    private String entType;

    /**
     * 企业类别
     */
    private String entCat;

    /**
     * 行业门类
     */
    private String industryPhy;

    /**
     * 信用等级
     */
    private String creditGrade;

}
