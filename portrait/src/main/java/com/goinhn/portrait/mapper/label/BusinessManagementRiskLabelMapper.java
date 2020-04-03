package com.goinhn.portrait.mapper.label;

import com.goinhn.portrait.model.entity.label.BusinessManagementRiskLabel;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

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
    BusinessManagementRiskLabel selectAllByEntName(BusinessManagementRiskLabel businessManagementRiskLabel);


    /**
     * 保存企业风险标签
     *
     * @param businessManagementRiskLabel 企业风险标签
     * @return
     */
    @Insert("insert into " +
            "tab_business_management_risk_label(eid, entname, label) " +
            "vlaues(#{eid}, #{entName}, #{label})")
    int saveBusinessManagementRiskLabel(BusinessManagementRiskLabel businessManagementRiskLabel);

}
