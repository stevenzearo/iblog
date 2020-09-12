package app.site.cache;

/**
 * @author steve
 */
@FunctionalInterface
public interface TransactionSupplier {
    void call();
}
