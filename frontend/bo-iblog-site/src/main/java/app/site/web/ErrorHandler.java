package app.site.web;

import app.web.error.ErrorResponse;
import app.web.error.MethodNotAllowedException;
import app.web.error.WebException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author steve
 */
@RestControllerAdvice
public class ErrorHandler {
    @ExceptionHandler(Exception.class)
    public ErrorResponse handleError(Exception exception) {
        return new ErrorResponse(new WebException(exception.getMessage()));
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ErrorResponse handleError(HttpRequestMethodNotSupportedException exception) {
        return new ErrorResponse(new MethodNotAllowedException(exception.getMessage()));
    }

    @ExceptionHandler(WebException.class)
    public ErrorResponse handleError(WebException exception) {
        return new ErrorResponse(exception);
    }
}
