package com.goinhn.portrait.service.impl;

import com.goinhn.portrait.constant.enums.Classification;
import com.goinhn.portrait.mapper.LabelKindMapper;
import com.goinhn.portrait.mapper.label.*;
import com.goinhn.portrait.model.entity.LabelKind;
import com.goinhn.portrait.model.entity.label.*;
import com.goinhn.portrait.model.dto.ShowLabel;
import com.goinhn.portrait.service.intf.LabelService;
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
            labelKind = labelKindMapper.selectAllByKindAndNumber(labelKind);
            return Optional.ofNullable(labelKind)
                    .map(i -> i.getLabel())
                    .orElse("");
        }
    }


    @Override
    public Map<Classification, Integer> getLabelNumber(@NotNull String entName) {
        BusinessBackgroundLabel businessBackgroundLabel = BusinessBackgroundLabel
                .builder()
                .entName(entName)
                .build();
        businessBackgroundLabel = businessBackgroundLabelMapper.selectAllByEntName(businessBackgroundLabel);

        BusinessManagementAbilityLabel businessManagementAbilityLabel = BusinessManagementAbilityLabel
                .builder()
                .entName(entName)
                .build();
        businessManagementAbilityLabel = businessManagementAbilityLabelMapper.selectAllByEntName(businessManagementAbilityLabel);

        BusinessManagementRiskLabel businessManagementRiskLabel = BusinessManagementRiskLabel
                .builder()
                .entName(entName)
                .build();
        businessManagementRiskLabel = businessManagementRiskLabelMapper.selectAllByEntName(businessManagementRiskLabel);

        BusinessStabilityLabel businessStabilityLabel = BusinessStabilityLabel
                .builder()
                .entName(entName)
                .build();
        businessStabilityLabel = businessStabilityLabelMapper.selectAllByEntName(businessStabilityLabel);

        CreditRiskLabel creditRiskLabel = CreditRiskLabel
                .builder()
                .entName(entName)
                .build();
        creditRiskLabel = creditRiskLabelMapper.selectAllByEntName(creditRiskLabel);

        JudicialRiskLabel judicialRiskLabel = JudicialRiskLabel
                .builder()
                .entName(entName)
                .build();
        judicialRiskLabel = judicialRiskLabelMapper.selectAllByEntName(judicialRiskLabel);

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
            showLabel.setJudicialRiskLabel(searchCorrect(Classification.CREDIT_RISK, "企业司法风险", mapNumber));
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
    public boolean saveLabelValue(@NotNull String entName,
                                  @NotNull Map<Classification, Object> map) {
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

        return false;
    }

}
