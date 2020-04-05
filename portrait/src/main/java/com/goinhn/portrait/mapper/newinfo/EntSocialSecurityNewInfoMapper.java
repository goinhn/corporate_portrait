package com.goinhn.portrait.mapper.newinfo;

import com.goinhn.portrait.model.entity.newinfo.EntSocialSecurityNewInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author goinhn
 */
@Repository
public interface EntSocialSecurityNewInfoMapper {

    /**
     * 根据公司名称进行查询
     *
     * @param entSocialSecurityNewInfo 年报社保信息（参保状态/年报五险一金欠税额）
     * @return
     */
    @Select("select * from tab_ent_social_security_new_info where entname = #{entName}")
    @Results(
            id = "entSocialSecurityNewInfoMap",
            value = {
                    @Result(id = true, column = "eid", property = "eid"),
                    @Result(column = "entname", property = "entName"),
                    @Result(column = "unpaidsocialins_so110", property = "unpaidsocialinsSo110"),
                    @Result(column = "unpaidsocialins_so210", property = "unpaidsocialinsSo210"),
                    @Result(column = "unpaidsocialins_so310", property = "unpaidsocialinsSo310"),
                    @Result(column = "unpaidsocialins_so410", property = "unpaidsocialinsSo410"),
                    @Result(column = "unpaidsocialins_so510", property = "unpaidsocialinsSo510"),
                    @Result(column = "updatetime", property = "updateTime")
            }
    )
    List<EntSocialSecurityNewInfo> selectAllByEntName(@NotNull EntSocialSecurityNewInfo entSocialSecurityNewInfo);


    /**
     * 保存年报社保信息（参保状态/年报五险一金欠税额）
     *
     * @param entSocialSecurityNewInfo 年报社保信息（参保状态/年报五险一金欠税额）
     * @return
     */
    @Insert("insert into " +
            "tab_ent_social_security_new_info(eid, entname, unpaidsocialins_so110, unpaidsocialins_so210, " +
            "unpaidsocialins_so310, unpaidsocialins_so410, unpaidsocialins_so510, updatetime) " +
            "values(#{eid}, #{entName}, #{unpaidsocialinsSo110}, #{unpaidsocialinsSo210}, #{unpaidsocialinsSo310}, " +
            "#{unpaidsocialinsSo410}, #{unpaidsocialinsSo510}, #{updateTime})")
    @SelectKey(keyColumn = "eid", keyProperty = "eid",
            resultType = Long.class, before = false, statement = {"select last_insert_id()"})
    int saveEntSocialSecurityNewInfo(@NotNull EntSocialSecurityNewInfo entSocialSecurityNewInfo);

}
