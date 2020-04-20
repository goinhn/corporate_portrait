package com.goinhn.portrait.mapper.original;

import com.goinhn.portrait.model.entity.original.BusinessManagementAbilityOriginal;
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
public interface BusinessManagementAbilityOriginalMapper {

    /**
     * 根据公司名称返回分析数据
     *
     * @param businessManagementAbilityOriginal 公司经营能力分析
     * @return
     */
    @Select("select * from tab_business_management_ability_original where entname = #{entName}")
    @Results(
            id = "businessManagementAbilityValueMap",
            value = {
                    @Result(id = true, column = "eid", property = "eid"),
                    @Result(column = "entname", property = "entName"),
                    @Result(column = "evaluation", property = "evaluation"),
                    @Result(column = "investnum", property = "investNum"),
                    @Result(column = "bidnum", property = "bidNum"),
                    @Result(column = "cbzt", property = "cbzt"),
                    @Result(column = "ibrand_num", property = "ibrandNum"),
                    @Result(column = "icopy_num", property = "icopyNum"),
                    @Result(column = "ipat_num", property = "ipatNum"),
                    @Result(column = "idom_num", property = "idomNum"),
                    @Result(column = "passpercent", property = "passPercent")
            }
    )
    BusinessManagementAbilityOriginal selectAllByEntName(@NotNull BusinessManagementAbilityOriginal businessManagementAbilityOriginal);


    /**
     * 保存公司经营能力分析
     *
     * @param businessManagementAbilityOriginal 公司经营能力分析
     * @return
     */
    @Insert("insert into " +
            "tab_business_management_ability_original(eid, entname, evaluation, investnum, bidnum, cbzt, ibrand_num, " +
            "icopy_num, ipat_num, idom_num, passpercent) " +
            "values(#{eid}, #{entName}, #{evaluation}, #{investNum}, #{bidNum}, #{cbzt}, #{ibrandNum}, #{icopyNum}, " +
            "#{ipatNum}, #{idomNum}, #{passPercent})")
    int saveBusinessManagementAbilityOriginal(@NotNull BusinessManagementAbilityOriginal businessManagementAbilityOriginal);

}
