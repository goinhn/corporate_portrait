package com.goinhn.kon.service.intf;



import com.goinhn.kon.constant.enums.Classification;
import com.goinhn.kon.model.dto.ShowLabel;
import com.goinhn.kon.model.vo.NewOriginalAnalysisLabel;

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
     * @param map     标签集合
     * @return
     */
    boolean saveLabelValue(Map<Classification, Object> map) throws Exception;

    /**
     * 保存多个标签值
     *
     * @param newOriginalAnalysisLabel 分析值和标签集合
     * @return
     */
    boolean saveLabelValueSpecial(NewOriginalAnalysisLabel newOriginalAnalysisLabel) throws Exception;

}
