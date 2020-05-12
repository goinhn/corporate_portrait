package com.goinhn.kon.mapper.newinfo;

import com.goinhn.kon.model.entity.newinfo.JnCreditInfoNewInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author goinhn
 */
@Repository
public interface JnCreditInfoNewInfoMapper {

    /**
     * 根据公司名称进行查询
     *
     * @param jnCreditInfoNewInfo 济南市信用信息
     * @return
     */
    @Select("select * from tab_jn_credit_info_new_info where entname = #{entName}")
    @Results(
            id = "jnCreditInfoNewInfoMap",
            value = {
                    @Result(id = true, column = "eid", property = "eid"),
                    @Result(column = "entname", property = "entName"),
                    @Result(column = "credit_grade", property = "creditGrade")
            }
    )
    List<JnCreditInfoNewInfo> selectAllByEntName(@NotNull JnCreditInfoNewInfo jnCreditInfoNewInfo);


    /**
     * 保存济南市信用信息
     *
     * @param jnCreditInfoNewInfo 济南市信用信息
     * @return
     */
    @Insert("insert into " +
            "tab_jn_credit_info_new_info(eid, entname, credit_grade) " +
            "values(#{eid}, #{entName}, #{creditGrade})")
    @SelectKey(keyColumn = "eid", keyProperty = "eid",
            resultType = Long.class, before = false, statement = {"select last_insert_id()"})
    int saveJnCreditInfoNewInfo(@NotNull JnCreditInfoNewInfo jnCreditInfoNewInfo);

}
