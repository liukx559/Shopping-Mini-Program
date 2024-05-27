package com.atkexin.ssyx.common.auth;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import javax.annotation.Resource;
//拦截器路径配置类
@Configuration
public class LoginMvcConfigurerAdapter extends WebMvcConfigurationSupport {

    @Resource
    private RedisTemplate redisTemplate;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //添加自定义拦截器，设置路径
        registry.addInterceptor(
                        new UserLoginInterceptor(redisTemplate))
                .addPathPatterns("/api/**")//添加拦截的路径
                .excludePathPatterns("/api/user/weixin/wxLogin/*");//除了XXX
        super.addInterceptors(registry);//super父类方法
    }
}