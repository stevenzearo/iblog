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

    @ExceptionHandler(Throwable.class)
    public ErrorResponse handleError(HttpServletResponse response, Throwable throwable) {
        WebException webException = new WebException(throwable.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(webException);

        if (throwable instanceof WebException) {
            webException = (WebException) throwable;
            errorResponse = new ErrorResponse(webException);
        } else if (throwable instanceof HttpRequestMethodNotSupportedException) {
            HttpRequestMethodNotSupportedException methodNotSupportedException = (HttpRequestMethodNotSupportedException) throwable;
            errorResponse = handleError(methodNotSupportedException);
        }

        logger.error(MarkerFactory.getMarker(webException.getErrorCode()), throwable.getMessage(), throwable);
        response.setStatus(webException.getStatusCode());
        return errorResponse;
    }

    private ErrorResponse handleError(HttpRequestMethodNotSupportedException exception) {
        MethodNotAllowedException webException = new MethodNotAllowedException(exception.getMessage());
        logger.error(MarkerFactory.getMarker(webException.getErrorCode()), exception.getMessage(), exception);
        return new ErrorResponse(webException);
    }
}
