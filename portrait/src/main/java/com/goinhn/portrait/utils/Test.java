package com.goinhn.portrait.utils;


import com.goinhn.portrait.model.vo.ResultInfo;

public class Test {
    public static void main(String[] args) {
        String str = "nA";

        ResultInfo resultInfo = ResultInfo
                .builder()
                .data(str.replaceFirst("(^)(?i)na($)", "未知"))
                .build();


        System.out.println(resultInfo);
    }
}
