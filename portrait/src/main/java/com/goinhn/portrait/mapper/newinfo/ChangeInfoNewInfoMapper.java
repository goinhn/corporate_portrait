package com.goinhn.portrait.mapper.newinfo;

import com.goinhn.portrait.model.entity.newinfo.ChangeInfoNewInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author goinhn
 */
@Repository
public interface ChangeInfoNewInfoMapper {

    /**
     * 根据公司名称进行查询
     *
     * @param changeInfoNewInfo 企业变更信息
     * @return
     */
    @Select("select * from tab_change_info_new_info where entname = #{entName}")
    @Results(
            id = "changeInfoNewInfoMap",
            value = {
                    @Result(id = true, column = "eid", property = "eid"),
                    @Result(column = "entname", property = "entName"),
                    @Result(column = "remark", property = "remark"),
                    @Result(column = "dataflag", property = "dataFlag"),
                    @Result(column = "alttime", property = "altTime"),
                    @Result(column = "altitem", property = "altItem"),
                    @Result(column = "cxstatus", property = "cxStatus"),
                    @Result(column = "altdate", property = "altDate"),
                    @Result(column = "openo", property = "openo")
            }
    )
    List<ChangeInfoNewInfo> selectAllByEntName(ChangeInfoNewInfo changeInfoNewInfo);


    /**
     * 保存企业变更信息
     *
     * @param changeInfoNewInfo 企业变更信息
     * @return
     */
    @Insert("insert into " +
            "tab_change_info_new_info(eid, entname, remark, dataflag, alttime, altitem, cxstatus, altdate, openo) " +
            "values(#{eid}, #{entName}, #{remark}, #{dataFlag}, #{altTime}, #{altItem}, #{cxStatus}, #{altDate}, #{openo})")
    @SelectKey(keyColumn = "eid", keyProperty = "eid",
            resultType = Long.class, before = false, statement = {"select last_insert_id()"})
    int saveChangeInfoNewInfo(ChangeInfoNewInfo changeInfoNewInfo);

}
