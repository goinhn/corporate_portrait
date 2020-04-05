package com.goinhn.portrait.model.vo;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * 封转返回的集合
 *
 * @author goinhn
 */
@Data
@Builder
public class ResultInfo implements Serializable {

    /**
     * 后端返回结果正常为true，发生异常返回false
     */
    private boolean flag;

    /**
     * 后端返回结果数据对象
     */
    private Object data;

    /**
     * 发生异常的错误消息
     */
    private String errorMsg;

}
