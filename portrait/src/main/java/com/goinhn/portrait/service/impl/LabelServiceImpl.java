package com.goinhn.portrait.service.impl;

import com.goinhn.portrait.mapper.*;
import com.goinhn.portrait.model.entity.*;
import com.goinhn.portrait.model.vo.ShowLabel;
import com.goinhn.portrait.service.intf.LabelService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 标签值的处理
 *
 * @author goinhn
 */
public class LabelServiceImpl implements LabelService {

    @Autowired
    private BusinessBackgroundLabelMapper businessBackgroundLabelMapper;

    @Autowired
    private BusinessManagementAbilityLabelMapper businessManagementAbilityLabelMapper;

    @Autowired
    private BusinessManagementRiskLabelMapper businessManagementRiskLabelMapper;

    @Autowired
    private BusinessStabilityLabelMapper businessStabilityLabelMapper;

    @Autowired
    private CreditRiskLabelMapper creditRiskLabelMapper;

    @Autowired
    private JudicialRiskLabelMapper judicialRiskLabelMapper;


    @Override
    public ShowLabel getLabelValue(String entName) {
        BusinessBackgroundLabel businessBackgroundLabel = new BusinessBackgroundLabel();
        businessBackgroundLabel.setEntName(entName);
        businessBackgroundLabel = businessBackgroundLabelMapper.selectByEntName(businessBackgroundLabel);

        BusinessManagementAbilityLabel businessManagementAbilityLabel = new BusinessManagementAbilityLabel();
        businessManagementAbilityLabel.setEntName(entName);
        businessManagementAbilityLabel = businessManagementAbilityLabelMapper.selectByEntName(businessManagementAbilityLabel);

        BusinessManagementRiskLabel businessManagementRiskLabel = new BusinessManagementRiskLabel();
        businessManagementRiskLabel.setEntName(entName);
        businessManagementRiskLabel = businessManagementRiskLabelMapper.selectByEntName(businessManagementRiskLabel);

        BusinessStabilityLabel businessStabilityLabel = new BusinessStabilityLabel();
        businessStabilityLabel.setEntName(entName);
        businessStabilityLabel = businessStabilityLabelMapper.selectByEntName(businessStabilityLabel);

        CreditRiskLabel creditRiskLabel = new CreditRiskLabel();
        creditRiskLabel.setEntName(entName);
        creditRiskLabel = creditRiskLabelMapper.selectByEntName(creditRiskLabel);

        JudicialRiskLabel judicialRiskLabel = new JudicialRiskLabel();
        judicialRiskLabel.setEntName(entName);
        judicialRiskLabel = judicialRiskLabelMapper.selectByEntName(judicialRiskLabel);


        ShowLabel showLabel = new ShowLabel();
        showLabel.setEntName(entName);
        showLabel.setBusinessBackgroundLabel(businessBackgroundLabel.getLabel());
        showLabel.setBusinessManagementAbilityLabel(businessManagementAbilityLabel.getLabel());
        showLabel.setBusinessManagementRiskLabel(businessManagementRiskLabel.getLabel());
        showLabel.setBusinessStabilityLabel(businessStabilityLabel.getLabel());
        showLabel.setCreditRiskLabel(creditRiskLabel.getLabel());
        showLabel.setJudicialRiskLabel(judicialRiskLabel.getLabel());

        return showLabel;
    }
}
