package app.validation;

import app.web.error.WebException;

import java.lang.reflect.Field;

/**
 * @author steve
 */
public interface ValidatorInterface {
    <T> void validate(Field field , T t) throws WebException;
}
