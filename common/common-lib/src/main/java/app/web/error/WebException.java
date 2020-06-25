package app.web.error;

/**
 * @author steve
 */
public class WebException extends Exception {
    int code = WebErrorCodes.SERVER_ERROR;
    String errorCode;

    public WebException() {
    }

    public WebException(String message) {
        super(message);
    }

    public WebException(int code, String message) {
        super(message);
        this.code = code;
    }

    public WebException(int code, String errorCode, String message) {
        super(message);
        this.code = code;
        this.errorCode = errorCode;
    }
    public int getCode() {
        return code;
    }
}
