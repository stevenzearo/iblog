package app.web.error;

/**
 * @author steve
 */
public class WebException extends Exception {
    int code = WebErrorCodes.SERVER_ERROR;
    String errorCode = "INTERNAL_ERROR";

    public WebException() {
    }

    public WebException(String message) {
        super(message);
    }

    public WebException(Throwable cause) {
        super(cause);
    }

    public WebException(String message, Throwable cause) {
        super(message, cause);
    }

    public WebException(String errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
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

    public String getErrorCode() {
        return errorCode;
    }
}
