package com.goinhn.kon.mapper.original;

import com.goinhn.kon.model.entity.original.BusinessStabilityOriginal;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;

/**
 * @author goinhn
 */
@Repository
public interface BusinessStabilityOriginalMapper {

    /**
     * 根据公司名称返回分析数据
     *
     * @param businessStabilityOriginal 企业稳定性分析
     * @return
     */
    @Select("select * from tab_business_stability_original where entname = #{entName}")
    @Results(
            id = "businessStabilityValueMap",
            value = {
                    @Result(id = true, column = "eid", property = "eid"),
                    @Result(column = "entname", property = "entName"),
                    @Result(column = "alttime", property = "altTime")
            })
    BusinessStabilityOriginal selectAllByEntName(@NotNull BusinessStabilityOriginal businessStabilityOriginal);


    /**
     * 保存企业稳定性分析数据
     *
     * @param businessStabilityOriginal 企业稳定性分析
     * @return
     */
    @Insert("insert into " +
            "tab_business_stability_original(eid, entname, alttime) " +
            "values(#{eid}, #{entName}, #{altTime})")
    int saveBusinessStabilityOriginal(@NotNull BusinessStabilityOriginal businessStabilityOriginal);


    /**
     * 根据id进行删除
     *
     * @param eid
     * @return
     */
    @Delete("delete from tab_business_stability_original where eid = #{eid}")
    int deleteById(@NotNull Long eid);

}
