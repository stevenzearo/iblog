package app.stock.api;

import app.stock.api.stock.AddStockRequest;
import app.stock.api.stock.SearchStockRequest;
import app.stock.api.stock.SearchStockResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author steve
 */
@RestController
@FeignClient("stock-web-service")
public interface StockWebService {
    @RequestMapping(value = "/stock/{id}", method = RequestMethod.POST)
    Long addStock(AddStockRequest request);

    @RequestMapping(value = "/stock", method = RequestMethod.PUT)
    SearchStockResponse search(SearchStockRequest request);
}
