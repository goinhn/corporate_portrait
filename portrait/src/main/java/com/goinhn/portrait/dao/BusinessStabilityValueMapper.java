package com.goinhn.portrait.dao;

import com.goinhn.portrait.model.BusinessStabilityValue;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @author goinhn
 */
@Repository
public interface BusinessStabilityValueMapper {

    /**
     * 根据公司名称返回分析数据
     *
     * @param businessStabilityValue 企业稳定性
     * @return
     */
    @Select("select * from tab_business_stability_value where entname = #{entName}")
    @Results(
            id = "businessStabilityValueMap",
            value = {
                    @Result(id = true, column = "eid", property = "eid"),
                    @Result(column = "entname", property = "entName"),
                    @Result(column = "alttime", property = "altTime")
            })
    BusinessStabilityValue selectAllByEntName(BusinessStabilityValue businessStabilityValue);
}
