package com.goinhn.kon;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author goinhn
 */
@SpringBootApplication
@MapperScan(basePackages = "com.goinhn.kon.mapper")
@EnableTransactionManagement
public class KonApplication extends SpringBootServletInitializer {

    private static Class applicationClass = KonApplication.class;

    public static void main(String[] args) {
        SpringApplication.run(KonApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(applicationClass);
    }

}
