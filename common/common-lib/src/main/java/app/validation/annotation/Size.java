package app.validation.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author steve
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Validator
public @interface Size {
    int min() default -1;
    int max() default -1;
}
