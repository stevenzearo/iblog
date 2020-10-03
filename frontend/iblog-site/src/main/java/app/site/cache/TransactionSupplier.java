package app.site.cache;

import app.web.error.WebException;

/**
 * @author steve
 */
@FunctionalInterface
public interface TransactionSupplier {
    void call() throws WebException;
}
