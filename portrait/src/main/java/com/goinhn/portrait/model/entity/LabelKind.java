package com.goinhn.portrait.model.entity;

import lombok.Data;

/**
 * 标签归类
 *
 * @author goinhn
 */
@Data
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
