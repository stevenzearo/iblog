package app.site.web.error;

/**
 * @author steve
 */
public class NotFoundException extends WebException {
    public NotFoundException(String message) {
        super(ErrorCodes.NOT_FOUND, message);
    }
}
