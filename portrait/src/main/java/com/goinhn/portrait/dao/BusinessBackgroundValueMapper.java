package com.goinhn.portrait.dao;

import com.goinhn.portrait.model.BusinessBackgroundValue;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @author goinhn
 */
@Repository
public interface BusinessBackgroundValueMapper {

    /**
     * 根据公司名称返回分析数据
     *
     * @param businessBackgroundValue 企业背景
     * @return
     */
    @Select("select * from tab_business_background_value where entname = #{entName}")
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
    BusinessBackgroundValue selectAllByEntName(BusinessBackgroundValue businessBackgroundValue);
}