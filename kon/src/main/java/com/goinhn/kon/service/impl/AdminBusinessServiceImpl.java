package com.goinhn.kon.service.impl;

import com.goinhn.kon.mapper.ShowInfoMapper;
import com.goinhn.kon.mapper.analysis.*;
import com.goinhn.kon.mapper.label.*;
import com.goinhn.kon.mapper.original.*;
import com.goinhn.kon.model.dto.BusinessDTO;
import com.goinhn.kon.model.dto.Page;
import com.goinhn.kon.model.entity.ShowInfo;
import com.goinhn.kon.service.intf.AdminBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminBusinessServiceImpl implements AdminBusinessService {

    @Autowired
    private ShowInfoMapper showInfoMapper;

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
    public int countLikeBusiness(String search, int pageIndex, int pageSize) {
        Page page = new Page();
        page.setName(search);
        page.setPageIndex(pageIndex);
        page.setPageSize(pageSize);

        return showInfoMapper.selectLikeCount(page);
    }


    @Override
    public int countBusiness() {
        return showInfoMapper.selectCount();
    }


    @Override
    public List<BusinessDTO> filterBusiness(String search, int pageIndex, int pageSize) {
        Page page = new Page();
        page.setName(search);
        page.setPageIndex(pageIndex);
        page.setPageSize(pageSize);

        List<ShowInfo> showInfos = showInfoMapper.selectAllLikeEntName(page);

        List<BusinessDTO> businessDTOS = new ArrayList<>();

        for (int i = 0; i < showInfos.size(); i++) {
            ShowInfo showInfo = showInfos.get(i);
            BusinessDTO businessDTO = new BusinessDTO();

            businessDTO.setEid(showInfo.getEid());
            businessDTO.setEntName(showInfo.getEntName());
            businessDTO.setCreditGrade(showInfo.getCreditGrade());
            businessDTO.setEntCat(showInfo.getEntCat());
            businessDTO.setEntStatus(showInfo.getEntStatus());
            businessDTO.setEntType(showInfo.getEntType());
            businessDTO.setIndustryPhy(showInfo.getIndustryPhy());

            businessDTOS.add(businessDTO);
        }

        return businessDTOS;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteBusiness(Long id) {

        showInfoMapper.deleteShowInfoById(ShowInfo.builder().eid(id).build());

        businessBackgroundAnalysisMapper.deleteById(id);
        businessManagementAbilityAnalysisMapper.deleteById(id);
        businessManagementRiskAnalysisMapper.deleteById(id);
        businessStabilityAnalysisMapper.deleteById(id);
        creditRiskAnalysisMapper.deleteById(id);
        judicialRiskAnalysisMapper.deleteById(id);

        businessBackgroundLabelMapper.deleteById(id);
        businessManagementAbilityLabelMapper.deleteById(id);
        businessManagementRiskLabelMapper.deleteById(id);
        businessStabilityLabelMapper.deleteById(id);
        creditRiskLabelMapper.deleteById(id);
        judicialRiskLabelMapper.deleteById(id);

        businessBackgroundOriginalMapper.deleteById(id);
        businessManagementAbilityOriginalMapper.deleteById(id);
        businessManagementRiskOriginalMapper.deleteById(id);
        businessStabilityOriginalMapper.deleteById(id);
        creditRiskOriginalMapper.deleteById(id);
        judicialRiskOriginalMapper.deleteById(id);

        return true;
    }

}
