package com.goinhn.kon.mapper.newinfo;

import com.goinhn.kon.model.entity.newinfo.JusticeJudgeNewNewInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author goinhn
 */
@Repository
public interface JusticeJudgeNewNewInfoMapper {

    /**
     * 根据公司名称进行查询
     *
     * @param justiceJudgeNewNewInfo 司法风险-裁判文书数据
     * @return
     */
    @Select("select * from tab_justice_judge_new_new_info where entname = #{entName}")
    @Results(
            id = "justiceJudgeNewNewInfoMap",
            value = {
                    @Result(id = true, column = "eid", property = "eid"),
                    @Result(column = "entname", property = "entName"),
                    @Result(column = "time", property = "time"),
                    @Result(column = "title", property = "title"),
                    @Result(column = "casetype", property = "caseType"),
                    @Result(column = "judgeresult", property = "judgeResult"),
                    @Result(column = "casecause", property = "caseCause"),
                    @Result(column = "evidence", property = "evidence"),
                    @Result(column = "courtrank", property = "courtRank"),
                    @Result(column = "datatype", property = "dataType"),
                    @Result(column = "latypes", property = "laTypes")
            }
    )
    List<JusticeJudgeNewNewInfo> selectAllByEntName(@NotNull JusticeJudgeNewNewInfo justiceJudgeNewNewInfo);


    /**
     * 保存司法风险-裁判文书数据
     *
     * @param justiceJudgeNewNewInfo 司法风险-裁判文书数据
     * @return
     */
    @Insert("insert into " +
            "tab_justice_judge_new_new_info(eid, entname, time, title, casetype, judgeresult, casecause, evidence, " +
            "courtrank, datatype, latypes) " +
            "values(#{eid}, #{entName}, #{time}, #{title}, #{caseType}, #{judgeResult}, #{caseCause}, #{evidence}, " +
            "#{courtRank}, #{dataType}, #{laTypes})")
    @SelectKey(keyColumn = "eid", keyProperty = "eid",
            resultType = Long.class, before = false, statement = {"select last_insert_id()"})
    int saveJusticeJudgeNewNewInfo(@NotNull JusticeJudgeNewNewInfo justiceJudgeNewNewInfo);

}

