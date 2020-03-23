package com.goinhn.portrait.mapper;

import com.goinhn.portrait.model.vo.ShowInfo;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @author goinhn
 */
@Repository
public interface ShowInfoMapper {

    @Select("select eid, entname, bidnum, branchnum, investnum, shopnum, level_rank, entstatus, enttype, entcat, industryphy, credit_grade from tab_info " +
            "where entname = #{entName}")
    @Results(
            id = "showInfoMap",
            value = {
                    @Result(id = true, column = "eid", property = "eid"),
                    @Result(column = "entname", property = "entName"),
                    @Result(column = "bidnum", property = "bidNum"),
                    @Result(column = "branchnum", property = "branchNum"),
                    @Result(column = "investnum", property = "investNum"),
                    @Result(column = "shopnum", property = "shopNum"),
                    @Result(column = "level_rank", property = "levelRank"),
                    @Result(column = "entstatus", property = "entStatus"),
                    @Result(column = "enttype", property = "entType"),
                    @Result(column = "entcat", property = "entCat"),
                    @Result(column = "indestryphy", property = "indestryPhy"),
                    @Result(column = "credit_grade", property = "creditGrade")
            }
    )
    ShowInfo selectByEntName(ShowInfo showInfo);
}
