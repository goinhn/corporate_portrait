package com.goinhn.portrait.dao;

import com.goinhn.portrait.model.BusinessManagementAbilityAnalysis;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @author goinhn
 */
@Repository
public interface BusinessManagementAbilityAnalysisMapper {

    /**
     * 根据公司名称返回分析数据
     *
     * @param businessManagementAbilityAnalysis 公司名称
     * @return
     */
    @Select("select * from tab_business_management_ability_analysis where entname = #{entName}")
    @Results(
            id = "businessManagementAbilityValueMap",
            value = {
                    @Result(id = true, column = "eid", property = "eid"),
                    @Result(column = "entname", property = "entName"),
                    @Result(column = "evaluation", property = "evaluation"),
                    @Result(column = "investnum", property = "investNum"),
                    @Result(column = "bidnum", property = "bidNum"),
                    @Result(column = "cbzt", property = "cbzt"),
                    @Result(column = "ibrand_num", property = "iBrandNum"),
                    @Result(column = "icopy_num", property = "iCopyNum"),
                    @Result(column = "ipat_num", property = "iPatNum"),
                    @Result(column = "idom_num", property = "iDomNum"),
                    @Result(column = "passpercent", property = "passPercent")
            }
    )
    BusinessManagementAbilityAnalysis selectAllByEntName(BusinessManagementAbilityAnalysis businessManagementAbilityAnalysis);
}
