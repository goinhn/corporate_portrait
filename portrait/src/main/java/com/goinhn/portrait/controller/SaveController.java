package com.goinhn.portrait.controller;


import com.goinhn.portrait.model.vo.NewAnalysisLabel;
import com.goinhn.portrait.model.vo.NewInfo;
import com.goinhn.portrait.model.vo.ResultInfo;
import com.goinhn.portrait.service.intf.AnalysisValueService;
import com.goinhn.portrait.service.intf.LabelService;
import com.goinhn.portrait.service.intf.NewInfoService;
import com.goinhn.portrait.service.intf.ShowInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;


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
    AnalysisValueService analysisValueService;

    @Autowired
    LabelService labelService;

    @Autowired
    NewInfoService newInfoService;

    @Autowired
    ShowInfoService showInfoService;

    /**
     * @param entName 企业名称
     * @return
     */
    @ApiOperation(value = "企业名称搜索是否唯一接口")
    @GetMapping(value = "/searchEntName/{entName}")
    public ResultInfo searchEntName(@PathVariable("entName")
                                    @NotNull(message = "搜索的企业名称不为空") String entName) {
        log.info("/por/save/searchEntName/{entName}" + "----------" + entName + "\n");

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
    @PostMapping(value = "/saveNewInfo")
    public ResultInfo saveNewInfo(@RequestBody @NotNull NewInfo newInfo) {
        log.info("/por/save/saveNewInfo" + "----------" + newInfo.toString() + "\n");

        boolean flag = false;
        try {
            flag = newInfoService.saveAllInfoSpecial(newInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (flag) {
            return ResultInfo
                    .builder()
                    .flag(true)
                    .build();
        } else {
            return ResultInfo
                    .builder()
                    .flag(false)
                    .errorMsg("存储展示数据失败")
                    .build();
        }
    }


    @ApiOperation(value = "保存新输入的企业分析数据和标签接口")
    @PostMapping(value = "/saveNewAnalysisLabel")
    public ResultInfo saveNewAnalysisLabel(@RequestBody @NotNull NewAnalysisLabel newAnalysisLabel) {
        log.info("/por/save/saveNewAnalysisLabel" + "----------" + newAnalysisLabel.toString() + "\n");

        String entName = newAnalysisLabel.getBusinessBackgroundAnalysis().getEntName();
        try {
            if (!analysisValueService.saveRiskValueSpecial(newAnalysisLabel)) {
                showInfoService.deleteShowInfo(entName);
                return ResultInfo
                        .builder()
                        .flag(false)
                        .errorMsg("存储展示数据失败")
                        .build();
            } else {
                if (!labelService.saveLabelValueSpecial(newAnalysisLabel)) {
                    showInfoService.deleteShowInfo(entName);
                    return ResultInfo
                            .builder()
                            .flag(false)
                            .errorMsg("存储展示数据失败")
                            .build();
                } else {
                    return ResultInfo
                            .builder()
                            .flag(true)
                            .build();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            showInfoService.deleteShowInfo(entName);
            return ResultInfo
                    .builder()
                    .flag(false)
                    .errorMsg("存储展示数据失败")
                    .build();
        }
    }

}
