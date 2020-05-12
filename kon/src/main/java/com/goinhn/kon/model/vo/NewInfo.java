package com.goinhn.kon.model.vo;

import com.goinhn.kon.model.entity.newinfo.*;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 接收新的输入信息模型
 *
 * @author goinhn
 */
@ApiModel("新的输入信息模型")
@Data
public class NewInfo {

    /**
     *
     */
    @NotNull(message = "企业基本信息不能为null")
    private CompanyBaseinfoNewInfo companyBaseinfoNewInfo;

    /**
     * 企业变更信息
     */
    @NotNull(message = "企业变更信息不能为null")
    private List<ChangeInfoNewInfo> changeInfoNewInfos;

    /**
     * 企业出资信息(股东（自然人）出资信息)
     */
    @NotNull(message = "企业出资信息(股东（自然人）出资信息)不能为null")
    private List<EntContributionNewInfo> entContributionNewInfos;

    /**
     * 企业年报出资信息
     */
    @NotNull(message = "企业年报出资信息不能为null")
    private List<EntContributionYearNewInfo> entContributionYearNewInfos;

    /**
     * 单位参保信息查询（养老单位参保信息）
     */
    @NotNull(message = "单位参保信息查询（养老单位参保信息）不能为null")
    private List<EnterpriseInsuranceNewInfo> enterpriseInsuranceNewInfos;

    /**
     * 企业年报对外担保
     */
    @NotNull(message = "企业年报对外担保不能为null")
    private List<EntGuaranteeNewInfo> entGuaranteeNewInfos;

    /**
     * 年报社保信息（参保状态/年报五险一金欠税额）
     */
    @NotNull(message = "年报社保信息（参保状态/年报五险一金欠税额）不能为null")
    private List<EntSocialSecurityNewInfo> entSocialSecurityNewInfos;

    /**
     * 济南市信用信息
     */
    @NotNull(message = "济南市信用信息不能为null")
    private JnCreditInfoNewInfo jnCreditInfoNewInfo;

    /**
     * 司法风险—开庭公告数据
     */
    @NotNull(message = "司法风险—开庭公告数据不能为null")
    private List<JusticeDeclareNewInfo> justiceDeclareNewInfos;

    /**
     * 司法风险—被执行人数据
     */
    @NotNull(message = "司法风险—被执行人数据不能为null")
    private List<JusticeEnforcedNewInfo> justiceEnforcedNewInfos;

    /**
     * 司法风险-裁判文书数据
     */
    @NotNull(message = "司法风险-裁判文书数据不能为null")
    private List<JusticeJudgeNewNewInfo> justiceJudgeNewNewInfos;

    /**
     * 单条数据
     */
    @NotNull(message = "单条数据不能为null")
    private OneDataNewInfo oneDataNewInfo;

}
