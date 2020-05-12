package com.goinhn.kon.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author goinhn
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {
    /**
     * 添加摘要信息
     */
    @Bean
    public Docket controllerApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .title("企业画像系统接口文档")
                        .description("Swagger接口文档")
                        .contact(new Contact("goinhn", null, null))
                        .version("1.0")
                        .build())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.goinhn.kon.controller"))
                .paths(PathSelectors.any())
                .build();
    }

}
