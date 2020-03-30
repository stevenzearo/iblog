package app.user;

import app.user.WebServiceInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author steve
 */
@Configuration
public class UserServiceAdapter implements WebMvcConfigurer {
    @Autowired
    WebServiceInterceptor webServiceInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(webServiceInterceptor);
    }
}
