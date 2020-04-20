package com.goinhn.portrait.service.intf;

import com.goinhn.portrait.constant.enums.Classification;
import com.goinhn.portrait.model.vo.NewOriginalAnalysisLabel;

import java.util.Map;

/**
 * @author goinhn
 */
public interface OriginalValueService {

    /**
     * 返回对应字段的评估值
     *
     * @param classification 分类
     * @param entName        企业名称
     * @return
     */
    Object getRiskValue(Classification classification, String entName);

    /**
     * 保存单个不用种类的评估值
     *
     * @param classification 分类
     * @param analysis       不同种类的模型
     * @return
     */
    boolean saveRiskValueSingle(Classification classification, Object analysis) throws Exception;

    /**
     * 保存所有的分析值数据
     *
     * @param map 所有种类集合
     * @return
     */
    boolean saveRiskValueAll(Map<Classification, Object> map) throws Exception;


    /**
     * 保存多个分析值数据
     *
     * @param newOriginalAnalysisLabel 分析数据集合
     * @return
     */
    boolean saveRiskValueSpecial(NewOriginalAnalysisLabel newOriginalAnalysisLabel) throws Exception;

}
