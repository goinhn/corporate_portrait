package com.goinhn.portrait.mapper.newinfo;

import com.goinhn.portrait.model.entity.newinfo.JusticeEnforcedNewInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author goinhn
 */
@Repository
public interface JusticeEnforcedNewInfoMapper {

    /**
     * 根据公司名称进行查询
     *
     * @param justiceEnforcedNewInfo 司法风险—被执行人数据
     * @return
     */
    @Select("select * from tab_justice_enforced_new_info where entname = #{entName}")
    @Results(
            id = "justiceEnforcedNewInfoMap",
            value = {
                    @Result(id = true, column = "eid", property = "eid"),
                    @Result(column = "entname", property = "entName"),
                    @Result(column = "record_date", property = "recordDate"),
                    @Result(column = "enforce_amount", property = "enforceAmount"),
            }
    )
    List<JusticeEnforcedNewInfo> selectAllByEntName(@NotNull JusticeEnforcedNewInfo justiceEnforcedNewInfo);


    /**
     * 保存司法风险—被执行人数据
     *
     * @param justiceEnforcedNewInfo 司法风险—被执行人数据
     * @return
     */
    @Insert("insert into " +
            "tab_justice_enforced_new_info(eid, entname, record_date, enforce_amount) " +
            "values(#{eid}, #{entName}, #{recordDate}, #{enforceAmount})")
    @SelectKey(keyColumn = "eid", keyProperty = "eid",
            resultType = Long.class, before = false, statement = {"select last_insert_id()"})
    int saveJusticeEnforcedNewInfo(@NotNull JusticeEnforcedNewInfo justiceEnforcedNewInfo);

}
