package com.goinhn.portrait.model.entity.newinfo;

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
    private String entName;

    /**
     * 单位参加失业保险累计欠缴金额
     */
    private Float unpaidsocialinsSo110;

    /**
     * 单位参加职工基本医疗保险累计欠缴金额
     */
    private Float unpaidsocialinsSo210;

    /**
     * 单位参加工伤保险累计欠缴金额
     */
    private Float unpaidsocialinsSo310;

    /**
     * 单位参加城镇职工基本养老保险累计欠缴金额
     */
    private Float unpaidsocialinsSo410;

    /**
     * 单位参加生育保险累计欠缴金额
     */
    private Float unpaidsocialinsSo510;

    /**
     * 更新时间
     */
    private Date updateTime;

}
