package com.goinhn.portrait.mapper;

import com.goinhn.portrait.model.entity.BusinessManagementAbilityLabel;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @author goinhn
 */
@Repository
public interface BusinessManagementAbilityLabelMapper {

    /**
     * 根据公司名返回企业经营能力标签序号数据
     *
     * @param businessManagementAbilityLabel 企业经营能力
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
    BusinessManagementAbilityLabel selectByEntName(BusinessManagementAbilityLabel businessManagementAbilityLabel);
}
