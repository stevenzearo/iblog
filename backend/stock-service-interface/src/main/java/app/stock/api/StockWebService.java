package app.stock.api;

import app.stock.api.stock.AddStockRequest;
import app.stock.api.stock.GetStockResponse;
import app.stock.api.stock.SearchStockRequest;
import app.stock.api.stock.SearchStockResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author steve
 */
@RestController
@FeignClient("stock-web-service")
public interface StockWebService {
    @RequestMapping(value = "/stock/test", method = RequestMethod.GET)
    String test(); // todo remove, for test

    @RequestMapping(value = "/stock", method = RequestMethod.POST)
    Long addStock(@RequestBody AddStockRequest request);

    @RequestMapping(value = "/stock/{id}", method = RequestMethod.GET)
    GetStockResponse getStock(@PathVariable("id") Long id);

    @RequestMapping(value = "/stock", method = RequestMethod.PUT)
    SearchStockResponse search(@RequestBody SearchStockRequest request);
}
