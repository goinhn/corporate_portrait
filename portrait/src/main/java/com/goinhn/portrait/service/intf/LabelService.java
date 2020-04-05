package com.goinhn.portrait.service.intf;

import com.goinhn.portrait.constant.enums.Classification;
import com.goinhn.portrait.model.dto.ShowLabel;

import java.util.Map;

/**
 * @author goinhn
 */
public interface LabelService {

    /**
     * 获取所有的标签数字
     *
     * @param entName 公司名称
     * @return
     */
    Map<Classification, Integer> getLabelNumber(String entName);

    /**
     * 获取所有的标签值
     *
     * @param entName 公司名称
     * @return
     */
    ShowLabel getLabelValue(String entName);

    /**
     * 保存标签值
     *
     * @param entName 企业名称
     * @param map     标签集合
     * @return
     */
    boolean saveLabelValue(String entName, Map<Classification, Object> map);

}
