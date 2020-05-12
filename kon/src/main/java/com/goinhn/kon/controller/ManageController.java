package com.goinhn.kon.controller;

import com.goinhn.kon.model.dto.BusinessDTO;
import com.goinhn.kon.model.dto.CountDTO;
import com.goinhn.kon.model.dto.UserDTO;
import com.goinhn.kon.model.vo.ResultInfo;
import com.goinhn.kon.service.intf.AdminBusinessService;
import com.goinhn.kon.service.intf.AdminCountService;
import com.goinhn.kon.service.intf.AdminPeopleService;
import com.goinhn.kon.utils.ResultInfoUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author goinhn
 */
@Api(tags = "管理操作接口")
@RestController()
@RequestMapping(value = "/manage")
@CrossOrigin
@Slf4j
public class ManageController {

    @Autowired
    private AdminCountService adminCountService;

    @Autowired
    private AdminPeopleService adminPeopleService;


    @Autowired
    private AdminBusinessService adminBusinessService;


    @ApiOperation(value = "统计接口")
    @GetMapping(value = "/count")
    public ResultInfo count() {
        log.info("/kon/manage/count\n");

        CountDTO countDTO = new CountDTO();
        countDTO.setUserCount(adminCountService.countUser());
        countDTO.setAdminCount(adminCountService.countAdmin());
        countDTO.setBusinessCount(adminCountService.countBusiness());

        return ResultInfoUtil.createResultInfo(true, 200, countDTO, "", "/kon/manage/count");

    }


    @ApiOperation(value = "用户管理接口")
    @GetMapping(value = "/people")
    public Map<String, Object> people(@RequestParam(value = "start") Integer start,
                                      @RequestParam(value = "length") Integer length,
                                      @RequestParam(value = "draw") Integer draw,
                                      @RequestParam(value = "search[value]") String search) {

        log.info("/kon/manage/people" + "----------" + "start:" + start.toString() + "length:" + length.toString() + "draw:" + draw.toString() + "search:" + search + "\n");

        Integer recordsTotal = adminPeopleService.countLikeUser(search, start, length);

        Integer recordsFiltered = adminPeopleService.countUser();

        List<UserDTO> list = adminPeopleService.filterUser(search, start, length);

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("data", list);
        resultMap.put("recordsTotal", recordsTotal);
        resultMap.put("recordsFiltered", recordsFiltered);
        resultMap.put("draw", draw);

        return resultMap;
    }


    @ApiOperation(value = "企业管理接口")
    @GetMapping(value = "/business")
    public Map<String, Object> business(@RequestParam(value = "start") Integer start,
                                        @RequestParam(value = "length") Integer length,
                                        @RequestParam(value = "draw") Integer draw,
                                        @RequestParam(value = "search[value]") String search) {

        log.info("/kon/manage/business" + "----------" + "start:" + start.toString() + "length:" + length.toString() + "draw:" + draw.toString() + "search:" + search + "\n");

        Integer recordsTotal = adminBusinessService.countLikeBusiness(search, start, length);

        Integer recordsFiltered = adminBusinessService.countBusiness();

        List<BusinessDTO> list = adminBusinessService.filterBusiness(search, start, length);

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("data", list);
        resultMap.put("recordsTotal", recordsTotal);
        resultMap.put("recordsFiltered", recordsFiltered);
        resultMap.put("draw", draw);

        return resultMap;
    }


    @ApiOperation(value = "用户删除接口")
    @GetMapping(value = "/userdelete")
    public ResultInfo userDelete(@RequestParam(value = "id") Long id) {

        log.info("/kon/manage/userdelete" + "----------" + id.toString() + "\n");

        boolean result = adminPeopleService.deleteUser(id);

        return ResultInfoUtil.createResultInfo(result, 200, "", "", "/kon/manage/userdelete");
    }


    @ApiOperation(value = "企业删除接口")
    @GetMapping(value = "/businessdelete")
    public ResultInfo businessDelete(@RequestParam(value = "id") Long id) {

        log.info("/kon/manage/businessdelete" + "----------" + id.toString() + "\n");

        boolean result = adminBusinessService.deleteBusiness(id);

        return ResultInfoUtil.createResultInfo(result, 200, "", "", "/kon/manage/businessdelete");
    }


}
