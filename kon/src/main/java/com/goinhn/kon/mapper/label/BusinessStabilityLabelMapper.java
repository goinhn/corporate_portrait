package com.goinhn.kon.mapper.label;

import com.goinhn.kon.model.entity.label.BusinessStabilityLabel;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;

/**
 * @author goinhn
 */
@Repository
public interface BusinessStabilityLabelMapper {

    /**
     * 根据公司名返回企业稳定性标签序号数据
     *
     * @param businessStabilityLabel 企业稳定性标签
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
    BusinessStabilityLabel selectAllByEntName(@NotNull BusinessStabilityLabel businessStabilityLabel);


    /**
     * 保存企业稳定性标签
     *
     * @param businessStabilityLabel 企业稳定性标签
     * @return
     */
    @Insert("insert into " +
            "tab_business_stability_label(eid, entname, label) " +
            "values(#{eid}, #{entName}, #{label})")
    int saveBusinessStabilityLabel(@NotNull BusinessStabilityLabel businessStabilityLabel);


    /**
     * 根据id进行删除
     *
     * @param eid
     * @return
     */
    @Delete("delete from tab_business_stability_label where eid = #{eid}")
    int deleteById(@NotNull Long eid);

}
