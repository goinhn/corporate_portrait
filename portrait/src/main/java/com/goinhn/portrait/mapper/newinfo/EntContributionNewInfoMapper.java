package com.goinhn.portrait.mapper.newinfo;

import com.goinhn.portrait.model.entity.newinfo.EntContributionNewInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author goinhn
 */
@Repository
public interface EntContributionNewInfoMapper {

    /**
     * 根据公司名称进行查询
     *
     * @param entContributionNewInfo 企业出资信息(股东（自然人）出资信息)
     * @return
     */
    @Select("select * from tab_ent_contribution_new_info where entname = #{entName}")
    @Results(
            id = "entContributionNewInfoMap",
            value = {
                    @Result(id = true, column = "eid", property = "eid"),
                    @Result(column = "entname", property = "entName"),
                    @Result(column = "invtype", property = "invType"),
                    @Result(column = "conform", property = "comForm"),
                    @Result(column = "subconam", property = "subconam"),
                    @Result(column = "conprop", property = "conProp"),
                    @Result(column = "condate", property = "conDate")
            }
    )
    List<EntContributionNewInfo> selectAllByEntName(EntContributionNewInfo entContributionNewInfo);


    /**
     * 保存企业出资信息(股东（自然人）出资信息)
     *
     * @param entContributionNewInfo 企业出资信息(股东（自然人）出资信息)
     * @return
     */
    @Insert("insert into " +
            "tab_ent_contribution_new_info(eid, entname, invtype, conform, subconam, conprop, condate) " +
            "values(#{eid}, #{entName}, #{invType}, #{conForm}, #{subconam}, #{conProp}, #{conDate})")
    @SelectKey(keyColumn = "eid", keyProperty = "eid",
            resultType = Long.class, before = false, statement = {"select last_insert_id()"})
    int saveEntContributionNewInfo(EntContributionNewInfo entContributionNewInfo);

}
