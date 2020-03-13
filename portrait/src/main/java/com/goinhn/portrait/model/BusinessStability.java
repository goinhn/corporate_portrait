package com.goinhn.portrait.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author goinhn
 */
@Data
public class BusinessStability implements Serializable {

    /**
     * eid：序号
     */
    private Long eid;

    /**
     * entName：公司名
     */
    private String entName;

    /**
     * altTime: 变更次数
     */
    private Double altTime;

}