package com.goinhn.portrait.model.entity;

import lombok.Builder;
import lombok.Data;

/**
 * 标签归类
 *
 * @author goinhn
 */
@Data
@Builder
public class LabelKind {

    /**
     * 序号
     */
    private Long eid;

    /**
     * 类别
     */
    private String kind;

    /**
     * 标签数字
     */
    private Integer number;

    /**
     * 标签
     */
    private String label;

}
