package com.goinhn.portrait.service.intf;

import com.goinhn.portrait.model.dto.ShowTableInfo;
import com.goinhn.portrait.model.entity.ShowInfo;

import java.util.List;

/**
 * 展示信息服务
 *
 * @author goinhn
 */
public interface ShowInfoService {

    /**
     * 获取前端表格显示的信息
     *
     * @param entName 企业名称
     * @param size
     * @return
     */
    List<ShowTableInfo> getTableInfo(String entName, int size);


    /**
     * 获取所有展示的信息
     * @param entName 企业名称
     * @return
     */
    ShowInfo getShowInfo(String entName);

}
