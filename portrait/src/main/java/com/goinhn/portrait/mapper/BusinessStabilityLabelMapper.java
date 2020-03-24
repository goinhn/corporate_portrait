package com.goinhn.portrait.mapper;

import com.goinhn.portrait.model.entity.BusinessStabilityLabel;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @author goinhn
 */
@Repository
public interface BusinessStabilityLabelMapper {

    /**
     * 根据公司名返回企业稳定性标签序号数据
     *
     * @param businessStabilityLabel 企业稳定性
     * @return
     */
    @Select("select * from tab_business_stability_label where entname = #{entName}")
    @Results(
            id = "businessStabilityLabelMap",
            value = {
                    @Result(id = true, column = "eid", property = "eid"),
                    @Result(column = "entname", property = "entName"),
                    @Result(column = "label", property = "label")
            }
    )
    BusinessStabilityLabel selectByEntName(BusinessStabilityLabel businessStabilityLabel);
}
