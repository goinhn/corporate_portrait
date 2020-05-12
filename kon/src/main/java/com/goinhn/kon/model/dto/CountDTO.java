package com.goinhn.kon.model.dto;

import lombok.Data;

/**
 * 统计传输模型
 *
 * @author goinhn
 */
@Data
public class CountDTO {

    /**
     * userCount 用户总数
     */
    private Integer userCount;

    /**
     * adminCount 管理员总数
     */
    private Integer adminCount;

    /**
     * businessCount 企业总数
     */
    private Integer businessCount;
}
