package com.goinhn.portrait.model.entity.newinfo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

/**
 * 企业变更信息输入信息
 *
 * @author goinhn
 */
@Data
public class ChangeInfoNewInfo {

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
     * 备注
     */
    @JsonProperty("remark")
    private Integer remark;

    /**
     * 数据来源标志：1核准通过2删除或者驳回或者不予受理
     */
    @JsonProperty("dataflag")
    private Integer dataFlag;

    /**
     * 变更次数
     */
    @JsonProperty("alttime")
    private Integer altTime;

    /**
     * 变更事项
     */
    @JsonProperty("altitem")
    private String altItem;

    /**
     * 撤销状态：1：变更2：撤销变更3：已撤销变更
     */
    @JsonProperty("cxstatus")
    private Integer cxStatus;

    /**
     * 变更日期
     */
    @JsonProperty("altdate")
    @JsonFormat
    private Date altDate;

    /**
     * 业务编号
     */
    @JsonProperty("openo")
    private String openo;

}
