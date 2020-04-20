package com.goinhn.portrait.service.impl;

import com.goinhn.portrait.constant.enums.Classification;
import com.goinhn.portrait.mapper.original.*;
import com.goinhn.portrait.model.entity.original.*;
import com.goinhn.portrait.model.vo.NewOriginalAnalysisLabel;
import com.goinhn.portrait.service.intf.OriginalValueService;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@Service
public class OriginalValueServiceImpl implements OriginalValueService {

    @Autowired
    private BusinessBackgroundOriginalMapper businessBackgroundOriginalMapper;

    @Autowired
    private BusinessManagementAbilityOriginalMapper businessManagementAbilityOriginalMapper;

    @Autowired
    private BusinessManagementRiskOriginalMapper businessManagementRiskOriginalMapper;

    @Autowired
    private BusinessStabilityOriginalMapper businessStabilityOriginalMapper;

    @Autowired
    private CreditRiskOriginalMapper creditRiskOriginalMapper;

    @Autowired
    private JudicialRiskOriginalMapper judicialRiskOriginalMapper;


    @Override
    public Object getRiskValue(@NotNull(message = "分类不能为空") Classification classification,
                               @NotNull(message = "企业名称不能为空") String entName) {
        try {
            switch (classification) {
                case BUSINESS_BACKGROUND:
                    BusinessBackgroundOriginal businessBackgroundOriginal = new BusinessBackgroundOriginal();
                    businessBackgroundOriginal.setEntName(entName);
                    return businessBackgroundOriginalMapper.selectAllByEntName(businessBackgroundOriginal);

                case BUSINESS_MANAGEMENT_ABILITY:
                    BusinessManagementAbilityOriginal businessManagementAbilityOriginal = new BusinessManagementAbilityOriginal();
                    businessManagementAbilityOriginal.setEntName(entName);
                    return businessManagementAbilityOriginalMapper.selectAllByEntName(businessManagementAbilityOriginal);

                case BUSINESS_MANAGEMENT_RISK:
                    BusinessManagementRiskOriginal businessManagementRiskOriginal = new BusinessManagementRiskOriginal();
                    businessManagementRiskOriginal.setEntName(entName);
                    return businessManagementRiskOriginalMapper.selectAllByEntName(businessManagementRiskOriginal);

                case BUSINESS_STABILITY:
                    BusinessStabilityOriginal businessStabilityOriginal = new BusinessStabilityOriginal();
                    businessStabilityOriginal.setEntName(entName);
                    return businessStabilityOriginalMapper.selectAllByEntName(businessStabilityOriginal);

                case CREDIT_RISK:
                    CreditRiskOriginal creditRiskOriginal = new CreditRiskOriginal();
                    creditRiskOriginal.setEntName(entName);
                    return creditRiskOriginalMapper.selectAllByEntName(creditRiskOriginal);

                case JUDICIAL_RISK:
                    JudicialRiskOriginal judicialRiskOriginal = new JudicialRiskOriginal();
                    judicialRiskOriginal.setEntName(entName);
                    return judicialRiskOriginalMapper.selectAllByEntName(judicialRiskOriginal);

                default:
                    return null;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveRiskValueSingle(@NotNull(message = "分类不能为空") Classification classification,
                                       @NotNull(message = "分析类不能为空") Object original) throws Exception {
        try {
            switch (classification) {
                case BUSINESS_BACKGROUND:
                    BusinessBackgroundOriginal businessBackgroundOriginal = (BusinessBackgroundOriginal) original;
                    return businessBackgroundOriginalMapper.saveBusinessBackgroundOriginal(businessBackgroundOriginal) == 1;

                case BUSINESS_MANAGEMENT_ABILITY:
                    BusinessManagementAbilityOriginal businessManagementAbilityOriginal = (BusinessManagementAbilityOriginal) original;
                    return businessManagementAbilityOriginalMapper.saveBusinessManagementAbilityOriginal(businessManagementAbilityOriginal) == 1;

                case BUSINESS_MANAGEMENT_RISK:
                    BusinessManagementRiskOriginal businessManagementRiskOriginal = (BusinessManagementRiskOriginal) original;
                    return businessManagementRiskOriginalMapper.saveBusinessManagementRiskOriginal(businessManagementRiskOriginal) == 1;

                case BUSINESS_STABILITY:
                    BusinessStabilityOriginal businessStabilityOriginal = (BusinessStabilityOriginal) original;
                    return businessStabilityOriginalMapper.saveBusinessStabilityOriginal(businessStabilityOriginal) == 1;

                case CREDIT_RISK:
                    CreditRiskOriginal creditRiskOriginal = (CreditRiskOriginal) original;
                    return creditRiskOriginalMapper.saveCreditRiskOriginal(creditRiskOriginal) == 1;

                case JUDICIAL_RISK:
                    JudicialRiskOriginal judicialRiskOriginal = (JudicialRiskOriginal) original;
                    return judicialRiskOriginalMapper.saveJudicialRiskOriginal(judicialRiskOriginal) == 1;

                default:
                    return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveRiskValueAll(@NotNull(message = "映射分类不能为空") Map<Classification, Object> map) throws Exception {
        Iterator<Entry<Classification, Object>> iterator = map.entrySet().iterator();
        try {
            while (iterator.hasNext()) {
                Entry<Classification, Object> entry = iterator.next();
                if (!saveRiskValueSingle(entry.getKey(), Optional.ofNullable(entry.getValue()).get())) {
                    return false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

        return true;
    }


    @Override
    @Transactional(rollbackFor = Error.class)
    public boolean saveRiskValueSpecial(@NotNull NewOriginalAnalysisLabel newOriginalOriginalLabel) throws Exception {
        log.info("saveRiskValueSpecial" + "----------" + newOriginalOriginalLabel.toString() + "\n");

        BusinessBackgroundOriginal businessBackgroundOriginal = newOriginalOriginalLabel.getBusinessBackgroundOriginal();
        BusinessManagementAbilityOriginal businessManagementAbilityOriginal = newOriginalOriginalLabel.getBusinessManagementAbilityOriginal();
        BusinessManagementRiskOriginal businessManagementRiskOriginal = newOriginalOriginalLabel.getBusinessManagementRiskOriginal();
        BusinessStabilityOriginal businessStabilityOriginal = newOriginalOriginalLabel.getBusinessStabilityOriginal();
        CreditRiskOriginal creditRiskOriginal = newOriginalOriginalLabel.getCreditRiskOriginal();
        JudicialRiskOriginal judicialRiskOriginal = newOriginalOriginalLabel.getJudicialRiskOriginal();

        try {
            if (!saveRiskValueSingle(Classification.BUSINESS_BACKGROUND, businessBackgroundOriginal)) {
                return false;
            }

            if (!saveRiskValueSingle(Classification.BUSINESS_MANAGEMENT_ABILITY, businessManagementAbilityOriginal)) {
                return false;
            }

            if (!saveRiskValueSingle(Classification.BUSINESS_MANAGEMENT_RISK, businessManagementRiskOriginal)) {
                return false;
            }

            if (!saveRiskValueSingle(Classification.BUSINESS_STABILITY, businessStabilityOriginal)) {
                return false;
            }

            if (!saveRiskValueSingle(Classification.CREDIT_RISK, creditRiskOriginal)) {
                return false;
            }

            if (!saveRiskValueSingle(Classification.JUDICIAL_RISK, judicialRiskOriginal)) {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

        return true;
    }

}
