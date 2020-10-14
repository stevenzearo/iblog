package app.validation;

import app.web.error.WebException;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * @author steve
 */
public class MinValueValidator extends AbstractValidator {
    private static final Class<? extends Annotation> MIN_CLASS = Min.class;

    public MinValueValidator(Annotation annotation) {
        super(annotation);
    }

    @Override
    <T> void validate(Annotation annotation, Field field , T t) throws WebException {
        if (annotation.annotationType() != MIN_CLASS) return;
        // todo
    }
}
