package com.goinhn.portrait.service.intf;

import com.goinhn.portrait.constant.enums.Classification;

/**
 * @author goinhn
 */
public interface AnalysisService {

    /**
     * 返回对应字段的评估值
     *
     * @param classification 分类
     * @param entName        企业名称
     * @return
     */
    Object riskValue(Classification classification, String entName);
}
