package app.validation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author steve
 */
public class ValidationTest {
    public static void main(String[] args) {
        AObject hello = new AObject("hello", 11);
        Field field = hello.getClass().getDeclaredFields()[0];
        Annotation[] declaredAnnotations = field.getDeclaredAnnotations();
        List<Annotation> annotations = Arrays.stream(declaredAnnotations).filter(annotation -> annotation
            .annotationType()
            .getDeclaredAnnotation(Validator.class) != null)
            .collect(Collectors.toList());
        annotations.forEach(a -> System.out.println(a.annotationType().getCanonicalName()));
    }
}
