package com.goinhn.portrait.model.vo;


import lombok.Data;

/**
 * 展示基本信息模型
 *
 * @author goinhn
 */
@Data
public class ShowInfo {

    /**
     * 序号
     */
    private Long eid;

    /**
     * 企业名称
     */
    private String entName;

    /**
     * 中标次数
     */
    private Integer bidNum;

    /**
     * 分支机构数
     */
    private Integer branchNum;

    /**
     * 对外投资次数
     */
    private Integer investNum;

    /**
     * 网店个数
     */
    private Integer shopNum;

    /**
     * 级别
     */
    private String levelRank;

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
