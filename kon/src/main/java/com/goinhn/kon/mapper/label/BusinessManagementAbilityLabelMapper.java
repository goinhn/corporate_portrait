package com.goinhn.kon.mapper.label;

import com.goinhn.kon.model.entity.label.BusinessManagementAbilityLabel;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;

/**
 * @author goinhn
 */
@Repository
public interface BusinessManagementAbilityLabelMapper {

    /**
     * 根据公司名返回企业经营能力标签序号数据
     *
     * @param businessManagementAbilityLabel 企业经营能力标签
     * @return
     */
    @Select("select * from tab_business_management_ability_label where entname = #{entName}")
    @Results(
            id = "businessManagementAbilityLabelMap",
            value = {
                    @Result(id = true, column = "eid", property = "eid"),
                    @Result(column = "entname", property = "entName"),
                    @Result(column = "label", property = "label")
            }
    )
    BusinessManagementAbilityLabel selectAllByEntName(@NotNull BusinessManagementAbilityLabel businessManagementAbilityLabel);


    /**
     * 保存企业经营能力标签
     *
     * @param businessManagementAbilityLabel 企业经营能力标签
     * @return
     */
    @Insert("insert into " +
            "tab_business_management_ability_label(eid, entname, label) " +
            "values(#{eid}, #{entName}, #{label})")
    int saveBusinessManagementAbilityLabel(@NotNull BusinessManagementAbilityLabel businessManagementAbilityLabel);


    /**
     * 根据id进行删除
     *
     * @param eid
     * @return
     */
    @Delete("delete from tab_business_management_ability_label where eid = #{eid}")
    int deleteById(@NotNull Long eid);

}
