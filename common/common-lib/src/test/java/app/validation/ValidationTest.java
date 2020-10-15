package app.validation;

import java.lang.reflect.Field;

/**
 * @author steve
 */
public class ValidationTest {
    public static void main(String[] args) throws Exception {
        AObject hello = new AObject("id-0001", "hello", 11, 15.2f);
        ValidatorInterface validator = new ValidatorImpl();

        for (Field f : hello.getClass().getDeclaredFields()) {
            validator.validate(f, hello);
        }
    }
}
