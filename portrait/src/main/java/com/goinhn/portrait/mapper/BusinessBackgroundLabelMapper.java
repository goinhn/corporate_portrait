package com.goinhn.portrait.mapper;

import com.goinhn.portrait.model.entity.BusinessBackgroundLabel;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @author goinhn
 */
@Repository
public interface BusinessBackgroundLabelMapper {

    /**
     * 根据公司名返回企业背景标签序号数据
     *
     * @param businessBackgroundLabel 企业背景
     * @return
     */
    @Select("select * from tab_business_background_label where entname = #{entName}")
    @Results(
            id = "businessBackgroundLabelMap",
            value = {
                    @Result(id = true, column = "eid", property = "eid"),
                    @Result(column = "entname", property = "entName"),
                    @Result(column = "label", property = "label")
            }
    )
    BusinessBackgroundLabel selectByEntName(BusinessBackgroundLabel businessBackgroundLabel);
}
