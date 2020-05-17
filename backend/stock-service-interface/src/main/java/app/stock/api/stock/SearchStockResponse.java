package app.stock.api.stock;

import java.time.ZonedDateTime;
import java.util.List;

/**
 * @author steve
 */
public class SearchStockResponse {
	public List<Stock> stocks = List.of();

	public static class Stock {
		public Long id;
		public String code;
		public Long marketId;
		public String name;
		public ZonedDateTime createTime;
		public ZonedDateTime updatedTime;
	}
}
