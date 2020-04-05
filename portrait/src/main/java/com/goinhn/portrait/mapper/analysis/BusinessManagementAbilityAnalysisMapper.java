package com.goinhn.portrait.mapper.analysis;

import com.goinhn.portrait.model.entity.analysis.BusinessManagementAbilityAnalysis;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;

/**
 * @author goinhn
 */
@Repository
public interface BusinessManagementAbilityAnalysisMapper {

    /**
     * 根据公司名称返回分析数据
     *
     * @param businessManagementAbilityAnalysis 公司经营能力分析
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
    BusinessManagementAbilityAnalysis selectAllByEntName(@NotNull BusinessManagementAbilityAnalysis businessManagementAbilityAnalysis);


    /**
     * 保存公司经营能力分析
     *
     * @param businessManagementAbilityAnalysis 公司经营能力分析
     * @return
     */
    @Insert("insert into " +
            "tab_business_management_ability_analysis(eid, entname, evaluation, investnum, bidnum, cbzt, ibrand_num, " +
            "icopy_num, ipat_num, idom_num, passpercent) " +
            "values(#{eid}, #{entName}, #{evaluation}， #{investNum}, #{bidNum}, #{cbzt}, #{iBrandNum}, #{iCopyNum}, " +
            "#{iPatNum}, #{iDomNum}, #{passPercent})")
    int saveBusinessManagementAbilityAnalysis(@NotNull BusinessManagementAbilityAnalysis businessManagementAbilityAnalysis);

}
