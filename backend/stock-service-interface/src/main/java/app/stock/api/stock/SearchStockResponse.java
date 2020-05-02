package app.stock.api.stock;

import java.util.List;

/**
 * @author steve
 */
public class SearchStockResponse {
	public List<Stock> stocks = List.of();

	public static class Stock {
		public String code;
		public String blockCode;
		public String name;
		public Double latest;
		public Double increased;
		public Double increasedRate;
		public Double open;
		public Double close;
		public Double high;
		public Double low;
		public Double volume;
		public Double volumeRate;
		public Double amount;
	}
}
