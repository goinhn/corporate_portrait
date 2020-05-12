package com.goinhn.kon.model.entity.newinfo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

/**
 * 单位参保信息查询（养老单位参保信息）
 *
 * @author goinhn
 */
@Data
public class EnterpriseInsuranceNewInfo {

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
     * 参保日期
     */
    @JsonProperty("cbrq")
    @JsonFormat
    private Date cbrq;

    /**
     * 险种标志
     */
    @JsonProperty("xzbz")
    private String xzbz;

    /**
     * 社会保险经办机构
     */
    @JsonProperty("sbjgbh")
    private String sbjgbh;

    /**
     * 险种标志名称
     */
    @JsonProperty("xzbzmc")
    private String xzbzmc;

    /**
     * 参保状态
     */
    @JsonProperty("cbzt")
    private Integer cbzt;

    /**
     * 参保状态名称
     */
    @JsonProperty("cbztmc")
    private String cbztmc;

    /**
     * 单位编号
     */
    @JsonProperty("dwbh")
    private String dwbh;

}
