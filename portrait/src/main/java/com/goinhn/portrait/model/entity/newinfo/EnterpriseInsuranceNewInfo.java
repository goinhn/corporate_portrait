package com.goinhn.portrait.model.entity.newinfo;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * 单位参保信息查询（养老单位参保信息）
 *
 * @author goinhn
 */
@Data
@Builder
public class EnterpriseInsuranceNewInfo {

    /**
     * 序号
     */
    private Long eid;

    /**
     * 企业名称
     */
    private String entName;

    /**
     * 参保日期
     */
    private Date cbrq;

    /**
     * 险种标志
     */
    private String xzbz;

    /**
     * 社会保险经办机构
     */
    private String sbjgbh;

    /**
     * 险种标志名称
     */
    private String xzbzmc;

    /**
     * 参保状态
     */
    private Integer cbzt;

    /**
     * 参保状态名称
     */
    private String cbztmc;

    /**
     * 单位编号
     */
    private String dwbh;

}
