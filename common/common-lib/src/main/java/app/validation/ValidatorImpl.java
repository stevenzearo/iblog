package app.validation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Map;

/**
 * @author steve
 */
public class ValidatorImpl implements ValidatorInterface {
    Map<Class<? extends Annotation>, AbstractValidator> validatorMap = Map.of(Min.class, new MinValueValidator());

    @Override
    public <T> void validate(Field field, T t) throws Exception {
        for (Annotation a : field.getDeclaredAnnotations()) {
            Class<? extends Annotation> annotationType = a.annotationType();
            if (annotationType.getDeclaredAnnotation(Validator.class) == null) continue;
            AbstractValidator validator = validatorMap.get(annotationType);
            validator.validate(a, field, t);
        }
    }
}
