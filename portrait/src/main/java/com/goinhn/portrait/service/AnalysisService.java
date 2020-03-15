package com.goinhn.portrait.service;

import com.goinhn.portrait.util.Classification;

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
