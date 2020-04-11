package com.goinhn.portrait.mapper.analysis;

import com.goinhn.portrait.model.entity.analysis.BusinessBackgroundAnalysis;
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
public interface BusinessBackgroundAnalysisMapper {

    /**
     * 根据公司名称返回分析数据
     *
     * @param businessBackgroundAnalysis 企业背景分析数据
     * @return
     */
    @Select("select * from tab_business_background_analysis where entname = #{entName}")
    @Results(
            id = "businessBackgroundMap",
            value = {
                    @Result(id = true, column = "eid", property = "eid"),
                    @Result(column = "entname", property = "entName"),
                    @Result(column = "empnum", property = "empNum"),
                    @Result(column = "encode_entstatus", property = "encodeEntStatus"),
                    @Result(column = "shopnum", property = "shopNum"),
                    @Result(column = "branchnum", property = "branchNum"),
                    @Result(column = "is_infoa", property = "isInfoA"),
                    @Result(column = "is_infob", property = "isInfoB"),
                    @Result(column = "level_rank", property = "levelRank")
            }
    )
    BusinessBackgroundAnalysis selectAllByEntName(@NotNull BusinessBackgroundAnalysis businessBackgroundAnalysis);


    /**
     * 保存企业背景分析数据
     *
     * @param businessBackgroundAnalysis 企业背景分析数据
     * @return
     */
    @Insert("insert into " +
            "tab_business_background_analysis(eid, entname, empnum, encode_entstatus, shopnum, branchnum, is_infoa, is_infob, level_rank) " +
            "values(#{eid}, #{entName}, #{empNum}, #{encodeEntStatus}, #{shopNum}, #{branchNum}, #{isInfoA}, #{isInfoB}, #{levelRank})")
    int saveBusinessBackgroundAnalysis(@NotNull BusinessBackgroundAnalysis businessBackgroundAnalysis);

}