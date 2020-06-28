package app.web.response;

import app.web.error.ErrorResponse;

/**
 * @author steve
 */
public class EmptyResponse {
    public int statusCode;
    public ErrorResponse error;

    public EmptyResponse() {
    }

    public EmptyResponse(int statusCode, ErrorResponse error) {
        this.statusCode = statusCode;
        this.error = error;
    }
}
