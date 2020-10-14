package app.validation;

import app.web.error.UnsupportedValidationClassException;
import app.web.error.ValidationException;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Set;

/**
 * @author steve
 */
public class MinValueValidator extends AbstractValidator {
    private static final Class<? extends Annotation> MIN_CLASS = Min.class;
    private static final Set<Class<? extends Number>> VALID_CLASSES = Set.of(Integer.class, Long.class, Double.class, Float.class);

    public MinValueValidator(Annotation annotation) {
        super(annotation);
    }

    @Override
    <T> void validate(Annotation annotation, Field field, T t) throws Exception {
        if (annotation.annotationType() != MIN_CLASS) return;
        validateFieldClass(field, t);
    }

    @Override
    <T> void validateFieldClass(Field field, T t) throws Exception {
        Object o = field.get(t);
        Class<?> valueClass = o.getClass();
        if (!VALID_CLASSES.contains(valueClass))
            throw new UnsupportedValidationClassException(String.format("unsupported validation class %s", valueClass.getCanonicalName()));
        double d = (Double) o;
        Min minAnnotation = field.getDeclaredAnnotation(Min.class);
        if (d < minAnnotation.value())
            throw new ValidationException(String.format(minAnnotation.msg(), d, minAnnotation.value()));
    }
}
