package app.validation;

import java.lang.reflect.Field;

/**
 * @author steve
 */
public class ValidationTest {
    public static void main(String[] args) throws Exception {
        AObject.ABObject abObject = new AObject.ABObject();
        abObject.id = "id-0001";
        abObject.name = "world";
        abObject.score = 189.99f;
        AObject hello = new AObject("id-0001", "hello", 11, 15.2f, abObject);
        ValidatorInterface validator = new ValidatorImpl();
        validator.validate(hello);
    }
}
