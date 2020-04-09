package com.goinhn.portrait.model.entity.newinfo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 单条数据
 *
 * @author goinhn
 */
@Data
public class OneDataNewInfo {

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
     * 公司是否有过行政处罚（有（数值为次数，一次为1），无（0）
     */
    @JsonProperty("is_punish")
    private Integer isPunish;

    /**
     * 是否列入经营异常
     */
    @JsonProperty("is_bra")
    private Integer isBra;

    /**
     * 企业是否行政处罚记录（有：处罚次数，无：值为0）
     */
    @JsonProperty("is_brap")
    private Integer isBrap;

    /**
     * 出质股权次数
     */
    @JsonProperty("pledgenum")
    private Integer pledgeNum;

    /**
     * 企业累计欠税额
     */
    @JsonProperty("taxunpaidnum")
    private Float taxunpaidNum;

    /**
     * 中标次数
     */
    @JsonProperty("bidnum")
    private Integer bidNum;

    /**
     * 分支机构数
     */
    @JsonProperty("branchnum")
    private Integer branchNum;

    /**
     * 投资数
     */
    @JsonProperty("investnum")
    private Integer investNum;

    /**
     * 网店个数
     */
    @JsonProperty("shopnum")
    private Integer shopNum;

    /**
     * 是否列为守合同重信用企业
     */
    @JsonProperty("is_kcont")
    private Integer isKcont;

    /**
     * 是否列为异常（有（次数），无（0））
     */
    @JsonProperty("is_except")
    private Integer isExcept;

    /**
     * 知识产权--商标申请次数
     */
    @JsonProperty("ibrand_num")
    private Integer ibrandNum;

    /**
     * 企业软件著作权登记次数
     */
    @JsonProperty("icopy_num")
    private Integer icopyNum;

    /**
     * 企业专利申请次数
     */
    @JsonProperty("ipat_num")
    private Integer ipatNum;

    /**
     * 是否是济南市专精特新中小企业（缺失值99.99%）
     */
    @JsonProperty("is_jnsn")
    private Integer isJnsn;

    /**
     * 级别(省级2、市级1、企业名称不出现在该表则值为0)
     */
    @JsonProperty("level_rank")
    private Integer levelRank;

    /**
     * 是否列入失信黑名单
     */
    @JsonProperty("is_justice_credit")
    private Integer isJusticeCredit;

    /**
     * 是否工商部失信企业
     */
    @JsonProperty("is_justice_creditaic")
    private Integer isJusticeCreditaic;

    /**
     * 企业产品被抽查的合格率（未被抽查值为0，被抽查则值为0-1之间小数值）
     */
    @JsonProperty("passpercent")
    private Float passpercent;

    /**
     * 招聘记录条数
     */
    @JsonProperty("qcwynum")
    private Integer qcwyNum;

    /**
     * 中华英才网招聘记录条数
     */
    @JsonProperty("zhycnum")
    private Integer zhycNum;

    /**
     * 智联招聘招聘记录条数
     */
    @JsonProperty("zlzpnum")
    private Integer zlzpNum;

    /**
     * 是否列为驰名商标
     */
    @JsonProperty("is_infoa")
    private Integer isInfoa;

    /**
     * 是否列为著名商标
     */
    @JsonProperty("is_infob")
    private Integer isInfob;

    /**
     * 企业是否拥有域名的知识产权
     */
    @JsonProperty("idom_num")
    private Integer idomNum;

}
