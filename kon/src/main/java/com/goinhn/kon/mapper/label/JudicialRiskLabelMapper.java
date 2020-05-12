package com.goinhn.kon.mapper.label;

import com.goinhn.kon.model.entity.label.JudicialRiskLabel;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;

/**
 * @author goinhn
 */
@Repository
public interface JudicialRiskLabelMapper {

    /**
     * 根据公司名返回司法风险标签序号数据
     *
     * @param judicialRiskLabel 司法风险标签
     * @return
     */
    @Select("select * from tab_judicial_risk_label where entname = #{entName}")
    @Results(
            id = "judicialRiskLabelMap",
            value = {
                    @Result(id = true, column = "eid", property = "eid"),
                    @Result(column = "entname", property = "entName"),
                    @Result(column = "label", property = "label")
            }
    )
    JudicialRiskLabel selectAllByEntName(@NotNull JudicialRiskLabel judicialRiskLabel);


    /**
     * 保存司法风险标签
     *
     * @param judicialRiskLabel 司法风险标签
     * @return
     */
    @Insert("insert into " +
            "tab_judicial_risk_label(eid, entname, label) " +
            "values(#{eid}, #{entName}, #{label})")
    int saveJudicialRiskLabel(@NotNull JudicialRiskLabel judicialRiskLabel);


    /**
     * 根据id进行删除
     *
     * @param eid
     * @return
     */
    @Delete("delete from tab_judicial_risk_label where eid = #{eid}")
    int deleteById(@NotNull Long eid);

}
