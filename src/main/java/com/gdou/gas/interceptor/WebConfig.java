package com.gdou.gas.interceptor;

/**
 * 用来配置拦截器的配置类
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


import java.util.Arrays;
import java.util.List;


@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private LoginInterceptor loginInterceptor;


    @Autowired
    private HomeLoginInterceptor homeloginInterceptor;
    List<String>  excludePathPatterns = Arrays.asList(
            "/system/login",
            "/home/**",
            "/admin/css/**",
            "/admin/js/**",
            "/admin/fonts/**",
            "/error",
            "/cpacha/**",
            "/admin/images/**"
    );
    List<String>  homeExcludePathPatterns = Arrays.asList(
            "/home/login",
            "/home/index",
            "/home/active/**",
            "/home/register",
            "/admin/css/**",
            "/admin/js/**",
            "/admin/fonts/**",
            "/error",
            "/cpacha/**",
            "/admin/images/**"
    );

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor).addPathPatterns("/**").excludePathPatterns(excludePathPatterns);
        registry.addInterceptor(homeloginInterceptor).addPathPatterns("/home/**").excludePathPatterns(homeExcludePathPatterns);
    }

}
