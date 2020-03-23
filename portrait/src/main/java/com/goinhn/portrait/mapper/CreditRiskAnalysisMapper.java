package com.goinhn.portrait.mapper;

import com.goinhn.portrait.model.entity.CreditRiskAnalysis;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @author goinhn
 */
@Repository
public interface CreditRiskAnalysisMapper {

    /**
     * 根据公司名称返回分析数据
     *
     * @param creditRiskAnalysis 公司名称
     * @return
     */
    @Select("select * from tab_credit_risk_analysis where entname = #{entName}")
    @Results(
            id = "creditRiskValueMap",
            value = {
                    @Result(id = true, column = "eid", property = "eid"),
                    @Result(column = "entname", property = "entName"),
                    @Result(column = "is_punish", property = "isPunish"),
                    @Result(column = "is_kcont", property = "isKcont"),
                    @Result(column = "credit_grade", property = "creditGrade"),
                    @Result(column = "is_justice_creditaic", property = "sJusticeCreditaic")
            }
    )
    CreditRiskAnalysis selectAllByEntName(CreditRiskAnalysis creditRiskAnalysis);
}
