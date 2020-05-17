package app.stock.api.stock;

import java.time.ZonedDateTime;

/**
 * @author steve
 */
public class GetStockResponse {
    public Long id;
    public String code;
    public String marketId;
    public String name;
    public ZonedDateTime createTime;
    public ZonedDateTime updatedTime;
}
