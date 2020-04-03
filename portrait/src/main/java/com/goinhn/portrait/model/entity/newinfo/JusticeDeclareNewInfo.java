package com.goinhn.portrait.model.entity.newinfo;

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
    private String entName;

    /**
     * 公告时间
     */
    private Date declareDate;

    /**
     * 上诉方(企业如果为上诉方，值为1，否则值为0）
     */
    private Integer appellant;

    /**
     * 被诉方（企业如果为被诉方，值为1，否则值为0）
     */
    private Integer defendant;

    /**
     * 公告类型
     */
    private String declareStyle;

}
