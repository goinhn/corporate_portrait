package com.goinhn.kon.service.impl;


import com.goinhn.kon.constant.enums.NewInfoKind;
import com.goinhn.kon.mapper.ShowInfoMapper;
import com.goinhn.kon.mapper.newinfo.*;
import com.goinhn.kon.model.entity.ShowInfo;
import com.goinhn.kon.model.entity.newinfo.*;
import com.goinhn.kon.model.vo.NewInfo;
import com.goinhn.kon.service.intf.NewInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.*;
import java.util.Optional;

/**
 * @author goinhn
 */
@Slf4j
@Service
public class NewInfoServiceImpl implements NewInfoService {

    @Autowired
    private ChangeInfoNewInfoMapper changeInfoNewInfoMapper;

    @Autowired
    private CompanyBaseinfoNewInfoMapper companyBaseinfoNewInfoMapper;

    @Autowired
    private EntContributionNewInfoMapper entContributionNewInfoMapper;

    @Autowired
    private EntContributionYearNewInfoMapper entContributionYearNewInfoMapper;

    @Autowired
    private EnterpriseInsuranceNewInfoMapper enterpriseInsuranceNewInfoMapper;

    @Autowired
    private EntGuaranteeNewInfoMapper entGuaranteeNewInfoMapper;

    @Autowired
    private EntSocialSecurityNewInfoMapper entSocialSecurityNewInfoMapper;

    @Autowired
    private JnCreditInfoNewInfoMapper jnCreditInfoNewInfoMapper;

    @Autowired
    private JusticeDeclareNewInfoMapper justiceDeclareNewInfoMapper;

    @Autowired
    private JusticeEnforcedNewInfoMapper justiceEnforcedNewInfoMapper;

    @Autowired
    private JusticeJudgeNewNewInfoMapper justiceJudgeNewNewInfoMapper;

    @Autowired
    private OneDataNewInfoMapper oneDataNewInfoMapper;

    @Autowired
    private ShowInfoMapper showInfoMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveNewInfoSignal(@NotNull NewInfoKind newInfoKind,
                                     @NotNull Object newInfo) throws Exception{
        try {
            switch (newInfoKind) {
                case CHANGE_INFO:
                    ChangeInfoNewInfo changeInfoNewInfo = (ChangeInfoNewInfo) newInfo;
                    return changeInfoNewInfoMapper.saveChangeInfoNewInfo(changeInfoNewInfo) == 1;

                case COMPANY_BASE_INFO:
                    CompanyBaseinfoNewInfo companyBaseinfoNewInfo = (CompanyBaseinfoNewInfo) newInfo;
                    return companyBaseinfoNewInfoMapper.saveCompanyBaseinfoNewInfo(companyBaseinfoNewInfo) == 1;

                case ENT_CONTRIBUTION:
                    EntContributionNewInfo entContributionNewInfo = (EntContributionNewInfo) newInfo;
                    return entContributionNewInfoMapper.saveEntContributionNewInfo(entContributionNewInfo) == 1;

                case ENT_CONTRIBUTION_YEAR:
                    EntContributionYearNewInfo entContributionYearNewInfo = (EntContributionYearNewInfo) newInfo;
                    return entContributionYearNewInfoMapper.saveEntContributionYearNewInfo(entContributionYearNewInfo) == 1;

                case ENTERPRISE_INSURANCE:
                    EnterpriseInsuranceNewInfo enterpriseInsuranceNewInfo = (EnterpriseInsuranceNewInfo) newInfo;
                    return enterpriseInsuranceNewInfoMapper.saveEnterpriseInsuranceNewInfo(enterpriseInsuranceNewInfo) == 1;

                case ENT_GUARANTEE:
                    EntGuaranteeNewInfo entGuaranteeNewInfo = (EntGuaranteeNewInfo) newInfo;
                    return entGuaranteeNewInfoMapper.saveEntGuaranteeNewInfo(entGuaranteeNewInfo) == 1;

                case ENT_SOCIAL_SECURITY:
                    EntSocialSecurityNewInfo entSocialSecurityNewInfo = (EntSocialSecurityNewInfo) newInfo;
                    return entSocialSecurityNewInfoMapper.saveEntSocialSecurityNewInfo(entSocialSecurityNewInfo) == 1;

                case JN_CREDIT_INFO:
                    JnCreditInfoNewInfo jnCreditInfoNewInfo = (JnCreditInfoNewInfo) newInfo;
                    return jnCreditInfoNewInfoMapper.saveJnCreditInfoNewInfo(jnCreditInfoNewInfo) == 1;

                case JUSTICE_DECLARE:
                    JusticeDeclareNewInfo justiceDeclareNewInfo = (JusticeDeclareNewInfo) newInfo;
                    return justiceDeclareNewInfoMapper.saveJusticeDeclareNewInfo(justiceDeclareNewInfo) == 1;

                case JUSTICE_ENFORCED:
                    JusticeEnforcedNewInfo justiceEnforcedNewInfo = (JusticeEnforcedNewInfo) newInfo;
                    return justiceEnforcedNewInfoMapper.saveJusticeEnforcedNewInfo(justiceEnforcedNewInfo) == 1;

                case JUSTICE_JUDGE:
                    JusticeJudgeNewNewInfo justiceJudgeNewNewInfo = (JusticeJudgeNewNewInfo) newInfo;
                    return justiceJudgeNewNewInfoMapper.saveJusticeJudgeNewNewInfo(justiceJudgeNewNewInfo) == 1;

                case ONE_DATA:
                    OneDataNewInfo oneDataNewInfo = (OneDataNewInfo) newInfo;
                    return oneDataNewInfoMapper.saveOneDataNewInfo(oneDataNewInfo) == 1;

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
    public boolean saveNewInfoAll(@NotNull Map<NewInfoKind, Object> map) throws Exception {
        Iterator<Entry<NewInfoKind, Object>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Entry<NewInfoKind, Object> entry = iterator.next();
            try{
                if (!saveNewInfoSignal(entry.getKey(), Optional.ofNullable(entry.getValue()).get())) {
                    return false;
                }
            } catch (Exception e){
                e.printStackTrace();
                throw e;
            }
        }

        return true;
    }

    @Override
    public boolean isEntName(@NotNull String entName) {
        ShowInfo showInfo = ShowInfo
                .builder()
                .entName(entName)
                .build();

        try{
            showInfo = showInfoMapper.selectAllByEntName(showInfo);
        } catch(Exception e){
            e.printStackTrace();
        }

        if (showInfo != null) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveShowInfo(ShowInfo showInfo) throws Exception{
        int number = 0;
        try{
            number = showInfoMapper.saveShowInfo(showInfo);
        } catch(Exception e){
            e.printStackTrace();
            throw e;
        }
        if (number == 1) {
            return true;
        }

        return false;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveAllInfoSpecial(@NotNull NewInfo newInfo) throws Exception {
        log.info("saveAllInfoSpecial" + "----------" + newInfo.toString() + "\n");

        CompanyBaseinfoNewInfo companyBaseinfoNewInfo = newInfo.getCompanyBaseinfoNewInfo();
        List<ChangeInfoNewInfo> changeInfoNewInfos = newInfo.getChangeInfoNewInfos();
        List<EntContributionNewInfo> entContributionNewInfos = newInfo.getEntContributionNewInfos();
        List<EntContributionYearNewInfo> entContributionYearNewInfos = newInfo.getEntContributionYearNewInfos();
        List<EnterpriseInsuranceNewInfo> enterpriseInsuranceNewInfos = newInfo.getEnterpriseInsuranceNewInfos();
        List<EntGuaranteeNewInfo> guaranteeNewInfos = newInfo.getEntGuaranteeNewInfos();
        List<EntSocialSecurityNewInfo> entSocialSecurityNewInfos = newInfo.getEntSocialSecurityNewInfos();
        JnCreditInfoNewInfo jnCreditInfoNewInfo = newInfo.getJnCreditInfoNewInfo();
        List<JusticeDeclareNewInfo> justiceDeclareNewInfos = newInfo.getJusticeDeclareNewInfos();
        List<JusticeEnforcedNewInfo> justiceEnforcedNewInfos = newInfo.getJusticeEnforcedNewInfos();
        List<JusticeJudgeNewNewInfo> justiceJudgeNewNewInfos = newInfo.getJusticeJudgeNewNewInfos();
        OneDataNewInfo oneDataNewInfo = newInfo.getOneDataNewInfo();

        ShowInfo showInfo = ShowInfo
                .builder()
                .entName(companyBaseinfoNewInfo.getEntName())
                .entCat(companyBaseinfoNewInfo.getEntCat())
                .entStatus(companyBaseinfoNewInfo.getEntStatus())
                .entType(companyBaseinfoNewInfo.getEntType())
                .industryPhy(companyBaseinfoNewInfo.getIndustryphy())
                .creditGrade(jnCreditInfoNewInfo.getCreditGrade())
                .bidNum(oneDataNewInfo.getBidNum())
                .branchNum(oneDataNewInfo.getBranchNum())
                .investNum(oneDataNewInfo.getInvestNum())
                .shopNum(oneDataNewInfo.getShopNum())
                .build();

        if (showInfo.getEntName() == null || "".equals(showInfo.getEntName())) {
            return false;
        }

        try{
            if (!saveNewInfoSignal(NewInfoKind.COMPANY_BASE_INFO, companyBaseinfoNewInfo)) {
                return false;
            }
            if (!saveNewInfoSignal(NewInfoKind.JN_CREDIT_INFO, jnCreditInfoNewInfo)) {
                return false;
            }
            if (!saveNewInfoSignal(NewInfoKind.ONE_DATA, oneDataNewInfo)) {
                return false;
            }

            for (ChangeInfoNewInfo changeInfoNewInfo : changeInfoNewInfos) {
                if (!saveNewInfoSignal(NewInfoKind.CHANGE_INFO, changeInfoNewInfo)) {
                    return false;
                }
            }

            for (EntContributionNewInfo entContributionNewInfo : entContributionNewInfos) {
                if (!saveNewInfoSignal(NewInfoKind.ENT_CONTRIBUTION, entContributionNewInfo)) {
                    return false;
                }
            }

            for (EntContributionYearNewInfo entContributionYearNewInfo : entContributionYearNewInfos) {
                if (!saveNewInfoSignal(NewInfoKind.ENT_CONTRIBUTION_YEAR, entContributionYearNewInfo)) {
                    return false;
                }
            }

            for (EnterpriseInsuranceNewInfo enterpriseInsuranceNewInfo : enterpriseInsuranceNewInfos) {
                if (!saveNewInfoSignal(NewInfoKind.ENTERPRISE_INSURANCE, enterpriseInsuranceNewInfo)) {
                    return false;
                }
            }

            for (EntGuaranteeNewInfo guaranteeNewInfo : guaranteeNewInfos) {
                if (!saveNewInfoSignal(NewInfoKind.ENT_GUARANTEE, guaranteeNewInfo)) {
                    return false;
                }
            }

            for (EntSocialSecurityNewInfo entSocialSecurityNewInfo : entSocialSecurityNewInfos) {
                if (!saveNewInfoSignal(NewInfoKind.ENT_SOCIAL_SECURITY, entSocialSecurityNewInfo)) {
                    return false;
                }
            }

            for (JusticeDeclareNewInfo justiceDeclareNewInfo : justiceDeclareNewInfos) {
                if (!saveNewInfoSignal(NewInfoKind.JUSTICE_DECLARE, justiceDeclareNewInfo)) {
                    return false;
                }
            }

            for (JusticeEnforcedNewInfo justiceEnforcedNewInfo : justiceEnforcedNewInfos) {
                if (!saveNewInfoSignal(NewInfoKind.JUSTICE_ENFORCED, justiceEnforcedNewInfo)) {
                    return false;
                }
            }

            for (JusticeJudgeNewNewInfo justiceJudgeNewNewInfo : justiceJudgeNewNewInfos) {
                if (!saveNewInfoSignal(NewInfoKind.JUSTICE_JUDGE, justiceJudgeNewNewInfo)) {
                    return false;
                }
            }
            if (!saveShowInfo(showInfo)) {
                return false;
            }
        } catch (Exception e){
            e.printStackTrace();
            throw e;
        }

        return true;
    }


}
