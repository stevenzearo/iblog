package app.web.response;

import app.web.error.WebErrorCodes;
import app.web.error.WebException;


/**
 * @author steve
 */
public class Response<T> extends EmptyResponse {
    public static EmptyResponse ok() {
        return new Response<>(null);
    }

    public static <A> Response<A> okOf(A data) {
        return new Response<>(WebErrorCodes.OK, data, null);
    }

    public static <E extends WebException> EmptyResponse errorOf(E e) {
        return new Response<>(e.getCode(), null, e);
    }

    public static EmptyResponse encloseWithException(SupplierWithoutReturn supplierWithoutReturn) {
        try {
            supplierWithoutReturn.call();
        } catch (Exception e) {
            return encloseException(e);
        }
        return ok();
    }

    public static <D> Response<D> encloseWithException(SupplierWithReturn<D> supplierWithReturn) {
        D data;
        try {
            data = supplierWithReturn.get();
        } catch (Exception e) {
            return encloseException(e);
        }
        return okOf(data);
    }

    private static <D> Response<D> encloseException(Exception e) {
        WebException webException = new WebException(e);
        if (e instanceof WebException) {
            webException = (WebException) e;
            return new Response<>(webException.getCode(), null, webException);
        }
        return new Response<>(webException.getCode(), null, webException);
    }

    private final T data;

    public Response(T data) {
        this.data = data;
    }

    private Response(int statusCode) {
        super(statusCode);
        this.statusCode = statusCode;
        this.data = null;
    }

    private Response(int statusCode, T data, WebException exception) {
        super(statusCode, exception);
        this.statusCode = statusCode;
        this.data = data;
        this.exception = exception;
    }

    public T getData() {
        return data;
    }

    public T getDataWithException() throws WebException {
        if (statusCode != WebErrorCodes.OK && exception != null) {
            throw exception;
        }
        return data;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public WebException getException() {
        return exception;
    }
}
