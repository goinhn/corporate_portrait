package com.goinhn.portrait.service.intf;

import com.goinhn.portrait.model.vo.ShowLabel;

/**
 * @author goinhn
 */
public interface LabelService {

    /**
     * 获取所有的标签值
     *
     * @param entName 公司名称
     * @return
     */
    ShowLabel getLabelValue(String entName);
}
