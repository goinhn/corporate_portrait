package com.goinhn.kon.service.intf;



import com.goinhn.kon.model.dto.ShowTableInfo;
import com.goinhn.kon.model.entity.ShowInfo;

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
     *
     * @param entName 企业名称
     * @return
     */
    ShowInfo getShowInfo(String entName);


    /**
     * 删除存储的展示信息
     *
     * @param entName 企业名称
     * @return
     */
    boolean deleteShowInfo(String entName);

}
