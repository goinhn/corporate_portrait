package com.goinhn.kon.mapper.label;

import com.goinhn.kon.model.entity.label.BusinessManagementRiskLabel;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;

/**
 * @author goinhn
 */
@Repository
public interface BusinessManagementRiskLabelMapper {

    /**
     * 根据公司名返回企业风险序号数据
     *
     * @param businessManagementRiskLabel 企业风险标签
     * @return
     */
    @Select("select * from tab_business_management_risk_label where entname = #{entName}")
    @Results(
            id = "businessManagementRiskLabelMap",
            value = {
                    @Result(id = true, column = "eid", property = "eid"),
                    @Result(column = "entname", property = "entName"),
                    @Result(column = "label", property = "label")
            }
    )
    BusinessManagementRiskLabel selectAllByEntName(@NotNull BusinessManagementRiskLabel businessManagementRiskLabel);


    /**
     * 保存企业风险标签
     *
     * @param businessManagementRiskLabel 企业风险标签
     * @return
     */
    @Insert("insert into " +
            "tab_business_management_risk_label(eid, entname, label) " +
            "values(#{eid}, #{entName}, #{label})")
    int saveBusinessManagementRiskLabel(@NotNull BusinessManagementRiskLabel businessManagementRiskLabel);


    /**
     * 根据id进行删除
     *
     * @param eid
     * @return
     */
    @Delete("delete from tab_business_management_risk_label where eid = #{eid}")
    int deleteById(@NotNull Long eid);

}
