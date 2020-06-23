package app.site.web.error;

/**
 * @author steve
 */
public class ErrorResponse {
    public int code;
    public String message;

    public ErrorResponse(WebException exception) {
        this.code = exception.getCode();
        this.message = exception.getMessage();
    }
}
