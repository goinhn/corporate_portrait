package com.goinhn.kon.model.dto;

import lombok.Data;

/**
 * 分页
 *
 * @author goinhn
 */
@Data
public class Page {

    private Object name;
    /**
     * 起始页
     */
    private Integer pageIndex;

    /**
     * 页面数
     */
    private Integer pageSize;

}
