package com.hung.config;

import javax.annotation.Resource;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mobile.device.DeviceResolverHandlerInterceptor;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.hung.config.interceptor.AdminInterceptor;
import com.hung.config.interceptor.ControllerHandlerManagerInterceptor;

/**
 * 
 * クラスタイトル(ピリオド削除厳禁).
 *
 * <pre>
 * 内容, 使用例など
 * </pre>
 *
 * @author Mitsui Zosen Systems Research Inc.
 * @version X.X
 * @since TIME-3 X.X
 */
@Configuration
// Bật spring web mvc
@EnableWebMvc
@PropertySource("classpath:setting/web/web.properties")
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    /** Environment. */
    @Resource
    private Environment env;

    // Cấu hình để sử dụng các file nguồn tĩnh (html, image, ..)
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        String[] resourceDirectories = env.getProperty("web.resources.directories").split(",");
        for (String resourceDirectory : resourceDirectories) {
            registry.addResourceHandler(resourceDirectory).addResourceLocations(resourceDirectory.replace("**", ""));
        }
        // registry.addResourceHandler("/assets/css/**").addResourceLocations("/assets/css/").setCachePeriod(31556926);
        // registry.addResourceHandler("/assets/js/**").addResourceLocations("/assets/css/").setCachePeriod(31556926);
        // registry.addResourceHandler("/assets/fonts/**").addResourceLocations("/assets/css/").setCachePeriod(31556926);
        // registry.addResourceHandler("/assets/images/**").addResourceLocations("/assets/css/").setCachePeriod(31556926);
    }

    /**
     * Default serverlet config.
     * 
     * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter#configureDefaultServletHandling(org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer)
     * @param configurer
     */
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    // Refer
    // https://docs.spring.io/spring/docs/3.2.x/spring-framework-reference/html/new-in-3.0.html#new-java-configuration

    /**
     * Interceptor.
     * 
     * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter#addInterceptors(org.springframework.web.servlet.config.annotation.InterceptorRegistry)
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        /** Interceptor : 非対象URLパターン. */
        final String[] INTERCEPT_PATH_PATTERNS_EXCLUDE = new String[] {
                // Root
                "/",
                // ログイン
                "/login",
        };

        // LogInterceptor áp dụng cho mọi URL.
        // DeviceResolverHandlerInterceptor nhận biết các request có nguồn gốc từ web
        registry.addInterceptor(new DeviceResolverHandlerInterceptor());

        // Đường dẫn login cũ, không còn sử dụng nữa.
        // Sử dụng OldURLInterceptor để điều hướng tới một URL mới.
        registry.addInterceptor(new ControllerHandlerManagerInterceptor())
                .addPathPatterns(new String[] {"/**"});

        // Interceptor này áp dụng cho các URL có dạng /admin/*
        // Loại đi trường hợp /admin/oldLogin
        registry.addInterceptor(new AdminInterceptor())
                .addPathPatterns("/admin/*")//
                .excludePathPatterns(INTERCEPT_PATH_PATTERNS_EXCLUDE);
    }

}
