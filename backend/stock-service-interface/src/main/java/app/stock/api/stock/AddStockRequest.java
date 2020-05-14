package app.stock.api.stock;

/**
 * @author steve
 */
public class AddStockRequest {
    public String code;
    public String blockCode;
    public String name;
    public Double latest;
    public Double open;
    public Double close;
    public Double high;
    public Double low;
    public Double volume;
    public Double volumeRate;

    @Override
    public String toString() {
        return "AddStockRequest{" +
            "code='" + code + '\'' +
            ", blockCode='" + blockCode + '\'' +
            ", name='" + name + '\'' +
            ", latest=" + latest +
            ", open=" + open +
            ", close=" + close +
            ", high=" + high +
            ", low=" + low +
            ", volume=" + volume +
            ", volumeRate=" + volumeRate +
            '}';
    }
}
