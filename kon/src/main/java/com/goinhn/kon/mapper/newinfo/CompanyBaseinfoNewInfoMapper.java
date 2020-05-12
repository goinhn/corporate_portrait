package com.goinhn.kon.mapper.newinfo;

import com.goinhn.kon.model.entity.newinfo.CompanyBaseinfoNewInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author goinhn
 */
@Repository
public interface CompanyBaseinfoNewInfoMapper {

    /**
     * 根据公司名称进行查询
     *
     * @param companyBaseinfoNewInfo 企业基本信息
     * @return
     */
    @Select("select * from tab_company_baseinfo_new_info where entname = #{entName}")
    @Results(
            id = "companyBaseinfoNewInfoMap",
            value = {
                    @Result(id = true, column = "eid", property = "eid"),
                    @Result(column = "entname", property = "entName"),
                    @Result(column = "regcap", property = "regcap"),
                    @Result(column = "empnum", property = "empNum"),
                    @Result(column = "estdate", property = "estDate"),
                    @Result(column = "candate", property = "canDate"),
                    @Result(column = "revdate", property = "revDate"),
                    @Result(column = "entstatus", property = "entStatus"),
                    @Result(column = "opto", property = "opto"),
                    @Result(column = "enttype", property = "entType"),
                    @Result(column = "entcat", property = "entCat"),
                    @Result(column = "industryphy", property = "industryphy"),
                    @Result(column = "regcapcur", property = "regcapcur"),
                    @Result(column = "industryco", property = "industryco"),
                    @Result(column = "opfrom", property = "opFrom")
            }
    )
    List<CompanyBaseinfoNewInfo> selectAllByEntName(@NotNull CompanyBaseinfoNewInfo companyBaseinfoNewInfo);


    /**
     * 保存企业基本信息
     *
     * @param companyBaseinfoNewInfo 企业基本信息
     * @return
     */
    @Insert("insert into " +
            "tab_company_baseinfo_new_info(eid, entname,regcap,empnum,estdate,candate,revdate,entstatus,opto,enttype, " +
            "entcat, industryphy,regcapcur,industryco,opfrom) " +
            "values(#{eid}, #{entName}, #{regcap}, #{empNum}, #{estDate}, #{canDate}, #{revDate}, #{entStatus}, " +
            "#{opto}, #{entType}, #{entCat}, #{industryphy}, #{regcapcur}, #{industryco}, #{opFrom})")
    @SelectKey(keyColumn = "eid", keyProperty = "eid",
            resultType = Long.class, before = false, statement = {"select last_insert_id()"})
    int saveCompanyBaseinfoNewInfo(@NotNull CompanyBaseinfoNewInfo companyBaseinfoNewInfo);
}
