package app.site.web;

import app.web.error.ErrorResponse;
import app.web.error.MethodNotAllowedException;
import app.web.error.WebException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MarkerFactory;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;

/**
 * @author steve
 */
@RestControllerAdvice
public class ErrorHandler {
    private final Logger logger = LoggerFactory.getLogger(ErrorHandler.class);

    @ExceptionHandler(Exception.class)
    public ErrorResponse handleError(HttpServletResponse response, Exception exception) {
        WebException webException = new WebException(exception.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(webException);

        if (exception instanceof WebException) {
            webException = (WebException) exception;
            errorResponse = new ErrorResponse(webException);
        } else if (exception instanceof HttpRequestMethodNotSupportedException) {
            HttpRequestMethodNotSupportedException methodNotSupportedException = (HttpRequestMethodNotSupportedException) exception;
            errorResponse = handleError(methodNotSupportedException);
        }

        logger.error(MarkerFactory.getMarker(webException.getErrorCode()), exception.getMessage(), exception);
        response.setStatus(webException.getStatusCode());
        return errorResponse;
    }

    private ErrorResponse handleError(HttpRequestMethodNotSupportedException exception) {
        MethodNotAllowedException webException = new MethodNotAllowedException(exception.getMessage());
        logger.error(MarkerFactory.getMarker(webException.getErrorCode()), exception.getMessage(), exception);
        return new ErrorResponse(webException);
    }
}
