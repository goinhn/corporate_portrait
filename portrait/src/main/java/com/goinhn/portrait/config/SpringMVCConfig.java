package com.goinhn.portrait.config;


import com.goinhn.portrait.filter.BeginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @author goinhn
 */
@Configuration
public class SpringMVCConfig extends WebMvcConfigurationSupport {

    /**
     * 通过@Bean注解，将我们定义的拦截器注册到Spring容器
     *
     * @return
     */
    @Bean
    public BeginInterceptor loginInterceptor() {
        return new BeginInterceptor();
    }

    /**
     * 重写接口中的addInterceptors方法，添加自定义拦截器
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 通过registry来注册拦截器，通过addPathPatterns来添加拦截路径
        registry.addInterceptor(this.loginInterceptor()).addPathPatterns("/**");
    }


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //默认静态资源处理
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");

        //Swagger 静态资源处理
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
        registry.addResourceHandler("/csrf/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
