package com.goinhn.kon.config;


import com.goinhn.kon.interceptor.AuthInterceptor;
import com.goinhn.kon.interceptor.ErrorInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author goinhn
 */
@Configuration
public class SpringMVCConfig implements WebMvcConfigurer {


    @Bean
    public HandlerInterceptor getErrorInterceptor() {
        return new ErrorInterceptor();
    }

    @Bean
    public HandlerInterceptor getAuthInterceptor() {
        return new AuthInterceptor();
    }


    @Override
    public void addCorsMappings(CorsRegistry corsRegistry) {

        corsRegistry.addMapping("/**").
                allowedHeaders("*").
                allowedMethods("*").
                maxAge(1800).
                allowedOrigins("*");

    }

    /**
     * 重写接口中的addInterceptors方法，添加自定义拦截器
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(getErrorInterceptor()).addPathPatterns("/**");

        List<String> authPatterns = new ArrayList();
        authPatterns.add("/webjars/**");
        authPatterns.add("/swagger/**");
        authPatterns.add("/swagger-ui.html");
        authPatterns.add("/swagger-resources/**");
        authPatterns.add("/static/**");
        authPatterns.add("/verification/**");
        authPatterns.add("/verify/**");
        authPatterns.add("/error/**");

        registry.addInterceptor(getAuthInterceptor()).addPathPatterns("/**").excludePathPatterns(authPatterns);
    }


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //默认静态资源处理
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");

        //Swagger 静态资源处理
        registry.addResourceHandler("/swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
        registry.addResourceHandler("/csrf/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
