package com.goinhn.kon.service.impl;


import com.goinhn.kon.constant.enums.Classification;
import com.goinhn.kon.mapper.analysis.*;
import com.goinhn.kon.model.entity.analysis.*;
import com.goinhn.kon.model.vo.NewOriginalAnalysisLabel;
import com.goinhn.kon.service.intf.AnalysisValueService;
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
                    return businessManagementRiskAnalysisMapper.selectAllByEntName(businessManagementRiskAnalysis);

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

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveRiskValueSingle(@NotNull(message = "分类不能为空") Classification classification,
                                       @NotNull(message = "分析类不能为空") Object analysis) throws Exception {
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
            throw e;
        }
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveRiskValueAll(@NotNull(message = "映射分类不能为空") Map<Classification, Object> map) throws Exception {
        Iterator<Entry<Classification, Object>> iterator = map.entrySet().iterator();
        try{
            while (iterator.hasNext()) {
                Entry<Classification, Object> entry = iterator.next();
                if (!saveRiskValueSingle(entry.getKey(), Optional.ofNullable(entry.getValue()).get())) {
                    return false;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }

        return true;
    }


    @Override
    @Transactional(rollbackFor = Error.class)
    public boolean saveRiskValueSpecial(@NotNull NewOriginalAnalysisLabel newOriginalAnalysisLabel) throws Exception {
        log.info("saveRiskValueSpecial" + "----------" + newOriginalAnalysisLabel.toString() + "\n");

        BusinessBackgroundAnalysis businessBackgroundAnalysis = newOriginalAnalysisLabel.getBusinessBackgroundAnalysis();
        BusinessManagementAbilityAnalysis businessManagementAbilityAnalysis = newOriginalAnalysisLabel.getBusinessManagementAbilityAnalysis();
        BusinessManagementRiskAnalysis businessManagementRiskAnalysis = newOriginalAnalysisLabel.getBusinessManagementRiskAnalysis();
        BusinessStabilityAnalysis businessStabilityAnalysis = newOriginalAnalysisLabel.getBusinessStabilityAnalysis();
        CreditRiskAnalysis creditRiskAnalysis = newOriginalAnalysisLabel.getCreditRiskAnalysis();
        JudicialRiskAnalysis judicialRiskAnalysis = newOriginalAnalysisLabel.getJudicialRiskAnalysis();

        try{
            if(!saveRiskValueSingle(Classification.BUSINESS_BACKGROUND, businessBackgroundAnalysis)){
                return false;
            }

            if(!saveRiskValueSingle(Classification.BUSINESS_MANAGEMENT_ABILITY, businessManagementAbilityAnalysis)){
                return false;
            }

            if(!saveRiskValueSingle(Classification.BUSINESS_MANAGEMENT_RISK, businessManagementRiskAnalysis)){
                return false;
            }

            if(!saveRiskValueSingle(Classification.BUSINESS_STABILITY, businessStabilityAnalysis)){
                return false;
            }

            if(!saveRiskValueSingle(Classification.CREDIT_RISK, creditRiskAnalysis)){
                return false;
            }

            if(!saveRiskValueSingle(Classification.JUDICIAL_RISK, judicialRiskAnalysis)){
                return false;
            }
        } catch(Exception e){
            e.printStackTrace();
            throw e;
        }

        return true;
    }

}
