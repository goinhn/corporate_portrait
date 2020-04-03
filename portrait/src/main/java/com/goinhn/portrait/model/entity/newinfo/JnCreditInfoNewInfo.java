package com.goinhn.portrait.model.entity.newinfo;

import lombok.Data;

/**
 * 济南市信用信息
 *
 * @author goinhn
 */
@Data
public class JnCreditInfoNewInfo {

    /**
     * 序号
     */
    private Long eid;

    /**
     * 企业名称
     */
    private String entName;

    /**
     * 信用等级 N+、B-、A、C、N、A-
     */
    private String creditGrade;

}
