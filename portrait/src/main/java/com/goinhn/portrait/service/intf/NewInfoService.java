package com.goinhn.portrait.service.intf;

import com.goinhn.portrait.constant.enums.NewInfoKind;

import java.util.Map;

/**
 * 新输入信息服务
 *
 * @author goinhn
 */
public interface NewInfoService {

    /**
     * 保存单个基本信息
     *
     * @param newInfoKind 种类
     * @param newInfo 单个基本信息
     * @return
     */
    boolean saveNewInfoSignal(NewInfoKind newInfoKind, Object newInfo);
    /**
     * 保存基本信息
     *
     * @param map 所有信息集合
     * @return
     */
    boolean saveNewInfoAll(Map<NewInfoKind, Object> map);

}
