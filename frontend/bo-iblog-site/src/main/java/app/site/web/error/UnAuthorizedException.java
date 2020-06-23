package app.site.web.error;

/**
 * @author steve
 */
public class UnAuthorizedException extends WebException {
    public UnAuthorizedException(String message) {
        super(ErrorCodes.UNAUTHORIZED, message);
    }
}
