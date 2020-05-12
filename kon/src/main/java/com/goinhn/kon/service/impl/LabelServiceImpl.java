package com.goinhn.kon.service.impl;

import com.goinhn.kon.constant.enums.Classification;
import com.goinhn.kon.mapper.LabelKindMapper;
import com.goinhn.kon.mapper.label.*;
import com.goinhn.kon.model.dto.ShowLabel;
import com.goinhn.kon.model.entity.LabelKind;
import com.goinhn.kon.model.entity.label.*;
import com.goinhn.kon.model.vo.NewOriginalAnalysisLabel;
import com.goinhn.kon.service.intf.LabelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * 标签值的处理
 *
 * @author goinhn
 */
@Slf4j
@Service
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

    @Autowired
    private LabelKindMapper labelKindMapper;


    /**
     * 在数据库中查找标签所属分类
     *
     * @param enumKind  种类
     * @param kind      种类String
     * @param mapNumber 6个种类标签
     * @return
     */
    private String searchCorrect(@NotNull Classification enumKind,
                                 @NotNull String kind,
                                 @NotNull Map<Classification, Integer> mapNumber) {
        if (mapNumber.get(enumKind) == -1) {
            return "";
        } else {
            LabelKind labelKind = LabelKind
                    .builder()
                    .kind(kind)
                    .number(mapNumber.get(enumKind))
                    .build();
            try {
                labelKind = labelKindMapper.selectAllByKindAndNumber(labelKind);
            } catch (Exception e) {
                e.printStackTrace();
            }

            return Optional.ofNullable(labelKind)
                    .map(i -> i.getLabel())
                    .orElse("");
        }
    }


    @Override
    public Map<Classification, Integer> getLabelNumber(@NotNull String entName) {
        BusinessBackgroundLabel businessBackgroundLabel = new BusinessBackgroundLabel();
        businessBackgroundLabel.setEntName(entName);
        BusinessManagementAbilityLabel businessManagementAbilityLabel = new BusinessManagementAbilityLabel();
        businessManagementAbilityLabel.setEntName(entName);
        BusinessManagementRiskLabel businessManagementRiskLabel = new BusinessManagementRiskLabel();
        businessManagementRiskLabel.setEntName(entName);
        BusinessStabilityLabel businessStabilityLabel = new BusinessStabilityLabel();
        businessStabilityLabel.setEntName(entName);
        CreditRiskLabel creditRiskLabel = new CreditRiskLabel();
        creditRiskLabel.setEntName(entName);
        JudicialRiskLabel judicialRiskLabel = new JudicialRiskLabel();
        judicialRiskLabel.setEntName(entName);
        try {
            businessBackgroundLabel = businessBackgroundLabelMapper.selectAllByEntName(businessBackgroundLabel);

            businessManagementAbilityLabel = businessManagementAbilityLabelMapper.selectAllByEntName(businessManagementAbilityLabel);

            businessManagementRiskLabel = businessManagementRiskLabelMapper.selectAllByEntName(businessManagementRiskLabel);

            businessStabilityLabel = businessStabilityLabelMapper.selectAllByEntName(businessStabilityLabel);

            creditRiskLabel = creditRiskLabelMapper.selectAllByEntName(creditRiskLabel);

            judicialRiskLabel = judicialRiskLabelMapper.selectAllByEntName(judicialRiskLabel);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //存储返回的6个标签数字
        Map<Classification, Integer> mapNumber = new HashMap<>(6);
        mapNumber.put(Classification.BUSINESS_BACKGROUND,
                Optional.ofNullable(businessBackgroundLabel)
                        .map(i -> i.getLabel())
                        .orElse(-1));
        mapNumber.put(Classification.BUSINESS_MANAGEMENT_ABILITY,
                Optional.ofNullable(businessManagementAbilityLabel)
                        .map(i -> i.getLabel())
                        .orElse(-1));
        mapNumber.put(Classification.BUSINESS_MANAGEMENT_RISK,
                Optional.ofNullable(businessManagementRiskLabel)
                        .map(i -> i.getLabel())
                        .orElse(-1));
        mapNumber.put(Classification.BUSINESS_STABILITY,
                Optional.ofNullable(businessStabilityLabel)
                        .map(i -> i.getLabel())
                        .orElse(-1));
        mapNumber.put(Classification.CREDIT_RISK,
                Optional.ofNullable(creditRiskLabel)
                        .map(i -> i.getLabel())
                        .orElse(-1));
        mapNumber.put(Classification.JUDICIAL_RISK,
                Optional.ofNullable(judicialRiskLabel)
                        .map(i -> i.getLabel())
                        .orElse(-1));

        return mapNumber;
    }


    @Override
    public ShowLabel getLabelValue(@NotNull String entName) {
        Map<Classification, Integer> mapNumber = getLabelNumber(entName);
        ShowLabel showLabel = ShowLabel
                .builder()
                .entName(entName)
                .build();

        //根据标签的数值取出对应的标签
        if (mapNumber != null) {
            showLabel.setBusinessBackgroundLabel(searchCorrect(Classification.BUSINESS_BACKGROUND, "企业背景", mapNumber));
            showLabel.setBusinessManagementAbilityLabel(searchCorrect(Classification.BUSINESS_MANAGEMENT_ABILITY, "企业经营能力", mapNumber));
            showLabel.setBusinessManagementRiskLabel(searchCorrect(Classification.BUSINESS_MANAGEMENT_RISK, "企业经营风险", mapNumber));
            showLabel.setBusinessStabilityLabel(searchCorrect(Classification.BUSINESS_STABILITY, "企业稳定性", mapNumber));
            showLabel.setCreditRiskLabel(searchCorrect(Classification.CREDIT_RISK, "企业信用风险", mapNumber));
            showLabel.setJudicialRiskLabel(searchCorrect(Classification.JUDICIAL_RISK, "企业司法风险", mapNumber));
        } else {
            showLabel.setBusinessBackgroundLabel("");
            showLabel.setBusinessManagementAbilityLabel("");
            showLabel.setBusinessManagementRiskLabel("");
            showLabel.setBusinessStabilityLabel("");
            showLabel.setCreditRiskLabel("");
            showLabel.setJudicialRiskLabel("");
        }

        return showLabel;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveLabelValue(@NotNull Map<Classification, Object> map) throws Exception {
        BusinessBackgroundLabel businessBackgroundLabel =
                (BusinessBackgroundLabel) Optional.ofNullable(map.get(Classification.BUSINESS_BACKGROUND)).get();
        BusinessManagementAbilityLabel businessManagementAbilityLabel =
                (BusinessManagementAbilityLabel) Optional.ofNullable(map.get(Classification.BUSINESS_MANAGEMENT_ABILITY)).get();
        BusinessManagementRiskLabel businessManagementRiskLabel =
                (BusinessManagementRiskLabel) Optional.ofNullable(map.get(Classification.BUSINESS_MANAGEMENT_RISK)).get();
        BusinessStabilityLabel businessStabilityLabel =
                (BusinessStabilityLabel) Optional.ofNullable(map.get(Classification.BUSINESS_STABILITY)).get();
        CreditRiskLabel creditRiskLabel =
                (CreditRiskLabel) Optional.ofNullable(map.get(Classification.CREDIT_RISK)).get();
        JudicialRiskLabel judicialRiskLabel =
                (JudicialRiskLabel) Optional.ofNullable(map.get(Classification.JUDICIAL_RISK)).get();

        try {
            if (businessBackgroundLabelMapper.saveBusinessBackgroundLabel(businessBackgroundLabel) == 1) {
                if (businessManagementAbilityLabelMapper.saveBusinessManagementAbilityLabel(businessManagementAbilityLabel) == 1) {
                    if (businessManagementRiskLabelMapper.saveBusinessManagementRiskLabel(businessManagementRiskLabel) == 1) {
                        if (businessStabilityLabelMapper.saveBusinessStabilityLabel(businessStabilityLabel) == 1) {
                            if (creditRiskLabelMapper.saveCreditRiskLabel(creditRiskLabel) == 1) {
                                if (judicialRiskLabelMapper.saveJudicialRiskLabel(judicialRiskLabel) == 1) {
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

        return false;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveLabelValueSpecial(NewOriginalAnalysisLabel newOriginalAnalysisLabel) throws Exception {
        log.info("saveLabelValueSpecial" + "----------" + newOriginalAnalysisLabel.toString() + "\n");

        BusinessBackgroundLabel businessBackgroundLabel = newOriginalAnalysisLabel.getBusinessBackgroundLabel();
        BusinessManagementAbilityLabel businessManagementAbilityLabel = newOriginalAnalysisLabel.getBusinessManagementAbilityLabel();
        BusinessManagementRiskLabel businessManagementRiskLabel = newOriginalAnalysisLabel.getBusinessManagementRiskLabel();
        BusinessStabilityLabel businessStabilityLabel = newOriginalAnalysisLabel.getBusinessStabilityLabel();
        CreditRiskLabel creditRiskLabel = newOriginalAnalysisLabel.getCreditRiskLabel();
        JudicialRiskLabel judicialRiskLabel = newOriginalAnalysisLabel.getJudicialRiskLabel();

        Map<Classification, Object> map = new HashMap<>(6);

        try {
            map.put(Classification.BUSINESS_BACKGROUND, businessBackgroundLabel);

            map.put(Classification.BUSINESS_MANAGEMENT_ABILITY, businessManagementAbilityLabel);

            map.put(Classification.BUSINESS_MANAGEMENT_RISK, businessManagementRiskLabel);

            map.put(Classification.BUSINESS_STABILITY, businessStabilityLabel);

            map.put(Classification.CREDIT_RISK, creditRiskLabel);

            map.put(Classification.JUDICIAL_RISK, judicialRiskLabel);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

        return saveLabelValue(map);
    }

}
