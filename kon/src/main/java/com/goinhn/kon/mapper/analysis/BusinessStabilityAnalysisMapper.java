package com.goinhn.kon.mapper.analysis;

import com.goinhn.kon.model.entity.analysis.BusinessStabilityAnalysis;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;

/**
 * @author goinhn
 */
@Repository
public interface BusinessStabilityAnalysisMapper {

    /**
     * 根据公司名称返回分析数据
     *
     * @param businessStabilityAnalysis 企业稳定性分析
     * @return
     */
    @Select("select * from tab_business_stability_analysis where entname = #{entName}")
    @Results(
            id = "businessStabilityValueMap",
            value = {
                    @Result(id = true, column = "eid", property = "eid"),
                    @Result(column = "entname", property = "entName"),
                    @Result(column = "alttime", property = "altTime")
            })
    BusinessStabilityAnalysis selectAllByEntName(@NotNull BusinessStabilityAnalysis businessStabilityAnalysis);


    /**
     * 保存企业稳定性分析数据
     *
     * @param businessStabilityAnalysis 企业稳定性分析
     * @return
     */
    @Insert("insert into " +
            "tab_business_stability_analysis(eid, entname, alttime) " +
            "values(#{eid}, #{entName}, #{altTime})")
    int saveBusinessStabilityAnalysis(@NotNull BusinessStabilityAnalysis businessStabilityAnalysis);


    /**
     * 根据id进行删除
     *
     * @param eid
     * @return
     */
    @Delete("delete from tab_business_stability_analysis where eid = #{eid}")
    int deleteById(@NotNull Long eid);

}
