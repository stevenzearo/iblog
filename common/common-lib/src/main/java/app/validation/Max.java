package app.validation;

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
public @interface Max {
    double value();

    String msg() default "field value can not greater than min value %d, value = %d";
}
