package app.validation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Map;

/**
 * @author steve
 */
public class ValidatorImpl implements ValidatorInterface {
    Map<Class<? extends Annotation>, AbstractValidator> validatorMap = Map.of(
        Min.class, new MinValueValidator(),
        Max.class, new MaxValueValidator(),
        NotNull.class, new NotNullValidator(),
        NotBlank.class, new NotBlankValidator(),
        Size.class, new SizeValidator()
    );

    @Override
    public <T> void validate(T t) throws Exception {
        Field[] declaredFields = t.getClass().getDeclaredFields();
        for (Field f : declaredFields) {
            for (Annotation a : f.getDeclaredAnnotations()) {
                Class<? extends Annotation> annotationType = a.annotationType();
                if (annotationType.getDeclaredAnnotation(Validator.class) == null) continue;
                AbstractValidator validator = validatorMap.get(annotationType);
                validator.validate(a, f, t);
            }
        }
    }
}
