package app.validation;

import app.web.error.WebException;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * @author steve
 */
public abstract class AbstractValidator implements ValidatorInterface {
    protected final Annotation annotation;

    public AbstractValidator(Annotation annotation) {
        this.annotation = annotation;
    }

    abstract <T> void validate(Annotation annotation, Field field, T t) throws WebException;

    @Override
    public <T> void validate(Field field, T t) throws WebException {
        for (java.lang.annotation.Annotation a : field.getDeclaredAnnotations()) {
            Class<? extends java.lang.annotation.Annotation> annotationType = a.annotationType();
            if (annotationType.getDeclaredAnnotation(Validator.class) == null) continue;
            validate(annotation, field, t);
        }
    }
}
