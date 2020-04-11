package com.goinhn.portrait.service.impl;

import com.goinhn.portrait.mapper.ShowInfoMapper;
import com.goinhn.portrait.model.dto.Page;
import com.goinhn.portrait.model.dto.ShowTableInfo;
import com.goinhn.portrait.model.entity.ShowInfo;
import com.goinhn.portrait.service.intf.ShowInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author goinhn
 */
@Slf4j
@Service
public class ShowInfoServiceImpl implements ShowInfoService {

    private static final int PAGE_ALL_SIZE = 210;

    @Autowired
    private ShowInfoMapper showInfoMapper;

    @Override
    public List<ShowTableInfo> getTableInfo(@NotNull String entName, @NotNull int size) {
        int per = PAGE_ALL_SIZE / size;
        List<ShowTableInfo> showTableInfos = new ArrayList<>();
        if (entName.length() < 4) {
            ShowInfo showInfo = ShowInfo
                    .builder()
                    .entName(entName)
                    .build();
            try {
                showInfo = showInfoMapper.selectAllByEntName(showInfo);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (showInfo != null) {
                showTableInfos.add(ShowTableInfo
                        .builder()
                        .entName(showInfo.getEntName())
                        .entCat(Optional.ofNullable(showInfo.getEntCat()).orElse("未知"))
                        .build());
            }
        }

        Page page = Page
                .builder()
                .entName(entName)
                .pageIndex(0)
                .pageSize(per)
                .build();
        List<ShowInfo> showInfos = new ArrayList<>();
        try {
            showInfos = showInfoMapper.selectAllLikeEntName(page);
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (ShowInfo each : showInfos) {
            ShowTableInfo showTableInfo = ShowTableInfo
                    .builder()
                    .entName(each.getEntName())
                    .entCat(Optional.ofNullable(each.getEntCat()).orElse("未知"))
                    .build();
            showTableInfos.add(showTableInfo);
        }

        return showTableInfos;
    }

    @Override
    public ShowInfo getShowInfo(@NotNull String entName) {
        ShowInfo showInfo = ShowInfo
                .builder()
                .entName(entName)
                .build();
        try {
            showInfo = showInfoMapper.selectAllByEntName(showInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return showInfo;
    }

    @Override
    public boolean deleteShowInfo(String entName) {
        log.info("deleteShowInfo" + "----------" + entName + "\n");

        int number = 0;
        try {
            ShowInfo showInfo = ShowInfo
                    .builder()
                    .entName(entName)
                    .build();
            number = showInfoMapper.deleteShowInfoByEntName(showInfo);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        if (number == 1) {
            return true;
        } else {
            return false;
        }
    }

}
