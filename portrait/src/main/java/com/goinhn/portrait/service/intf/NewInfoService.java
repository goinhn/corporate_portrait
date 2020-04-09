package com.goinhn.portrait.service.intf;

import com.goinhn.portrait.constant.enums.NewInfoKind;
import com.goinhn.portrait.model.entity.ShowInfo;
import com.goinhn.portrait.model.vo.NewInfo;

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
     * @param newInfo     单个基本信息
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

    /**
     * 搜索企业名称是否唯一
     *
     * @param entName 企业名称
     * @return
     */
    boolean isEntName(String entName);

    /**
     * 保存要展示的信息
     *
     * @param showInfo 展示信息
     * @return
     */
    boolean saveShowInfo(ShowInfo showInfo);


    /**
     * 存储所有的信息
     *
     * @param newInfo 前端输入的所有信息模型
     * @return
     */
    boolean saveAllInfoSpecial(NewInfo newInfo);

}
