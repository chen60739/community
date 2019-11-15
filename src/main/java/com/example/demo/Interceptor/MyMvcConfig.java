package com.example.demo.Interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyMvcConfig implements WebMvcConfigurer {

    @Autowired
    private SessionInterceptor sessionInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //添加需要验证登录用户操作权限的请求
        registry.addInterceptor(sessionInterceptor).addPathPatterns("/**").excludePathPatterns("/css/**","/js/**");
        //排除不需要验证登录用户操作权限的请求
    }
}