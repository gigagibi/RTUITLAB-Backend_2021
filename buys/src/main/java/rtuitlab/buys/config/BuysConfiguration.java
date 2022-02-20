package rtuitlab.buys.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import rtuitlab.buys.authHandler.AdminInterceptor;
import rtuitlab.buys.authHandler.AuthInterceptor;

@Configuration
public class BuysConfiguration implements WebMvcConfigurer {
    @Value("${jwt.secret}")
    private String jwtSecret;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthInterceptor(jwtSecret));
        registry.addInterceptor(new AdminInterceptor(jwtSecret)).addPathPatterns("/api/**/admin");
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    };
}
