package com.goinhn.portrait.service.impl;

import com.goinhn.portrait.dao.*;
import com.goinhn.portrait.model.*;
import com.goinhn.portrait.service.AnalysisService;
import com.goinhn.portrait.util.Classification;
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
    private BusinessBackgroundValueMapper businessBackgroundValueMapper;

    @Autowired
    private BusinessManagementAbilityValueMapper businessManagementAbilityValueMapper;

    @Autowired
    private BusinessManagementRiskValueMapper businessManagementRiskValueMapper;

    @Autowired
    private BusinessStabilityValueMapper businessStabilityValueMapper;

    @Autowired
    private CreditRiskValueMapper creditRiskValueMapper;

    @Autowired
    private JudicialRiskValueMapper judicialRiskValueMapper;


    @Override
    public Object riskValue(Classification classification, String entName) {
        switch (classification) {
            case BUSINESS_BACKGROUND:
                BusinessBackgroundValue businessBackgroundValue = new BusinessBackgroundValue();
                businessBackgroundValue.setEntName(entName);
                return businessBackgroundValueMapper.selectAllByEntName(businessBackgroundValue);

            case BUSINESS_MANAGEMENT_ABILITY:
                BusinessManagementAbilityValue businessManagementAbilityValue = new BusinessManagementAbilityValue();
                businessManagementAbilityValue.setEntName(entName);
                return businessManagementAbilityValueMapper.selectAllByEntName(businessManagementAbilityValue);

            case BUSINESS_MANAGEMENT_RISK:
                BusinessManagementRiskValue businessManagementRiskValue = new BusinessManagementRiskValue();
                businessManagementRiskValue.setEntName(entName);
                return businessManagementRiskValueMapper.selectAllByEntName(businessManagementRiskValue);

            case BUSINESS_STABILITY:
                BusinessStabilityValue businessStabilityValue = new BusinessStabilityValue();
                businessStabilityValue.setEntName(entName);
                return businessStabilityValueMapper.selectAllByEntName(businessStabilityValue);

            case CREDIT_RISK:
                CreditRiskValue creditRiskValue = new CreditRiskValue();
                creditRiskValue.setEntName(entName);
                return creditRiskValueMapper.selectAllByEntName(creditRiskValue);

            case JUDICIAL_RISK:
                JudicialRiskValue judicialRiskValue = new JudicialRiskValue();
                judicialRiskValue.setEntName(entName);
                return judicialRiskValueMapper.selectAllByEntName(judicialRiskValue);

            default:
                return null;
        }
    }
}
