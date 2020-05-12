package com.goinhn.kon.model.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * 返回前端的企业表格信息
 *
 * @author goinhn
 */
@Data
@Builder
public class ShowTableInfo implements Serializable {

    /**
     * 企业名称
     */
    private String entName;

    /**
     * 企业类别
     */
    private String entCat;

}
