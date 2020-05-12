package com.goinhn.kon.controller;



import com.goinhn.kon.model.vo.NewInfo;
import com.goinhn.kon.model.vo.NewOriginalAnalysisLabel;
import com.goinhn.kon.model.vo.ResultInfo;
import com.goinhn.kon.service.intf.*;
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
    private OriginalValueService originalValueService;

    @Autowired
    private AnalysisValueService analysisValueService;

    @Autowired
    private LabelService labelService;

    @Autowired
    private NewInfoService newInfoService;

    @Autowired
    private ShowInfoService showInfoService;

    /**
     * @param entName 企业名称
     * @return
     */
    @ApiOperation(value = "企业名称搜索是否唯一接口")
    @GetMapping(value = "/searchEntName/{entName}")
    public ResultInfo searchEntName(@PathVariable("entName")
                                    @NotNull(message = "搜索的企业名称不为空") String entName) {
        log.info("/kon/save/searchEntName/{entName}" + "----------" + entName + "\n");

        boolean flag = newInfoService.isEntName(entName);

        if (flag) {
            ResultInfo resultInfo = ResultInfo
                    .builder()
                    .status(200)
                    .flag(true)
                    .build();
            log.info("/kon/save/searchEntName/{entName}" + "++++++++++" + resultInfo.toString() + "\n");
            return resultInfo;

        } else {
            ResultInfo resultInfo = ResultInfo
                    .builder()
                    .status(200)
                    .flag(false)
                    .errorMsg("该企业已经存在")
                    .build();
            log.info("/kon/save/searchEntName/{entName}" + "++++++++++" + resultInfo.toString() + "\n");
            return resultInfo;
        }
    }

    @ApiOperation(value = "保存新输入的企业信息接口")
    @PostMapping(value = "/saveNewInfo")
    public ResultInfo saveNewInfo(@RequestBody @NotNull NewInfo newInfo) {
        log.info("/kon/save/saveNewInfo" + "----------" + newInfo.toString() + "\n");

        boolean flag = false;
        try {
            flag = newInfoService.saveAllInfoSpecial(newInfo);
        } catch (Exception e) {
            e.printStackTrace();
            ResultInfo resultInfo = ResultInfo
                    .builder()
                    .status(200)
                    .flag(false)
                    .errorMsg("存储展示数据失败")
                    .build();
            log.info("/kon/save/saveNewInfo" + "++++++++++" + resultInfo.toString() + "\n");
            return resultInfo;
        }

        if (flag) {
            ResultInfo resultInfo = ResultInfo
                    .builder()
                    .status(200)
                    .flag(true)
                    .build();
            log.info("/kon/save/saveNewInfo" + "++++++++++" + resultInfo.toString() + "\n");
            return resultInfo;
        } else {
            ResultInfo resultInfo = ResultInfo
                    .builder()
                    .status(200)
                    .flag(false)
                    .errorMsg("存储展示数据失败")
                    .build();
            log.info("/kon/save/saveNewInfo" + "++++++++++" + resultInfo.toString() + "\n");
            return resultInfo;
        }
    }


    @ApiOperation(value = "保存新输入的企业原始数据、分析数据和标签接口")
    @PostMapping(value = "/saveNewOriginalAnalysisLabel")
    public ResultInfo saveNewAnalysisLabel(@RequestBody @NotNull NewOriginalAnalysisLabel newOriginalAnalysisLabel) {
        log.info("/kon/save/saveNewAnalysisLabel" + "----------" + newOriginalAnalysisLabel.toString() + "\n");

        String entName = newOriginalAnalysisLabel.getBusinessBackgroundAnalysis().getEntName();
        try {
            if (!analysisValueService.saveRiskValueSpecial(newOriginalAnalysisLabel)) {
                showInfoService.deleteShowInfo(entName);
                ResultInfo resultInfo = ResultInfo
                        .builder()
                        .flag(false)
                        .status(200)
                        .errorMsg("存储展示数据失败")
                        .build();
                log.info("/kon/save/saveNewAnalysisLabel" + "++++++++++" + resultInfo.toString() + "\n");
                return resultInfo;
            } else {
                if (!labelService.saveLabelValueSpecial(newOriginalAnalysisLabel)) {
                    showInfoService.deleteShowInfo(entName);
                    ResultInfo resultInfo = ResultInfo
                            .builder()
                            .flag(false)
                            .status(200)
                            .errorMsg("存储展示数据失败")
                            .build();
                    log.info("/kon/save/saveNewAnalysisLabel" + "++++++++++" + resultInfo.toString() + "\n");
                    return resultInfo;
                } else {
                    if (!originalValueService.saveRiskValueSpecial(newOriginalAnalysisLabel)) {
                        showInfoService.deleteShowInfo(entName);
                        ResultInfo resultInfo = ResultInfo
                                .builder()
                                .flag(false)
                                .status(200)
                                .errorMsg("存储展示数据失败")
                                .build();
                        log.info("/kon/save/saveNewAnalysisLabel" + "++++++++++" + resultInfo.toString() + "\n");
                        return resultInfo;
                    } else {
                        ResultInfo resultInfo = ResultInfo
                                .builder()
                                .flag(true)
                                .status(200)
                                .build();
                        log.info("/kon/save/saveNewAnalysisLabel" + "++++++++++" + resultInfo.toString() + "\n");
                        return resultInfo;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            showInfoService.deleteShowInfo(entName);
            ResultInfo resultInfo = ResultInfo
                    .builder()
                    .flag(false)
                    .status(200)
                    .errorMsg("存储展示数据失败")
                    .build();
            log.info("/kon/save/saveNewAnalysisLabel" + "++++++++++" + resultInfo.toString() + "\n");
            return resultInfo;
        }
    }

}
