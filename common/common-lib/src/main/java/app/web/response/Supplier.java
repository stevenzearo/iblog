package app.web.response;

/**
 * @author steve
 */
@FunctionalInterface
public interface Supplier<T> {
    <E extends Exception> T getVal() throws E;
}
