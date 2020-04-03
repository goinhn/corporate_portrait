package com.goinhn.portrait.mapper;

import com.goinhn.portrait.model.entity.LabelKind;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @author goinhn
 */
@Repository
public interface LabelKindMapper {

    /**
     * 查找标签所属的归类
     *
     * @param labelKind 标签归类
     * @return
     */
    @Select("select * from tab_label_kind where kind = #{kind} and number = #{number}")
    @Results(
            id = "labelKindMap",
            value = {
                    @Result(id = true, column = "eid", property = "eid"),
                    @Result(column = "kind", property = "kind"),
                    @Result(column = "number", property = "number"),
                    @Result(column = "label", property = "label")
            }
    )
    LabelKind selectAllByKindAndNumber(LabelKind labelKind);

}
