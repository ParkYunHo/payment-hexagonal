package com.john.payment.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author john.09
 * @since 2022.12.18
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry
            .addMapping("/**")
            .allowedOrigins("*")
            // java17부터 HttpMethod는 interface가 아닌 class형식으로 변경됨
            .allowedMethods(HttpMethod.GET.name(), HttpMethod.POST.name())
            .allowedHeaders("*")
            .maxAge(3600L);
    }
}
