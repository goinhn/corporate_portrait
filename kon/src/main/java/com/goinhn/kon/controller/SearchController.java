package com.goinhn.kon.controller;


import com.goinhn.kon.constant.enums.Classification;
import com.goinhn.kon.model.dto.ShowLabel;
import com.goinhn.kon.model.dto.ShowTableInfo;
import com.goinhn.kon.model.entity.ShowInfo;
import com.goinhn.kon.model.entity.analysis.*;
import com.goinhn.kon.model.entity.original.*;
import com.goinhn.kon.model.vo.ResultInfo;
import com.goinhn.kon.service.intf.AnalysisValueService;
import com.goinhn.kon.service.intf.LabelService;
import com.goinhn.kon.service.intf.OriginalValueService;
import com.goinhn.kon.service.intf.ShowInfoService;
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
    private OriginalValueService originalValueService;

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
        log.info("/kon/search/searchEntName/{entName}" + "----------" + entName + "\n");

        String[] entNames = entName.split(",");
        List<String> entNameList = new ArrayList<>();
        for (String name : entNames) {
            if ("".equals(name.toString())) {
                continue;
            }
            entNameList.add(name);
        }

        if (entNameList.size() == 0) {
            ResultInfo resultInfo = ResultInfo
                    .builder()
                    .flag(false)
                    .status(200)
                    .errorMsg("查询的字段有误")
                    .build();
            log.info("/kon/search/searchEntName/{entName}" + "++++++++++" + resultInfo.toString() + "\n");
            return resultInfo;
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
            ResultInfo resultInfo = ResultInfo
                    .builder()
                    .flag(false)
                    .status(200)
                    .errorMsg("不存在该公司")
                    .build();
            log.info("/kon/search/searchEntName/{entName}" + "++++++++++" + resultInfo.toString() + "\n");
            return resultInfo;
        }

        ResultInfo resultInfo = ResultInfo
                .builder()
                .flag(true)
                .status(200)
                .data(showTableInfos)
                .build();
//        log.info("/kon/search/searchEntName/{entName}" + "++++++++++" + resultInfo.toString() + "\n");
        return resultInfo;
    }


    /**
     * @param entName 企业名称
     * @return
     */
    @ApiOperation(value = "企业信息搜索接口")
    @GetMapping(value = "/searchEntInfo/{entName}")
    public ResultInfo searchEntInfo(@PathVariable("entName")
                                    @NotNull(message = "搜索的企业名称不为空") String entName) {
        log.info("/kon/searchEntInfo/{entName)" + "----------" + entName + "\n");
        if ("".equals(entName) || entName == null) {
            ResultInfo resultInfo = ResultInfo
                    .builder()
                    .flag(false)
                    .status(200)
                    .errorMsg("查询发生错误！")
                    .build();
            log.info("/kon/searchEntInfo/{entName)" + "++++++++++" + resultInfo.toString() + "\n");
            return resultInfo;
        }

        Map<String, Object> resultMap = new HashMap<>(3);

        resultMap = dealInfo(resultMap, entName);
        resultMap = dealOriginal(resultMap, entName);
        resultMap = dealAnalysis(resultMap, entName);
        resultMap = dealLabel(resultMap, entName);

        ResultInfo resultInfo = ResultInfo
                .builder()
                .flag(true)
                .status(200)
                .data(resultMap)
                .build();
        log.info("/kon/searchEntInfo/{entName)" + "++++++++++" + resultInfo.toString() + "\n");
        return resultInfo;
    }

    /**
     * 处理展示信息部分
     *
     * @param resultMap 返回的集合
     * @param entName   企业名称
     * @return
     */
    private Map<String, Object> dealInfo(Map<String, Object> resultMap, String entName) {
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
    private Map<String, Object> dealAnalysis(Map<String, Object> resultMap, String entName) {
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
            list.add(Optional.ofNullable(businessManagementAbilityAnalysis.getIbrandNum()).orElse(0.0));
            list.add(Optional.ofNullable(businessManagementAbilityAnalysis.getIcopyNum()).orElse(0.0));
            list.add(Optional.ofNullable(businessManagementAbilityAnalysis.getIpatNum()).orElse(0.0));
            list.add(Optional.ofNullable(businessManagementAbilityAnalysis.getIdomNum()).orElse(0.0));
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

        resultMap.put("analysis", chartMap);
        return resultMap;
    }


    /**
     * 处理雷达图原始数值部分
     *
     * @param resultMap 返回的集合
     * @param entName   企业名称
     * @return
     */
    private Map<String, Object> dealOriginal(Map<String, Object> resultMap, String entName) {
        Map<String, List<Map<String, Object>>> chartMap = new HashMap<>(6);

        BusinessBackgroundOriginal businessBackgroundOriginal =
                (BusinessBackgroundOriginal) originalValueService.getRiskValue(Classification.BUSINESS_BACKGROUND, entName);
        BusinessManagementAbilityOriginal businessManagementAbilityOriginal =
                (BusinessManagementAbilityOriginal) originalValueService.getRiskValue(Classification.BUSINESS_MANAGEMENT_ABILITY, entName);
        BusinessManagementRiskOriginal businessManagementRiskOriginal =
                (BusinessManagementRiskOriginal) originalValueService.getRiskValue(Classification.BUSINESS_MANAGEMENT_RISK, entName);
        BusinessStabilityOriginal businessStabilityOriginal =
                (BusinessStabilityOriginal) originalValueService.getRiskValue(Classification.BUSINESS_STABILITY, entName);
        CreditRiskOriginal creditRiskOriginal =
                (CreditRiskOriginal) originalValueService.getRiskValue(Classification.CREDIT_RISK, entName);
        JudicialRiskOriginal judicialRiskOriginal =
                (JudicialRiskOriginal) originalValueService.getRiskValue(Classification.JUDICIAL_RISK, entName);

        if (businessBackgroundOriginal != null) {
            Map<String, Object> map = new HashMap<>(2);
            map.put("name", "企业背景分析");
            List<Double> list = new ArrayList<>();
            list.add(Optional.ofNullable(businessBackgroundOriginal.getEmpNum()).orElse(0.0));
            list.add(Optional.ofNullable(businessBackgroundOriginal.getEncodeEntStatus()).orElse(0.0));
            list.add(Optional.ofNullable(businessBackgroundOriginal.getShopNum()).orElse(0.0));
            list.add(Optional.ofNullable(businessBackgroundOriginal.getBranchNum()).orElse(0.0));
            list.add(Optional.ofNullable(businessBackgroundOriginal.getIsInfoA()).orElse(0.0));
            list.add(Optional.ofNullable(businessBackgroundOriginal.getIsInfoB()).orElse(0.0));
            list.add(Optional.ofNullable(businessBackgroundOriginal.getLevelRank()).orElse(0.0));
            map.put("value", list);
            List<Map<String, Object>> listKind = new ArrayList<>();
            listKind.add(map);
            chartMap.put("businessBackground", listKind);
        } else {
            chartMap.put("businessBackground", null);
        }

        if (businessManagementAbilityOriginal != null) {
            Map<String, Object> map = new HashMap<>(2);
            map.put("name", "企业经营能力分析");
            List<Double> list = new ArrayList<>();
            list.add(Optional.ofNullable(businessManagementAbilityOriginal.getEvaluation()).orElse(0.0));
            list.add(Optional.ofNullable(businessManagementAbilityOriginal.getInvestNum()).orElse(0.0));
            list.add(Optional.ofNullable(businessManagementAbilityOriginal.getBidNum()).orElse(0.0));
            list.add(Optional.ofNullable(businessManagementAbilityOriginal.getCbzt()).orElse(0.0));
            list.add(Optional.ofNullable(businessManagementAbilityOriginal.getIbrandNum()).orElse(0.0));
            list.add(Optional.ofNullable(businessManagementAbilityOriginal.getIcopyNum()).orElse(0.0));
            list.add(Optional.ofNullable(businessManagementAbilityOriginal.getIpatNum()).orElse(0.0));
            list.add(Optional.ofNullable(businessManagementAbilityOriginal.getIdomNum()).orElse(0.0));
            list.add(Optional.ofNullable(businessManagementAbilityOriginal.getPassPercent()).orElse(0.0));
            map.put("value", list);
            List<Map<String, Object>> listKind = new ArrayList<>();
            listKind.add(map);
            chartMap.put("businessManagementAbility", listKind);
        } else {
            chartMap.put("businessManagementAbility", null);
        }

        if (businessManagementRiskOriginal != null) {
            Map<String, Object> map = new HashMap<>(2);
            map.put("name", "企业经营风险分析");
            List<Double> list = new ArrayList<>();
            list.add(Optional.ofNullable(businessManagementRiskOriginal.getPriclasecam()).orElse(0.0));
            list.add(Optional.ofNullable(businessManagementRiskOriginal.getEncodeGuaranperiod()).orElse(0.0));
            list.add(Optional.ofNullable(businessManagementRiskOriginal.getEncodeGatype()).orElse(0.0));
            list.add(Optional.ofNullable(businessManagementRiskOriginal.getIsRage()).orElse(0.0));
            list.add(Optional.ofNullable(businessManagementRiskOriginal.getSubPefperfromto()).orElse(0.0));
            list.add(Optional.ofNullable(businessManagementRiskOriginal.getUnpaidsocialins()).orElse(0.0));
            list.add(Optional.ofNullable(businessManagementRiskOriginal.getIsBra()).orElse(0.0));
            list.add(Optional.ofNullable(businessManagementRiskOriginal.getIsBrap()).orElse(0.0));
            list.add(Optional.ofNullable(businessManagementRiskOriginal.getPledgeNum()).orElse(0.0));
            list.add(Optional.ofNullable(businessManagementRiskOriginal.getTaxunpaidNum()).orElse(0.0));
            list.add(Optional.ofNullable(businessManagementRiskOriginal.getIsExcept()).orElse(0.0));
            map.put("value", list);
            List<Map<String, Object>> listKind = new ArrayList<>();
            listKind.add(map);
            chartMap.put("businessManagementRisk", listKind);
        } else {
            chartMap.put("businessManagementRisk", null);
        }

        if (businessStabilityOriginal != null) {
            Map<String, Object> map = new HashMap<>(2);
            map.put("name", "企业稳定性分析");
            List<Double> list = new ArrayList<>();
            list.add(Optional.ofNullable(businessStabilityOriginal.getAltTime()).orElse(0.0));
            map.put("value", list);
            List<Map<String, Object>> listKind = new ArrayList<>();
            listKind.add(map);
            chartMap.put("businessStability", listKind);
        } else {
            chartMap.put("businessStability", null);
        }

        if (creditRiskOriginal != null) {
            Map<String, Object> map = new HashMap<>(2);
            map.put("name", "信用风险分析");
            List<Double> list = new ArrayList<>();
            list.add(Optional.ofNullable(creditRiskOriginal.getIsPunish()).orElse(0.0));
            list.add(Optional.ofNullable(creditRiskOriginal.getIsKcont()).orElse(0.0));
            list.add(Optional.ofNullable(creditRiskOriginal.getCreditGrade()).orElse(0.0));
            list.add(Optional.ofNullable(creditRiskOriginal.getIsJusticeCreditaic()).orElse(0.0));
            map.put("value", list);
            List<Map<String, Object>> listKind = new ArrayList<>();
            listKind.add(map);
            chartMap.put("creditRisk", listKind);
        } else {
            chartMap.put("creditRisk", null);
        }

        if (judicialRiskOriginal != null) {
            Map<String, Object> map = new HashMap<>(2);
            map.put("name", "司法风险分析");
            List<Double> list = new ArrayList<>();
            list.add(Optional.ofNullable(judicialRiskOriginal.getLawSum()).orElse(0.0));
            list.add(Optional.ofNullable(judicialRiskOriginal.getDefendant()).orElse(0.0));
            list.add(Optional.ofNullable(judicialRiskOriginal.getEnforceAmount()).orElse(0.0));
            list.add(Optional.ofNullable(judicialRiskOriginal.getIsJusticeCredit()).orElse(0.0));
            map.put("value", list);
            List<Map<String, Object>> listKind = new ArrayList<>();
            listKind.add(map);
            chartMap.put("judicialRisk", listKind);
        } else {
            chartMap.put("judicialRisk", null);
        }

        resultMap.put("original", chartMap);
        return resultMap;
    }


    /**
     * 标签信息处理
     *
     * @param resultMap 返回的集合
     * @param entName   企业名称
     * @return
     */
    private Map<String, Object> dealLabel(Map<String, Object> resultMap, String entName) {
        long before = System.currentTimeMillis();
        ShowLabel showLabel = labelService.getLabelValue(entName);
        long end = System.currentTimeMillis();
        long label_time = end - before;
        resultMap.put("label", showLabel);
        resultMap.put("label_time", label_time);

        return resultMap;
    }

}
