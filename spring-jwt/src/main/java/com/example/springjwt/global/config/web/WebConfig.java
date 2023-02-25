package com.example.springjwt.global.config.web;

import com.example.springjwt.global.interceptor.AdminAuthorizationInterceptor;
import com.example.springjwt.global.interceptor.AuthenticationInterceptor;
import com.example.springjwt.global.resolver.memberinfo.MemberInfoArgumentResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final AuthenticationInterceptor authenticationInterceptor;
    private final AdminAuthorizationInterceptor adminAuthorizationInterceptor;
    private final MemberInfoArgumentResolver memberInfoArgumentResolver;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authenticationInterceptor)
                .order(1)
                .addPathPatterns("/api/**")
                .excludePathPatterns(
                        "/api/oauth/login",
                        "/api/logout",
                        "/api/token/access-token/issue"
                );

        registry.addInterceptor(adminAuthorizationInterceptor)
                .order(2)
                .addPathPatterns("/api/admin/**");

    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(memberInfoArgumentResolver);
    }
}
