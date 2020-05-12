package com.goinhn.kon.model.entity.newinfo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

/**
 * 年报社保信息（参保状态/年报五险一金欠税额）
 *
 * @author goinhn
 */
@Data
public class EntSocialSecurityNewInfo {

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
     * 单位参加失业保险累计欠缴金额
     */
    @JsonProperty("unpaidsocialins_so110")
    private Float unpaidsocialinsSo110;

    /**
     * 单位参加职工基本医疗保险累计欠缴金额
     */
    @JsonProperty("unpaidsocialins_so210")
    private Float unpaidsocialinsSo210;

    /**
     * 单位参加工伤保险累计欠缴金额
     */
    @JsonProperty("unpaidsocialins_so310")
    private Float unpaidsocialinsSo310;

    /**
     * 单位参加城镇职工基本养老保险累计欠缴金额
     */
    @JsonProperty("unpaidsocialins_so410")
    private Float unpaidsocialinsSo410;

    /**
     * 单位参加生育保险累计欠缴金额
     */
    @JsonProperty("unpaidsocialins_so510")
    private Float unpaidsocialinsSo510;

    /**
     * 更新时间
     */
    @JsonProperty("updatetime")
    @JsonFormat
    private Date updateTime;

}
