package app.site.web;

import app.validation.ValidatorInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * @author steve
 */
@RestControllerAdvice
public class RequestRestrictionHandler implements RequestBodyAdvice {
    private final Logger logger = LoggerFactory.getLogger(ResponseRestrictionHandler.class);
    @Autowired
    ValidatorInterface validatorInterface;

    @Override
    public boolean supports(MethodParameter methodParameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return converterType.equals(MappingJackson2HttpMessageConverter.class);
    }

    @Override
    public HttpInputMessage beforeBodyRead(HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) throws IOException {
        return inputMessage;
    }

    @Override
    public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
       /* try {
            validatorInterface.validate(body);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }*/
        return body;
    }

    @Override
    public Object handleEmptyBody(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        /*try {
            validatorInterface.validate(body);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }*/
        return body;
    }
}
