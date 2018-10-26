package com.code.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 实现基本的跨域请求
 *
 * @author Admin
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {
    // private CorsConfiguration buildConfig() {
    //     CorsConfiguration corsConfiguration = new CorsConfiguration();
    //     // 允许任何域名使用
    //     corsConfiguration.addAllowedOrigin("*");
    //     // 允许任何头
    //     corsConfiguration.addAllowedHeader("*");
    //     // 允许任何方法（post、get等）
    //     corsConfiguration.addAllowedMethod("*");
    //     return corsConfiguration;
    // }
    //
    // @Bean
    // public CorsFilter corsFilter() {
    //     UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    //     // 对接口配置跨域设置
    //     source.registerCorsConfiguration("/**", buildConfig());
    //     return new CorsFilter(source);
    // }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowCredentials(true)
                .allowedMethods("GET", "POST", "DELETE", "PUT")
                .maxAge(3600 * 24);
    }
}
