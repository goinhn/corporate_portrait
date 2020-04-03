package com.goinhn.portrait.model.entity.newinfo;

import lombok.Data;

import java.util.Date;

/**
 * 司法风险—被执行人数据
 *
 * @author goinhn
 */
@Data
public class JusticeEnforcedNewInfo {

    /**
     * 序号
     */
    private Long eid;

    /**
     * 企业名称
     */
    private String entName;

    /**
     * 立案日期
     */
    private Date recordDate;

    /**
     * 执行标的
     */
    private Float enforceAmount;

}
