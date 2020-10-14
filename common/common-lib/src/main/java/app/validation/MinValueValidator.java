package app.validation;

import app.web.error.UnsupportedValidationClassException;
import app.web.error.ValidationException;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Set;

/**
 * @author steve
 */
class MinValueValidator extends AbstractValidator {
    private static final Class<? extends Annotation> MIN_CLASS = Min.class;
    private static final Set<Class<? extends Number>> VALID_CLASSES = Set.of(Integer.class, Long.class, Double.class, Float.class);

    @Override
    <T> void validate(Annotation annotation, Field field, T t) throws Exception {
        Object fieldValue = field.get(t);
        if (annotation.annotationType() != MIN_CLASS) return;
        validateFieldClass(field, fieldValue);
        validateFieldValue(field, fieldValue);
    }

    @Override
    protected void validateFieldValue(Field field, Object fieldValue) throws ValidationException {
        double d = Double.parseDouble(fieldValue.toString());
        Min minAnnotation = field.getDeclaredAnnotation(Min.class);
        if (d < minAnnotation.value())
            throw new ValidationException(String.format(minAnnotation.msg(), d, minAnnotation.value()));
    }

    @Override
    protected void validateFieldClass(Field field, Object fieldValue) throws Exception {
        Class<?> valueClass = fieldValue.getClass();
        if (!VALID_CLASSES.contains(valueClass))
            throw new UnsupportedValidationClassException(String.format("unsupported validation class %s", valueClass.getCanonicalName()));
    }
}
