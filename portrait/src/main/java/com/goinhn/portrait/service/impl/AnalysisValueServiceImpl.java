package com.goinhn.portrait.service.impl;

import com.goinhn.portrait.mapper.analysis.*;
import com.goinhn.portrait.model.entity.analysis.*;
import com.goinhn.portrait.service.intf.AnalysisService;
import com.goinhn.portrait.constant.enums.Classification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 各个分类数值处理
 *
 * @author goinhn
 */
@Service
public class AnalysisValueServiceImpl implements AnalysisService {

    @Autowired
    private BusinessBackgroundAnalysisMapper businessBackgroundAnalysisMapper;

    @Autowired
    private BusinessManagementAbilityAnalysisMapper businessManagementAbilityAnalysisMapper;

    @Autowired
    private BusinessManagementRiskAnalysisMapper businessManagementAnalysisValueMapper;

    @Autowired
    private BusinessStabilityAnalysisMapper businessStabilityAnalysisMapper;

    @Autowired
    private CreditRiskAnalysisMapper creditRiskAnalysisMapper;

    @Autowired
    private JudicialRiskAnalysisMapper judicialRiskAnalysisMapper;


    @Override
    public Object getRiskValue(Classification classification, String entName) {
        try {
            switch (classification) {
                case BUSINESS_BACKGROUND:
                    BusinessBackgroundAnalysis businessBackgroundAnalysis = new BusinessBackgroundAnalysis();
                    businessBackgroundAnalysis.setEntName(entName);
                    return businessBackgroundAnalysisMapper.selectAllByEntName(businessBackgroundAnalysis);

                case BUSINESS_MANAGEMENT_ABILITY:
                    BusinessManagementAbilityAnalysis businessManagementAbilityAnalysis = new BusinessManagementAbilityAnalysis();
                    businessManagementAbilityAnalysis.setEntName(entName);
                    return businessManagementAbilityAnalysisMapper.selectAllByEntName(businessManagementAbilityAnalysis);

                case BUSINESS_MANAGEMENT_RISK:
                    BusinessManagementRiskAnalysis businessManagementRiskAnalysis = new BusinessManagementRiskAnalysis();
                    businessManagementRiskAnalysis.setEntName(entName);
                    return businessManagementAnalysisValueMapper.selectAllByEntName(businessManagementRiskAnalysis);

                case BUSINESS_STABILITY:
                    BusinessStabilityAnalysis businessStabilityAnalysis = new BusinessStabilityAnalysis();
                    businessStabilityAnalysis.setEntName(entName);
                    return businessStabilityAnalysisMapper.selectAllByEntName(businessStabilityAnalysis);

                case CREDIT_RISK:
                    CreditRiskAnalysis creditRiskAnalysis = new CreditRiskAnalysis();
                    creditRiskAnalysis.setEntName(entName);
                    return creditRiskAnalysisMapper.selectAllByEntName(creditRiskAnalysis);

                case JUDICIAL_RISK:
                    JudicialRiskAnalysis judicialRiskAnalysis = new JudicialRiskAnalysis();
                    judicialRiskAnalysis.setEntName(entName);
                    return judicialRiskAnalysisMapper.selectAllByEntName(judicialRiskAnalysis);

                default:
                    return null;
            }

        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
