package app.web.error;

/**
 * @author steve
 */
public class NotFoundException extends WebException {
    public NotFoundException(String errorCode, String message) {
        super(WebErrorCodes.NOT_FOUND, errorCode, message);
    }
}
