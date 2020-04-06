package com.goinhn.portrait.service.impl;

import com.goinhn.portrait.mapper.ShowInfoMapper;
import com.goinhn.portrait.model.dto.Page;
import com.goinhn.portrait.model.dto.ShowTableInfo;
import com.goinhn.portrait.model.entity.ShowInfo;
import com.goinhn.portrait.service.intf.ShowInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author goinhn
 */
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
            showInfo = showInfoMapper.selectAllByEntName(showInfo);
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
        List<ShowInfo> showInfos = showInfoMapper.selectAllLikeEntName(page);

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
        showInfo = showInfoMapper.selectAllByEntName(showInfo);

        return showInfo;
    }

}
