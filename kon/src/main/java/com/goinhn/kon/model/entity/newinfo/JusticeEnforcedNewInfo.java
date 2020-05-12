package com.goinhn.kon.model.entity.newinfo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty("entname")
    private String entName;

    /**
     * 立案日期
     */
    @JsonProperty("record_date")
    @JsonFormat
    private Date recordDate;

    /**
     * 执行标的
     */
    @JsonProperty("enforce_amount")
    private Float enforceAmount;

}
