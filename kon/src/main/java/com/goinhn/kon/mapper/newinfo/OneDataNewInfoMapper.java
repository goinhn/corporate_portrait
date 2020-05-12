package com.goinhn.kon.mapper.newinfo;

import com.goinhn.kon.model.entity.newinfo.OneDataNewInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author goinhn
 */
@Repository
public interface OneDataNewInfoMapper {

    /**
     * 根据公司名称进行查询
     *
     * @param oneDataNewInfo 单条数据
     * @return
     */
    @Select("select * from tab_one_data_new_info where entname = #{entName}")
    @Results(
            id = "oneDataNewInfoMap",
            value = {
                    @Result(id = true, column = "eid", property = "eid"),
                    @Result(column = "entname", property = "entName"),
                    @Result(column = "is_punish", property = "isPunish"),
                    @Result(column = "is_bra", property = "isBra"),
                    @Result(column = "is_brap", property = "isBrap"),
                    @Result(column = "pledgenum", property = "pledgeNum"),
                    @Result(column = "taxunpaidnum", property = "taxunpaidNum"),
                    @Result(column = "bidnum", property = "bidNum"),
                    @Result(column = "branchnum", property = "branchNum"),
                    @Result(column = "investnum", property = "investNum"),
                    @Result(column = "shopnum", property = "shopNum"),
                    @Result(column = "is_kcont", property = "isKcont"),
                    @Result(column = "is_except", property = "isExcept"),
                    @Result(column = "ibrand_num", property = "ibrandNum"),
                    @Result(column = "icopy_num", property = "icopyNum"),
                    @Result(column = "ipat_num", property = "ipatNum"),
                    @Result(column = "is_jnsn", property = "isJnsn"),
                    @Result(column = "level_rank", property = "levelRank"),
                    @Result(column = "is_justice_credit", property = "isJusticeCredit"),
                    @Result(column = "is_justice_creditaic", property = "isJusticeCreditaic"),
                    @Result(column = "passpercent", property = "passpercent"),
                    @Result(column = "qcwynum", property = "qcwyNum"),
                    @Result(column = "zhycnum", property = "zhycNum"),
                    @Result(column = "zlzpnum", property = "zlzpNum"),
                    @Result(column = "is_infoa", property = "isInfoa"),
                    @Result(column = "is_infob", property = "isInfob"),
                    @Result(column = "idom_num", property = "idomNum")
            }
    )
    List<OneDataNewInfo> selectAllByEntName(@NotNull OneDataNewInfo oneDataNewInfo);

    /**
     * 保存单条数据
     *
     * @param oneDataNewInfo 单条数据
     * @return
     */
    @Insert("insert into " +
            "tab_one_data_new_info(eid, entname, is_punish, is_bra, is_brap, pledgenum, taxunpaidnum, bidnum, " +
            "branchnum, investnum, shopnum, is_kcont, is_except, ibrand_num, icopy_num, ipat_num, is_jnsn, " +
            "level_rank, is_justice_credit, is_justice_creditaic, passpercent, qcwynum, zhycnum, zlzpnum, " +
            "is_infoa, is_infob, idom_num) " +
            "values(#{eid}, #{entName}, #{isPunish}, #{isBra}, #{isBrap}, #{pledgeNum}, #{taxunpaidNum}, #{bidNum}, " +
            "#{branchNum}, #{investNum}, #{shopNum}, #{isKcont}, #{isExcept}, #{ibrandNum}, #{icopyNum}, #{ipatNum}, " +
            "#{isJnsn}, #{levelRank}, #{isJusticeCredit}, #{isJusticeCreditaic}, #{passpercent}, #{qcwyNum}, " +
            "#{zhycNum}, #{zlzpNum}, #{isInfoa}, #{isInfob}, #{idomNum})")
    @SelectKey(keyColumn = "eid", keyProperty = "eid",
            resultType = Long.class, before = false, statement = {"select last_insert_id()"})
    int saveOneDataNewInfo(@NotNull OneDataNewInfo oneDataNewInfo);

}
