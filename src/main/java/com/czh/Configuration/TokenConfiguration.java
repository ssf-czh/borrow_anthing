package com.czh.Configuration;

import com.czh.interceptor.TokenInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class TokenConfiguration implements WebMvcConfigurer {
    @Bean
    TokenInterceptor tokenInterceptor() {
        return new TokenInterceptor();
        // 这个方法才能在拦截器中自动注入查询数据库的对象
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry ){
        registry.addInterceptor(tokenInterceptor()).addPathPatterns("/**");
        //配置生成器：添加一个拦截器，拦截路径为API以后的路径
    }
}
