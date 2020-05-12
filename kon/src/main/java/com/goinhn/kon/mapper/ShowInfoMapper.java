package com.goinhn.kon.mapper;

import com.goinhn.kon.model.dto.Page;
import com.goinhn.kon.model.entity.ShowInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
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
    ShowInfo selectAllByEntName(@NotNull ShowInfo showInfo);


    /**
     * 查找条目的总数
     *
     * @return
     */
    @Select("select count(*) from tab_show_info")
    int selectCount();


    /**
     * 分页查询展示信息
     *
     * @param page
     * @return
     */
    @Select("select * from tab_show_info where entname like concat('%', #{name}, '%') limit #{pageIndex}, #{pageSize}")
    @ResultMap("showInfoMap")
    List<ShowInfo> selectAllLikeEntName(@NotNull Page page);


    /**
     * 查询过滤总数
     *
     * @param page
     * @return
     */
    @Select("select count(*) from tab_show_info where entname like concat('%', #{name}, '%')")
    int selectLikeCount(@NotNull Page page);


    /**
     * 保存展示信息
     *
     * @param showInfo 数据展示模型
     * @return
     */
    @Insert("insert into " +
            "tab_show_info(eid, entname, bidnum, branchnum, investnum, shopnum, entstatus, " +
            "enttype, entcat, industryphy, credit_grade) " +
            "values(#{eid}, #{entName}, #{bidNum}, #{branchNum}, #{investNum}, #{shopNum}, " +
            "#{entStatus}, #{entType}, #{entCat}, #{industryPhy}, #{creditGrade})")
    @SelectKey(keyColumn = "eid", keyProperty = "eid",
            resultType = Long.class, before = false, statement = {"select last_insert_id()"})
    int saveShowInfo(@NotNull ShowInfo showInfo);


    /**
     * 根据名字删除指定的展示信息
     *
     * @param showInfo 展示信息
     * @return
     */
    @Delete("delete from tab_show_info where entName = #{entName}")
    int deleteShowInfoByEntName(@NotNull ShowInfo showInfo);


    /**
     * 根据eid删除指定的展示信息
     *
     * @param showInfo 展示信息
     * @return
     */
    @Delete("delete from tab_show_info where eid = #{eid}")
    int deleteShowInfoById(@NotNull ShowInfo showInfo);
}
