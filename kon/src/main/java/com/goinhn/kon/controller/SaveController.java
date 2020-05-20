package com.goinhn.kon.controller;


import com.goinhn.kon.model.vo.NewInfo;
import com.goinhn.kon.model.vo.NewOriginalAnalysisLabel;
import com.goinhn.kon.model.vo.ResultInfo;
import com.goinhn.kon.service.intf.*;
import com.goinhn.kon.utils.ResultInfoUtil;
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
        log.info("/kon/save/searchEntName/{entName}----------{}\n", entName);

        boolean flag = newInfoService.isEntName(entName);

        if (flag) {
            return ResultInfoUtil.createResultInfo(
                    true,
                    200,
                    "",
                    "",
                    "/kon/save/searchEntName/{entName}");
        } else {
            return ResultInfoUtil.createResultInfo(
                    false,
                    200,
                    "",
                    "该企业已经存在",
                    "/kon/save/searchEntName/{entName}");
        }
    }

    @ApiOperation(value = "保存新输入的企业信息接口")
    @PostMapping(value = "/saveNewInfo")
    public ResultInfo saveNewInfo(@RequestBody @NotNull NewInfo newInfo) {
        log.info("/kon/save/saveNewInfo----------{}\n", newInfo.toString());

        boolean flag = false;
        try {
            flag = newInfoService.saveAllInfoSpecial(newInfo);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultInfoUtil.createResultInfo(
                    false,
                    200,
                    "",
                    "存储展示数据失败",
                    "/kon/save/saveNewInfo");
        }

        if (flag) {
            return ResultInfoUtil.createResultInfo(
                    true,
                    200,
                    "",
                    "",
                    "/kon/save/saveNewInfo");
        } else {
            return ResultInfoUtil.createResultInfo(
                    false,
                    200,
                    "",
                    "存储展示数据失败",
                    "/kon/save/saveNewInfo");
        }
    }


    @ApiOperation(value = "保存新输入的企业原始数据、分析数据和标签接口")
    @PostMapping(value = "/saveNewOriginalAnalysisLabel")
    public ResultInfo saveNewAnalysisLabel(@RequestBody @NotNull NewOriginalAnalysisLabel newOriginalAnalysisLabel) {
        log.info("/kon/save/saveNewAnalysisLabel----------{}\n", newOriginalAnalysisLabel.toString());

        String entName = newOriginalAnalysisLabel.getBusinessBackgroundAnalysis().getEntName();
        try {
            if (!analysisValueService.saveRiskValueSpecial(newOriginalAnalysisLabel)) {
                showInfoService.deleteShowInfo(entName);
                return ResultInfoUtil.createResultInfo(
                        false,
                        200,
                        "",
                        "存储展示数据失败",
                        "/kon/save/saveNewAnalysisLabel");
            } else {
                if (!labelService.saveLabelValueSpecial(newOriginalAnalysisLabel)) {
                    showInfoService.deleteShowInfo(entName);
                    return ResultInfoUtil.createResultInfo(
                            false,
                            200,
                            "",
                            "存储展示数据失败",
                            "/kon/save/saveNewAnalysisLabel");
                } else {
                    if (!originalValueService.saveRiskValueSpecial(newOriginalAnalysisLabel)) {
                        showInfoService.deleteShowInfo(entName);
                        return ResultInfoUtil.createResultInfo(
                                false,
                                200,
                                "",
                                "存储展示数据失败",
                                "/kon/save/saveNewAnalysisLabel");
                    } else {
                        return ResultInfoUtil.createResultInfo(
                                true,
                                200,
                                "",
                                "",
                                "/kon/save/saveNewAnalysisLabel");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            showInfoService.deleteShowInfo(entName);
            return ResultInfoUtil.createResultInfo(
                    false,
                    200,
                    "",
                    "存储展示数据失败",
                    "/kon/save/saveNewAnalysisLabel");
        }
    }

}
