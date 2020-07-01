package app.site.web;

import app.site.web.interceptor.LoginRequiredInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author steve
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Autowired
    LoginRequiredInterceptor loginRequiredInterceptor;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // process error: Reason: CORS header "Access-Control-Allow-Origin" missing
        registry.addMapping("/**")
            .allowedOrigins("http://47.99.152.254:3000")
            .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE")
            .maxAge(3600)
            .allowCredentials(true);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginRequiredInterceptor).addPathPatterns("/**");
    }
}
