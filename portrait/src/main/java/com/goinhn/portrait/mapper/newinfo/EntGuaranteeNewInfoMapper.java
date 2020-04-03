package com.goinhn.portrait.mapper.newinfo;

import com.goinhn.portrait.model.entity.newinfo.EntGuaranteeNewInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author goinhn
 */
@Repository
public interface EntGuaranteeNewInfoMapper {

    /**
     * 根据公司名称进行查询
     *
     * @param entGuaranteeNewInfo 企业年报对外担保
     * @return
     */
    @Select("select * from tab_ent_guarantee_new_info where entname = {entName}")
    @Results(
            id = "entGuaranteeNewInfoMap",
            value = {
                    @Result(id = true, column = "eid", property = "eid"),
                    @Result(column = "entname", property = "entName"),
                    @Result(column = "priclaseckind", property = "priclaseckind"),
                    @Result(column = "pefperfrom", property = "pefperFrom"),
                    @Result(column = "iftopub", property = "iftopub"),
                    @Result(column = "priclasecam", property = "priclasecam"),
                    @Result(column = "pefperto", property = "pefperto"),
                    @Result(column = "guaranperiod", property = "guaranperiod"),
                    @Result(column = "gatype", property = "gaType"),
                    @Result(column = "rage", property = "rage")
            }
    )
    List<EntGuaranteeNewInfo> selectAllByEntName(EntGuaranteeNewInfo entGuaranteeNewInfo);


    /**
     * 保存企业年报对外担保信息
     *
     * @param entGuaranteeNewInfo 企业年报对外担保
     * @return
     */
    @Insert("insert into " +
            "tab_ent_guarantee_new_info(eid, entname, priclaseckind, pefperfrom, iftopub, priclasecam, pefperto, " +
            "guaranperiod, gatype, rage) " +
            "values(#{eid}, #{entName}, #{priclaseckind}, #{pefperFrom}, #{iftopub}, #{priclasecam}, #{pefperto}, " +
            "#{guaranperiod}, #{gaType}, #{rage})")
    @SelectKey(keyColumn = "eid", keyProperty = "eid",
            resultType = Long.class, before = false, statement = {"select last_insert_id()"})
    int saveEntGuaranteeNewInfo(EntGuaranteeNewInfo entGuaranteeNewInfo);

}
