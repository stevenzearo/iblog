package app.validation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * @author steve
 */
abstract class AbstractValidator {
    abstract <T> void validate(Annotation annotation, Field field, T t) throws Exception;

    protected abstract void validateFieldValue(Field field, Object t) throws Exception;

    protected abstract void validateFieldClass(Field field, Object fieldValue) throws Exception;
}
