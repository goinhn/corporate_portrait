package com.goinhn.portrait.controller;

import com.goinhn.portrait.constant.enums.Classification;
import com.goinhn.portrait.model.dto.ShowLabel;
import com.goinhn.portrait.model.dto.ShowTableInfo;
import com.goinhn.portrait.model.entity.ShowInfo;
import com.goinhn.portrait.model.entity.analysis.*;
import com.goinhn.portrait.model.vo.ResultInfo;
import com.goinhn.portrait.service.intf.AnalysisValueService;
import com.goinhn.portrait.service.intf.LabelService;
import com.goinhn.portrait.service.intf.ShowInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.*;

/**
 * @author goinhn
 */
@Api(tags = "搜索接口")
@RestController
@RequestMapping(value = "/search")
@CrossOrigin
@Slf4j
public class SearchController {

    @Autowired
    private AnalysisValueService analysisValueService;

    @Autowired
    private LabelService labelService;

    @Autowired
    private ShowInfoService showInfoService;


    /**
     * @param entName 企业名称
     * @return
     */
    @ApiOperation(value = "企业名称搜索接口")
    @GetMapping(value = "/searchEntName/{entName}")
    public ResultInfo searchEntName(@PathVariable("entName")
                                    @NotNull(message = "搜索的企业名称不为空") String entName) {
        log.info("/por/search/searchEntName/" + entName);

        String[] entNames = entName.split(",");
        List<String> entNameList = new ArrayList<>();
        for (String name : entNames) {
            if ("".equals(name.toString())) {
                continue;
            }
            entNameList.add(name);
        }

        if (entNameList.size() == 0) {
            return ResultInfo
                    .builder()
                    .flag(false)
                    .errorMsg("查询的字段有误")
                    .build();
        }

        List<ShowTableInfo> showTableInfos = new ArrayList<>();
        int size = entNameList.size();
        for (String s : entNameList) {
            List<ShowTableInfo> tables = showInfoService.getTableInfo(s, size);
            if (tables != null) {
                showTableInfos.addAll(tables);
            }
        }

        if (showTableInfos.size() == 0) {
            return ResultInfo
                    .builder()
                    .flag(false)
                    .errorMsg("不存在该公司")
                    .build();
        }

        return ResultInfo
                .builder()
                .flag(true)
                .data(showTableInfos)
                .build();
    }


    /**
     * @param entName 企业名称
     * @return
     */
    @ApiOperation(value = "企业信息搜索接口")
    @GetMapping(value = "/searchEntInfo/{entName}")
    public ResultInfo searchEntInfo(@PathVariable("entName")
                                    @NotNull(message = "搜索的企业名称不为空") String entName) {
        log.info("/por/searchEntInfo/" + entName);
        if ("".equals(entName) || entName == null) {
            return ResultInfo
                    .builder()
                    .flag(false)
                    .errorMsg("查询发生错误！")
                    .build();
        }

        Map<String, Object> resultMap = new HashMap<>(3);

        resultMap = delInfo(resultMap, entName);
        resultMap = delChart(resultMap, entName);
        resultMap = delLabel(resultMap, entName);

        return ResultInfo
                .builder()
                .flag(true)
                .data(resultMap)
                .build();
    }

    /**
     * 处理展示信息部分
     *
     * @param resultMap 返回的集合
     * @param entName   企业名称
     * @return
     */
    private Map<String, Object> delInfo(Map<String, Object> resultMap, String entName) {
        ShowInfo showInfo = showInfoService.getShowInfo(entName);
        resultMap.put("info", showInfo);

        return resultMap;
    }

    /**
     * 处理雷达图数值部分
     *
     * @param resultMap 返回的集合
     * @param entName   企业名称
     * @return
     */
    private Map<String, Object> delChart(Map<String, Object> resultMap, String entName) {
        Map<String, List<Map<String, Object>>> chartMap = new HashMap<>(6);

        BusinessBackgroundAnalysis businessBackgroundAnalysis =
                (BusinessBackgroundAnalysis) analysisValueService.getRiskValue(Classification.BUSINESS_BACKGROUND, entName);
        BusinessManagementAbilityAnalysis businessManagementAbilityAnalysis =
                (BusinessManagementAbilityAnalysis) analysisValueService.getRiskValue(Classification.BUSINESS_MANAGEMENT_ABILITY, entName);
        BusinessManagementRiskAnalysis businessManagementRiskAnalysis =
                (BusinessManagementRiskAnalysis) analysisValueService.getRiskValue(Classification.BUSINESS_MANAGEMENT_RISK, entName);
        BusinessStabilityAnalysis businessStabilityAnalysis =
                (BusinessStabilityAnalysis) analysisValueService.getRiskValue(Classification.BUSINESS_STABILITY, entName);
        CreditRiskAnalysis creditRiskAnalysis =
                (CreditRiskAnalysis) analysisValueService.getRiskValue(Classification.CREDIT_RISK, entName);
        JudicialRiskAnalysis judicialRiskAnalysis =
                (JudicialRiskAnalysis) analysisValueService.getRiskValue(Classification.JUDICIAL_RISK, entName);

        if (businessBackgroundAnalysis != null) {
            Map<String, Object> map = new HashMap<>(2);
            map.put("name", "企业背景分析");
            List<Double> list = new ArrayList<>();
            list.add(Optional.ofNullable(businessBackgroundAnalysis.getEmpNum()).orElse(0.0));
            list.add(Optional.ofNullable(businessBackgroundAnalysis.getEncodeEntStatus()).orElse(0.0));
            list.add(Optional.ofNullable(businessBackgroundAnalysis.getShopNum()).orElse(0.0));
            list.add(Optional.ofNullable(businessBackgroundAnalysis.getBranchNum()).orElse(0.0));
            list.add(Optional.ofNullable(businessBackgroundAnalysis.getIsInfoA()).orElse(0.0));
            list.add(Optional.ofNullable(businessBackgroundAnalysis.getIsInfoB()).orElse(0.0));
            list.add(Optional.ofNullable(businessBackgroundAnalysis.getLevelRank()).orElse(0.0));
            map.put("value", list);
            List<Map<String, Object>> listKind = new ArrayList<>();
            listKind.add(map);
            chartMap.put("businessBackground", listKind);
        } else {
            chartMap.put("businessBackground", null);
        }

        if (businessManagementAbilityAnalysis != null) {
            Map<String, Object> map = new HashMap<>(2);
            map.put("name", "企业经营能力分析");
            List<Double> list = new ArrayList<>();
            list.add(Optional.ofNullable(businessManagementAbilityAnalysis.getEvaluation()).orElse(0.0));
            list.add(Optional.ofNullable(businessManagementAbilityAnalysis.getInvestNum()).orElse(0.0));
            list.add(Optional.ofNullable(businessManagementAbilityAnalysis.getBidNum()).orElse(0.0));
            list.add(Optional.ofNullable(businessManagementAbilityAnalysis.getCbzt()).orElse(0.0));
            list.add(Optional.ofNullable(businessManagementAbilityAnalysis.getCbzt()).orElse(0.0));
            list.add(Optional.ofNullable(businessManagementAbilityAnalysis.getIBrandNum()).orElse(0.0));
            list.add(Optional.ofNullable(businessManagementAbilityAnalysis.getICopyNum()).orElse(0.0));
            list.add(Optional.ofNullable(businessManagementAbilityAnalysis.getIPatNum()).orElse(0.0));
            list.add(Optional.ofNullable(businessManagementAbilityAnalysis.getIDomNum()).orElse(0.0));
            list.add(Optional.ofNullable(businessManagementAbilityAnalysis.getPassPercent()).orElse(0.0));
            map.put("value", list);
            List<Map<String, Object>> listKind = new ArrayList<>();
            listKind.add(map);
            chartMap.put("businessManagementAbility", listKind);
        } else {
            chartMap.put("businessManagementAbility", null);
        }

        if (businessManagementRiskAnalysis != null) {
            Map<String, Object> map = new HashMap<>(2);
            map.put("name", "企业经营风险分析");
            List<Double> list = new ArrayList<>();
            list.add(Optional.ofNullable(businessManagementRiskAnalysis.getPriclasecam()).orElse(0.0));
            list.add(Optional.ofNullable(businessManagementRiskAnalysis.getEncodeGuaranperiod()).orElse(0.0));
            list.add(Optional.ofNullable(businessManagementRiskAnalysis.getEncodeGatype()).orElse(0.0));
            list.add(Optional.ofNullable(businessManagementRiskAnalysis.getIsRage()).orElse(0.0));
            list.add(Optional.ofNullable(businessManagementRiskAnalysis.getSubPefperfromto()).orElse(0.0));
            list.add(Optional.ofNullable(businessManagementRiskAnalysis.getUnpaidsocialins()).orElse(0.0));
            list.add(Optional.ofNullable(businessManagementRiskAnalysis.getIsBra()).orElse(0.0));
            list.add(Optional.ofNullable(businessManagementRiskAnalysis.getIsBrap()).orElse(0.0));
            list.add(Optional.ofNullable(businessManagementRiskAnalysis.getPledgeNum()).orElse(0.0));
            list.add(Optional.ofNullable(businessManagementRiskAnalysis.getTaxunpaidNum()).orElse(0.0));
            list.add(Optional.ofNullable(businessManagementRiskAnalysis.getIsExcept()).orElse(0.0));
            map.put("value", list);
            List<Map<String, Object>> listKind = new ArrayList<>();
            listKind.add(map);
            chartMap.put("businessManagementRisk", listKind);
        } else {
            chartMap.put("businessManagementRisk", null);
        }

        if (businessStabilityAnalysis != null) {
            Map<String, Object> map = new HashMap<>(2);
            map.put("name", "企业稳定性分析");
            List<Double> list = new ArrayList<>();
            list.add(Optional.ofNullable(businessStabilityAnalysis.getAltTime()).orElse(0.0));
            map.put("value", list);
            List<Map<String, Object>> listKind = new ArrayList<>();
            listKind.add(map);
            chartMap.put("businessStability", listKind);
        } else {
            chartMap.put("businessStability", null);
        }

        if (creditRiskAnalysis != null) {
            Map<String, Object> map = new HashMap<>(2);
            map.put("name", "信用风险分析");
            List<Double> list = new ArrayList<>();
            list.add(Optional.ofNullable(creditRiskAnalysis.getIsPunish()).orElse(0.0));
            list.add(Optional.ofNullable(creditRiskAnalysis.getIsKcont()).orElse(0.0));
            list.add(Optional.ofNullable(creditRiskAnalysis.getCreditGrade()).orElse(0.0));
            list.add(Optional.ofNullable(creditRiskAnalysis.getIsJusticeCreditaic()).orElse(0.0));
            map.put("value", list);
            List<Map<String, Object>> listKind = new ArrayList<>();
            listKind.add(map);
            chartMap.put("creditRisk", listKind);
        } else {
            chartMap.put("creditRisk", null);
        }

        if (judicialRiskAnalysis != null) {
            Map<String, Object> map = new HashMap<>(2);
            map.put("name", "司法风险分析");
            List<Double> list = new ArrayList<>();
            list.add(Optional.ofNullable(judicialRiskAnalysis.getLawSum()).orElse(0.0));
            list.add(Optional.ofNullable(judicialRiskAnalysis.getDefendant()).orElse(0.0));
            list.add(Optional.ofNullable(judicialRiskAnalysis.getEnforceAmount()).orElse(0.0));
            list.add(Optional.ofNullable(judicialRiskAnalysis.getIsJusticeCredit()).orElse(0.0));
            map.put("value", list);
            List<Map<String, Object>> listKind = new ArrayList<>();
            listKind.add(map);
            chartMap.put("judicialRisk", listKind);
        } else {
            chartMap.put("judicialRisk", null);
        }

        resultMap.put("chart", chartMap);
        return resultMap;
    }

    /**
     * 标签信息处理
     *
     * @param resultMap 返回的集合
     * @param entName   企业名称
     * @return
     */
    private Map<String, Object> delLabel(Map<String, Object> resultMap, String entName) {
        ShowLabel showLabel = labelService.getLabelValue(entName);
        resultMap.put("label", showLabel);

        return resultMap;
    }

    //    @PostMapping(value = "/test", produces = "application/json")
//    public ResultInfo test(@RequestBody Te te) {
//
//    }

}
