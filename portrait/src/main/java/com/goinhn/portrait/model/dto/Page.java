package com.goinhn.portrait.model.dto;

import lombok.Builder;
import lombok.Data;

/**
 * 分页
 *
 * @author goinhn
 */
@Data
@Builder
public class Page<T> {

    private T entName;
    /**
     * 起始页
     */
    private Integer pageIndex;

    /**
     * 页面数
     */
    private Integer pageSize;
}
