package com.goinhn.portrait.mapper;

import com.goinhn.portrait.model.dto.Page;
import com.goinhn.portrait.model.entity.ShowInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author goinhn
 */
@Repository
public interface ShowInfoMapper {

    /**
     * 根据企业名称查询企业数据
     *
     * @param showInfo 数据展示模型
     * @return
     */
    @Select("select * from tab_show_info where entname = #{entName}")
    @Results(
            id = "showInfoMap",
            value = {
                    @Result(id = true, column = "eid", property = "eid"),
                    @Result(column = "entname", property = "entName"),
                    @Result(column = "bidnum", property = "bidNum"),
                    @Result(column = "branchnum", property = "branchNum"),
                    @Result(column = "investnum", property = "investNum"),
                    @Result(column = "shopnum", property = "shopNum"),
                    @Result(column = "entstatus", property = "entStatus"),
                    @Result(column = "enttype", property = "entType"),
                    @Result(column = "entcat", property = "entCat"),
                    @Result(column = "indestryphy", property = "indestryPhy"),
                    @Result(column = "credit_grade", property = "creditGrade")
            }
    )
    ShowInfo selectAllByEntName(ShowInfo showInfo);


    /**
     * 查找条目的总数
     *
     * @return
     */
    @Select("select count(*) from tab_show_info")
    int findAllCount();


    @Select("select * from tab_show_info where entname like concat('%', #{entName}, '%') limit #{pageIndex}, #{pageSize}")
    @ResultMap("showInfoMap")
    List<ShowInfo> selectAllLikeEntName(Page page);


    /**
     * 保存展示信息
     *
     * @param showInfo 数据展示模型
     * @return
     */
    @Insert("insert into " +
            "tab_show_info(eid, entname, bidnum, branchnum, investnum, shopnum, entstatus, " +
            "enttype, entcat, indestryphy, credit_grade) " +
            "values(#{eid}, #{entName}, #{bidNum}, #{branchNum}, #{investNum}, #{shopNum}, " +
            "#{entStatus}, #{entType}, #{entCat}, #{indestryPhy}, #{creditGrade})")
    @SelectKey(keyColumn = "eid", keyProperty = "eid",
            resultType = Long.class, before = false, statement = {"select last_insert_id()"})
    int saveShowInfo(ShowInfo showInfo);
}
