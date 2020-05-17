package app.stock.api.stock;

import java.time.ZonedDateTime;

/**
 * @author steve
 */
public class AddStockRequest {
    public Long id;
    public String code;
    public Long marketId;
    public String name;
    public String createdBy;

    @Override
    public String toString() {
        return "AddStockRequest{" +
            "id=" + id +
            ", code='" + code + '\'' +
            ", marketId='" + marketId + '\'' +
            ", name='" + name + '\'' +
            ", createdBy='" + createdBy + '\'' +
            '}';
    }
}
