package com.goinhn.portrait.service.impl;

import com.goinhn.portrait.constant.enums.Classification;
import com.goinhn.portrait.constant.enums.NewInfoKind;
import com.goinhn.portrait.mapper.ShowInfoMapper;
import com.goinhn.portrait.mapper.newinfo.*;
import com.goinhn.portrait.model.entity.ShowInfo;
import com.goinhn.portrait.model.entity.newinfo.*;
import com.goinhn.portrait.model.vo.NewInfo;
import com.goinhn.portrait.service.intf.NewInfoService;
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
    public boolean saveNewInfoSignal(@NotNull NewInfoKind newInfoKind,
                                     @NotNull Object newInfo) {
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
            return false;
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveNewInfoAll(@NotNull Map<NewInfoKind, Object> map) {
        Iterator<Entry<NewInfoKind, Object>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Entry<NewInfoKind, Object> entry = iterator.next();
            if (!saveNewInfoSignal(entry.getKey(), Optional.ofNullable(entry.getValue()).get())) {
                return false;
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

        showInfo = showInfoMapper.selectAllByEntName(showInfo);

        if (showInfo != null) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean saveShowInfo(ShowInfo showInfo) {
        int number = showInfoMapper.saveShowInfo(showInfo);
        if (number == 1) {
            return true;
        }

        return false;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveAllInfoSpecial(NewInfo newInfo) {
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

        if(!saveNewInfoSignal(NewInfoKind.COMPANY_BASE_INFO, companyBaseinfoNewInfo)){
            return false;
        }
        if(!saveNewInfoSignal(NewInfoKind.JN_CREDIT_INFO, jnCreditInfoNewInfo)){
            return false;
        }
        if(!saveNewInfoSignal(NewInfoKind.ONE_DATA, oneDataNewInfo)){
            return false;
        }

        for (ChangeInfoNewInfo changeInfoNewInfo : changeInfoNewInfos) {
            if(!saveNewInfoSignal(NewInfoKind.CHANGE_INFO, changeInfoNewInfo)){
                return false;
            }
        }

        for (EntContributionNewInfo entContributionNewInfo : entContributionNewInfos) {
            if(!saveNewInfoSignal(NewInfoKind.ENT_CONTRIBUTION, entContributionNewInfo)){
                return false;
            }
        }

        for (EntContributionYearNewInfo entContributionYearNewInfo : entContributionYearNewInfos) {
            if(!saveNewInfoSignal(NewInfoKind.ENT_CONTRIBUTION_YEAR, entContributionYearNewInfo)){
                return false;
            }
        }

        return true;
    }

}
