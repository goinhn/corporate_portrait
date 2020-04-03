package com.goinhn.portrait.mapper.newinfo;

import com.goinhn.portrait.model.entity.newinfo.JusticeDeclareNewInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author goinhn
 */
@Repository
public interface JusticeDeclareNewInfoMapper {

    /**
     * 根据公司名称进行查询
     *
     * @param justiceDeclareNewInfo 司法风险—开庭公告数据
     * @return
     */
    @Select("select * from tab_justice_declare_new_info where entname = #{entName}")
    @Results(
            id = "justiceDeclareNewInfoMap",
            value = {
                    @Result(id = true, column = "eid", property = "eid"),
                    @Result(column = "entname", property = "entName"),
                    @Result(column = "declaredate", property = "declareDate"),
                    @Result(column = "appellant", property = "appellant"),
                    @Result(column = "defendant", property = "defendant"),
                    @Result(column = "declarestyle", property = "declareStyle")
            }
    )
    List<JusticeDeclareNewInfo> selectAllByEntName(JusticeDeclareNewInfo justiceDeclareNewInfo);


    /**
     * 保存司法风险—开庭公告数据
     *
     * @param justiceDeclareNewInfo 司法风险—开庭公告数据
     * @return
     */
    @Insert("insert into " +
            "tab_justice_declare_new_info(eid, entname, declaredate, appellant, defendant, declarestyle) " +
            "values(#{eid}, #{entName}, #{declareDate}, #{appellant}, #{defendant}, #{declareStyle})")
    @SelectKey(keyColumn = "eid", keyProperty = "eid",
            resultType = Long.class, before = false, statement = {"select last_insert_id()"})
    int saveJusticeDeclareNewInfo(JusticeDeclareNewInfo justiceDeclareNewInfo);

}
