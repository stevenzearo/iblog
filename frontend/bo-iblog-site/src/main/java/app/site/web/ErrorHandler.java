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
        if (exception instanceof WebException) {
            webException = (WebException) exception;
            handleError(response, webException);
        } else {
            logger.error(MarkerFactory.getMarker(webException.getErrorCode()), exception.getMessage(), exception);
        }
        response.setStatus(webException.getStatusCode());
        return new ErrorResponse(webException);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ErrorResponse handleError(HttpServletResponse response, HttpRequestMethodNotSupportedException exception) {
        MethodNotAllowedException webException = new MethodNotAllowedException(exception.getMessage());
        logger.error(MarkerFactory.getMarker(webException.getErrorCode()), exception.getMessage(), exception);
        response.setStatus(webException.getStatusCode());
        return new ErrorResponse(webException);
    }

    @ExceptionHandler(WebException.class)
    public ErrorResponse handleError(HttpServletResponse response, WebException exception) {
        logger.error(MarkerFactory.getMarker(exception.getErrorCode()), exception.getMessage(), exception);
        response.setStatus(exception.getStatusCode());
        return new ErrorResponse(exception);
    }
}
