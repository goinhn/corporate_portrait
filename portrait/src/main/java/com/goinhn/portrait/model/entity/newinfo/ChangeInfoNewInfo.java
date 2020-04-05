package com.goinhn.portrait.model.entity.newinfo;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * 企业变更信息输入信息
 *
 * @author goinhn
 */
@Data
@Builder
public class ChangeInfoNewInfo {

    /**
     * 序号
     */
    private Long eid;

    /**
     * 企业名称
     */
    private String entName;

    /**
     * 备注
     */
    private Integer remark;

    /**
     * 数据来源标志：1核准通过2删除或者驳回或者不予受理
     */
    private Integer dataFlag;

    /**
     * 变更次数
     */
    private Integer altTime;

    /**
     * 变更事项
     */
    private String altItem;

    /**
     * 撤销状态：1：变更2：撤销变更3：已撤销变更
     */
    private Integer cxStatus;

    /**
     * 变更日期
     */
    private Date altDate;

    /**
     * 业务编号
     */
    private String openo;

}
