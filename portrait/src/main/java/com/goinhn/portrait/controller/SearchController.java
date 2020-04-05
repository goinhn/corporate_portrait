package com.goinhn.portrait.controller;

import com.goinhn.portrait.model.vo.ResultInfo;
import com.goinhn.portrait.model.dto.ShowTableInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author goinhn
 */
@Api(tags = "搜索接口")
@RestController
@RequestMapping(value = "/search")
public class SearchController {

    @ApiOperation(value = "企业名称搜索接口")
    @GetMapping(value = "/searchEntName/pageIndex={pageIndex}&pageSize={pageSize}&entName={entName}")
    public ResultInfo searchEntName(@PathVariable("pageIndex") Integer pageIndex,
                                    @PathVariable("pageSize") Integer pageSize,
                                    @PathVariable("entName") String entName) {
        System.out.println(pageIndex);
        System.out.println(pageSize);
        System.out.println(entName);
        List<ShowTableInfo> list = new ArrayList<>();
        ShowTableInfo showTableInfo_1 = ShowTableInfo
                .builder()
                .entName("fkahfkdhfkajfhjkd")
                .entCat("begin")
                .build();
        ShowTableInfo showTableInfo_2 = ShowTableInfo
                .builder()
                .entName("fkahfkdhfkajfhjkd")
                .entCat("begin")
                .build();
        list.add(showTableInfo_1);
        list.add(showTableInfo_2);

        ResultInfo resultInfo = ResultInfo
                .builder()
                .flag(true)
                .data(list)
                .errorMsg("")
                .build();


        return resultInfo;
    }

//    @PostMapping(value = "/test", produces = "application/json")
//    public ResultInfo test(@RequestBody Te te) {
//
//    }


}
