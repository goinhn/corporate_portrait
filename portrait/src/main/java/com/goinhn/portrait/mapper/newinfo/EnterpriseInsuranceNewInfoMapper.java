package com.goinhn.portrait.mapper.newinfo;

import com.goinhn.portrait.model.entity.newinfo.EnterpriseInsuranceNewInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author goinhn
 */
@Repository
public interface EnterpriseInsuranceNewInfoMapper {

    /**
     * 根据公司名称进行查询
     *
     * @param enterpriseInsuranceNewInfo 单位参保信息查询（养老单位参保信息）
     * @return
     */
    @Select("select * from tab_ent_guarantee_new_info where entname = {entName}")
    @Results(
            id = "enterpriseInsuranceNewInfoMap",
            value = {
                    @Result(id = true, column = "eid", property = "eid"),
                    @Result(column = "entname", property = "entName"),
                    @Result(column = "cbrq", property = "cbrq"),
                    @Result(column = "xzbz", property = "xzbz"),
                    @Result(column = "sbjgbh", property = "sbjgbh"),
                    @Result(column = "xzbzmc", property = "xzbzmc"),
                    @Result(column = "cbzt", property = "cbzt"),
                    @Result(column = "cbztmc", property = "cbztmc"),
                    @Result(column = "dwbh", property = "dwbh")
            }
    )
    List<EnterpriseInsuranceNewInfo> selectAllByEntName(@NotNull EnterpriseInsuranceNewInfo enterpriseInsuranceNewInfo);


    /**
     * 保存单位参保信息查询（养老单位参保信息）
     *
     * @param enterpriseInsuranceNewInfo 单位参保信息查询（养老单位参保信息）
     * @return
     */
    @Insert("insert into " +
            "tab_ent_guarantee_new_info(eid, entname, chrq, xzbz, sbjgbh, xzbzmc, cbzt, cbztmc, dwbh) " +
            "values(#{eid}, #{entName}, #{cbrq}, #{xzbz}, #{sbjgbh}, #{xzbzmc}, #{cbzt}, #{cbztmc}, #{dwbh})")
    @SelectKey(keyColumn = "eid", keyProperty = "eid",
            resultType = Long.class, before = false, statement = {"select last_insert_id()"})
    int saveEnterpriseInsuranceNewInfo(@NotNull EnterpriseInsuranceNewInfo enterpriseInsuranceNewInfo);
}
