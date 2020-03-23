package app.site;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

/**
 * @author steve
 */
@Configuration
public class WebMVCConfig {
    public void addCORSMapping(CorsRegistry corsRegistry) {
        corsRegistry.addMapping("/**")
            .allowedOrigins("*")
            .allowedMethods("POST", "PUT", "GET", "DELETE")
            .maxAge(3600)
            .allowCredentials(true);
    }
}
