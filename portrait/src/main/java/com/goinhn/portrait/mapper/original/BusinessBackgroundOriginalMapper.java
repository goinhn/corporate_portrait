package com.goinhn.portrait.mapper.original;

import com.goinhn.portrait.model.entity.original.BusinessBackgroundOriginal;
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
public interface BusinessBackgroundOriginalMapper {

    /**
     * 根据公司名称返回分析数据
     *
     * @param businessBackgroundOriginal 企业背景分析数据
     * @return
     */
    @Select("select * from tab_business_background_original where entname = #{entName}")
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
    BusinessBackgroundOriginal selectAllByEntName(@NotNull BusinessBackgroundOriginal businessBackgroundOriginal);


    /**
     * 保存企业背景分析数据
     *
     * @param businessBackgroundOriginal 企业背景分析数据
     * @return
     */
    @Insert("insert into " +
            "tab_business_background_original(eid, entname, empnum, encode_entstatus, shopnum, branchnum, is_infoa, is_infob, level_rank) " +
            "values(#{eid}, #{entName}, #{empNum}, #{encodeEntStatus}, #{shopNum}, #{branchNum}, #{isInfoA}, #{isInfoB}, #{levelRank})")
    int saveBusinessBackgroundOriginal(@NotNull BusinessBackgroundOriginal businessBackgroundOriginal);

}