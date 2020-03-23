package com.goinhn.portrait;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author goinhn
 */
@SpringBootApplication
@MapperScan(basePackages = "com.goinhn.portrait.mapper")
@EnableTransactionManagement
public class PortraitApplication {

    public static void main(String[] args) {
        SpringApplication.run(PortraitApplication.class, args);
    }

}
