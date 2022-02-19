package rtulab.shops.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import rtulab.shops.authHandler.AdminInterceptor;
import rtulab.shops.authHandler.AuthInterceptor;

@Configuration
public class ShopsConfiguration implements WebMvcConfigurer {
    @Value("${jwt.secret}")
    private String jwtSecret;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthInterceptor(jwtSecret));
        registry.addInterceptor(new AdminInterceptor(jwtSecret)).addPathPatterns("/api/**/admin");
    }
}
