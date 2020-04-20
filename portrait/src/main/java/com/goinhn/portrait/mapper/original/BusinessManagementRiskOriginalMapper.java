package com.goinhn.portrait.mapper.original;

import com.goinhn.portrait.model.entity.analysis.BusinessManagementRiskAnalysis;
import com.goinhn.portrait.model.entity.original.BusinessManagementRiskOriginal;
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
public interface BusinessManagementRiskOriginalMapper {

    /**
     * 根据公司名称返回分析数据
     *
     * @param businessManagementRiskOriginal 企业经营风险分析
     * @return
     */
    @Select("select * from tab_business_management_risk_original where entname = #{entName}")
    @Results(
            id = "businessManagementRiskValueMap",
            value = {
                    @Result(id = true, column = "eid", property = "eid"),
                    @Result(column = "entname", property = "entName"),
                    @Result(column = "priclasecam", property = "priclasecam"),
                    @Result(column = "encode_guaranperiod", property = "encodeGuaranperiod"),
                    @Result(column = "encode_gatype", property = "encodeGatype"),
                    @Result(column = "is_rage", property = "isRage"),
                    @Result(column = "sub_pefperfromto", property = "subPefperfromto"),
                    @Result(column = "unpaidsocialins", property = "unpaidsocialins"),
                    @Result(column = "is_bra", property = "isBra"),
                    @Result(column = "is_brap", property = "isBrap"),
                    @Result(column = "pledgenum", property = "pledgeNum"),
                    @Result(column = "taxunpaidnum", property = "taxunpaidNum"),
                    @Result(column = "is_except", property = "isExcept")
            }
    )
    BusinessManagementRiskOriginal selectAllByEntName(@NotNull BusinessManagementRiskOriginal businessManagementRiskOriginal);


    /**
     * 保存企业经营风险分析数据
     *
     * @param businessManagementRiskOriginal 企业经营风险分析
     * @return
     */
    @Insert("insert into " +
            "tab_business_management_risk_original(eid, entname, priclasecam, encode_guaranperiod, encode_gatype, " +
            "is_rage, sub_pefperfromto, unpaidsocialins, is_bra, is_brap, pledgenum, taxunpaidnum, is_except) " +
            "values(#{eid}, #{entName}, #{priclasecam}, #{encodeGuaranperiod}, #{encodeGatype}, #{isRage}, #{subPefperfromto}, " +
            "#{unpaidsocialins}, #{isBra}, #{isBrap}, #{pledgeNum}, #{taxunpaidNum}, #{isExcept})")
    int saveBusinessManagementRiskOriginal(@NotNull BusinessManagementRiskOriginal businessManagementRiskOriginal);

}
