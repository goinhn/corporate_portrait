package com.goinhn.portrait.service.impl;

import com.goinhn.portrait.mapper.analysis.*;
import com.goinhn.portrait.model.entity.analysis.*;
import com.goinhn.portrait.service.intf.AnalysisValueService;
import com.goinhn.portrait.constant.enums.Classification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;


/**
 * 各个分类数值处理
 *
 * @author goinhn
 */
@Service
public class AnalysisValueServiceImpl implements AnalysisValueService {

    @Autowired
    private BusinessBackgroundAnalysisMapper businessBackgroundAnalysisMapper;

    @Autowired
    private BusinessManagementAbilityAnalysisMapper businessManagementAbilityAnalysisMapper;

    @Autowired
    private BusinessManagementRiskAnalysisMapper businessManagementRiskAnalysisMapper;

    @Autowired
    private BusinessStabilityAnalysisMapper businessStabilityAnalysisMapper;

    @Autowired
    private CreditRiskAnalysisMapper creditRiskAnalysisMapper;

    @Autowired
    private JudicialRiskAnalysisMapper judicialRiskAnalysisMapper;


    @Override
    public Object getRiskValue(@NotNull(message = "分类不能为空") Classification classification,
                               @NotNull(message = "企业名称不能为空") String entName) {
        try {
            switch (classification) {
                case BUSINESS_BACKGROUND:
                    BusinessBackgroundAnalysis businessBackgroundAnalysis = BusinessBackgroundAnalysis
                            .builder()
                            .entName(entName)
                            .build();
                    return businessBackgroundAnalysisMapper.selectAllByEntName(businessBackgroundAnalysis);

                case BUSINESS_MANAGEMENT_ABILITY:
                    BusinessManagementAbilityAnalysis businessManagementAbilityAnalysis = BusinessManagementAbilityAnalysis
                            .builder()
                            .entName(entName)
                            .build();
                    return businessManagementAbilityAnalysisMapper.selectAllByEntName(businessManagementAbilityAnalysis);

                case BUSINESS_MANAGEMENT_RISK:
                    BusinessManagementRiskAnalysis businessManagementRiskAnalysis = BusinessManagementRiskAnalysis
                            .builder()
                            .entName(entName)
                            .build();
                    return businessManagementRiskAnalysisMapper.selectAllByEntName(businessManagementRiskAnalysis);

                case BUSINESS_STABILITY:
                    BusinessStabilityAnalysis businessStabilityAnalysis = BusinessStabilityAnalysis
                            .builder()
                            .entName(entName)
                            .build();
                    return businessStabilityAnalysisMapper.selectAllByEntName(businessStabilityAnalysis);

                case CREDIT_RISK:
                    CreditRiskAnalysis creditRiskAnalysis = CreditRiskAnalysis
                            .builder()
                            .entName(entName)
                            .build();
                    return creditRiskAnalysisMapper.selectAllByEntName(creditRiskAnalysis);

                case JUDICIAL_RISK:
                    JudicialRiskAnalysis judicialRiskAnalysis = JudicialRiskAnalysis
                            .builder()
                            .entName(entName)
                            .build();
                    return judicialRiskAnalysisMapper.selectAllByEntName(judicialRiskAnalysis);

                default:
                    return null;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public boolean saveRiskValueSingle(@NotNull(message = "分类不能为空") Classification classification,
                                       @NotNull(message = "分析类不能为空") Object analysis) {
        try {
            switch (classification) {
                case BUSINESS_BACKGROUND:
                    BusinessBackgroundAnalysis businessBackgroundAnalysis = (BusinessBackgroundAnalysis) analysis;
                    return businessBackgroundAnalysisMapper.saveBusinessBackgroundAnalysis(businessBackgroundAnalysis) == 1;

                case BUSINESS_MANAGEMENT_ABILITY:
                    BusinessManagementAbilityAnalysis businessManagementAbilityAnalysis = (BusinessManagementAbilityAnalysis) analysis;
                    return businessManagementAbilityAnalysisMapper.saveBusinessManagementAbilityAnalysis(businessManagementAbilityAnalysis) == 1;

                case BUSINESS_MANAGEMENT_RISK:
                    BusinessManagementRiskAnalysis businessManagementRiskAnalysis = (BusinessManagementRiskAnalysis) analysis;
                    return businessManagementRiskAnalysisMapper.saveBusinessManagementRiskAnalysis(businessManagementRiskAnalysis) == 1;

                case BUSINESS_STABILITY:
                    BusinessStabilityAnalysis businessStabilityAnalysis = (BusinessStabilityAnalysis) analysis;
                    return businessStabilityAnalysisMapper.saveBusinessStabilityAnalysis(businessStabilityAnalysis) == 1;

                case CREDIT_RISK:
                    CreditRiskAnalysis creditRiskAnalysis = (CreditRiskAnalysis) analysis;
                    return creditRiskAnalysisMapper.saveCreditRiskAnalysis(creditRiskAnalysis) == 1;

                case JUDICIAL_RISK:
                    JudicialRiskAnalysis judicialRiskAnalysis = (JudicialRiskAnalysis) analysis;
                    return judicialRiskAnalysisMapper.saveJudicialRiskAnalysis(judicialRiskAnalysis) == 1;

                default:
                    return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveRiskValueAll(@NotNull(message = "映射分类不能为空") Map<Classification, Object> map) {
        Iterator<Entry<Classification, Object>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Entry<Classification, Object> entry = iterator.next();
            if (!saveRiskValueSingle(entry.getKey(), Optional.ofNullable(entry.getValue()).get())) {
                return false;
            }
        }

        return true;
    }

}
