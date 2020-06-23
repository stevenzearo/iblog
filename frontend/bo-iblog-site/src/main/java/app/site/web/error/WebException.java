package app.site.web.error;

/**
 * @author steve
 */
public class WebException extends Exception {
    int code = ErrorCodes.SERVER_ERROR;

    public WebException() {
    }

    public WebException(String message) {
        super(message);
    }

    public WebException(int code, String message) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
