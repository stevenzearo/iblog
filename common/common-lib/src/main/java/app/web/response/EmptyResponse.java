package app.web.response;

import app.web.error.WebErrorCodes;
import app.web.error.WebException;

/**
 * @author steve
 */
public abstract class EmptyResponse {
    int statusCode = 200;
    WebException exception;

    public EmptyResponse() {}

    public EmptyResponse(int statusCode) {
        this.statusCode = statusCode;
    }

    public EmptyResponse(int statusCode, WebException exception) {
        this.statusCode = statusCode;
        this.exception = exception;
    }

    public void checkStatusCode() throws WebException {
        if (statusCode != WebErrorCodes.OK && exception != null) throw exception;
    }
}
