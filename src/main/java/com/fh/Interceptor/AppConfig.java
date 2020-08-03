package com.fh.Interceptor;

import com.fh.resolver.MemberResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;

@Configuration
public class AppConfig extends WebMvcConfigurationSupport {

    @Autowired
    private MemberResolver memberResolver;

    @Bean
    public LoginInterceptory loginInterceptory(){
        return new LoginInterceptory();
    }

    @Bean
    public IdempotentInterceptory idempotentInterceptory(){
        return new IdempotentInterceptory();
    }

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        super.addInterceptors(registry);
        registry.addInterceptor(loginInterceptory()).addPathPatterns("/**");
        registry.addInterceptor(idempotentInterceptory()).addPathPatterns("/**");
    }

    @Override
    protected void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        super.addArgumentResolvers(argumentResolvers);
        argumentResolvers.add(memberResolver);
    }
}
