package app.web.response;

import app.web.error.WebErrorCodes;
import app.web.error.WebException;


/**
 * @author steve
 */
public class Response<T> {
    public static <A> Response<A> ok() {
        return new Response<>(WebErrorCodes.OK);
    }

    public static <A> Response<A> okOf(A data) {
        return new Response<>(WebErrorCodes.OK, data);
    }

    public static <E extends WebException> Response<E> errorOf(E e) {
        return new Response<>(e.getCode(), e);
    }

    public static <D> Response<D> encloseWithException(Supplier<D> supplier) {
        D data;
        try {
            data = supplier.getVal();
        } catch (Exception e) {
            WebException webException = new WebException(e);
            if (e instanceof WebException) {
                webException = (WebException) e;
                return new Response<>(webException.getCode(), null, webException);
            }
            return new Response<>(webException.getCode(), null, webException);
        }
        return okOf(data);
    }
    private final int statusCode;
    private T data;
    private WebException exception;

    private Response(int statusCode) {
        this.statusCode = statusCode;
    }

    private Response(int statusCode, T data) {
        this.statusCode = statusCode;
        this.data = data;
    }

    private Response(int statusCode, T data, WebException exception) {
        this.statusCode = statusCode;
        this.data = data;
        this.exception = exception;
    }

    public T getData() {
        return data;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public WebException getException() {
        return exception;
    }
}
