package app.site.web.error;

/**
 * @author steve
 */
public class MethodNotAllowedException extends WebException {
    public MethodNotAllowedException(String message) {
        super(ErrorCodes.NOT_ALLOWED, message);
    }
}
