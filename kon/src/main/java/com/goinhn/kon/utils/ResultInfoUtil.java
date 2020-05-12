package com.goinhn.kon.utils;

import com.goinhn.kon.model.vo.ResultInfo;
import lombok.extern.slf4j.Slf4j;

/**
 * 创建返回的对象
 *
 * @author goinhn
 */
@Slf4j
public class ResultInfoUtil {

    public static ResultInfo createResultInfo(boolean flag, Integer status, Object data, String errorMsg, String path) {
        ResultInfo resultInfo = ResultInfo
                .builder()
                .flag(flag)
                .status(status)
                .data(data)
                .errorMsg(errorMsg)
                .build();

        log.info(path + "----------" + resultInfo.toString() + "\n");
        return resultInfo;
    }

}
