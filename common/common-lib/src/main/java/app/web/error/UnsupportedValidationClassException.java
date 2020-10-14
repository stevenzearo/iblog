package app.web.error;

/**
 * @author steve
 */
public class UnsupportedValidationClassException extends WebException {
    public UnsupportedValidationClassException(String errorCode, String message) {
        super(WebErrorCodes.CONFLICT, errorCode, message);
    }
}
