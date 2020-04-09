package com.goinhn.portrait.controller;

import com.goinhn.portrait.model.entity.ShowInfo;
import com.goinhn.portrait.model.entity.newinfo.*;
import com.goinhn.portrait.model.vo.NewInfo;
import com.goinhn.portrait.model.vo.ResultInfo;
import com.goinhn.portrait.service.intf.NewInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author goinhn
 */
@Api(tags = "保存接口")
@RestController()
@RequestMapping(value = "/save")
@CrossOrigin
@Slf4j
public class SaveController {

    @Autowired
    NewInfoService newInfoService;

    /**
     * @param entName 企业名称
     * @return
     */
    @ApiOperation(value = "企业名称搜索是否唯一接口")
    @GetMapping(value = "/searchEntName/{entName}")
    public ResultInfo searchEntName(@PathVariable("entName")
                                    @NotNull(message = "搜索的企业名称不为空") String entName) {
        boolean flag = newInfoService.isEntName(entName);

        if (flag) {
            return ResultInfo
                    .builder()
                    .flag(true)
                    .build();
        } else {
            return ResultInfo
                    .builder()
                    .flag(false)
                    .errorMsg("该企业已经存在")
                    .build();
        }
    }

    @ApiOperation(value = "保存新输入的企业信息接口")
    @PostMapping(value = "/saveEntName")
    public ResultInfo saveNewInfo(@RequestBody @NotNull NewInfo newInfo) {

        return null;
    }

}
