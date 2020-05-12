package com.goinhn.kon.model.entity.newinfo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

/**
 * 司法风险—开庭公告数据
 *
 * @author goinhn
 */
@Data
public class JusticeDeclareNewInfo {

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
     * 公告时间
     */
    @JsonProperty("declaredate")
    @JsonFormat
    private Date declareDate;

    /**
     * 上诉方(企业如果为上诉方，值为1，否则值为0）
     */
    @JsonProperty("appellant")
    private Integer appellant;

    /**
     * 被诉方（企业如果为被诉方，值为1，否则值为0）
     */
    @JsonProperty("defendant")
    private Integer defendant;

    /**
     * 公告类型
     */
    @JsonProperty("declarestyle")
    private String declareStyle;

}
