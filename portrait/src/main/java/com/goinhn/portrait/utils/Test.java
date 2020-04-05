package com.goinhn.portrait.utils;

import com.goinhn.portrait.model.dto.Page;

import java.util.Optional;

public class Test {
    public static void main(String[] args) {
        Page page = new Test().test();
        System.out.println(page.getEntName());

    }

    public Page test() {
        Page<String> page = Page
                .<String>builder()
                .entName("begin")
                .pageIndex(10)
                .pageSize(10)
                .build();

        return Optional.ofNullable(page).get();
    }
}
