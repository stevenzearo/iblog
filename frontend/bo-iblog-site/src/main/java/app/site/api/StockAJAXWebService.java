package app.site.api;

import app.stock.api.StockWebService;
import app.stock.api.stock.AddStockRequest;
import app.stock.api.stock.GetStockResponse;
import app.stock.api.stock.SearchStockRequest;
import app.stock.api.stock.SearchStockResponse;
import org.slf4j.ILoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
public class StockAJAXWebService {
    @Autowired(required = false)
    StockWebService stockWebService;

    @RequestMapping(value = "/iblog/stock/test", method = RequestMethod.GET)
    String test() {
        return "hello, world!";
    }

    @PostMapping(value = "/iblog/stock")
    Long addStock(@RequestBody AddStockRequest request) {
        return stockWebService.addStock(request);
    }

    @RequestMapping(value = "/iblog/stock/{id}", method = RequestMethod.GET)
    GetStockResponse getStock(@PathVariable("id") Long id) {
        return stockWebService.getStock(id);
    }

    @RequestMapping(value = "/iblog/stock", method = RequestMethod.PUT)
    SearchStockResponse search(@RequestBody SearchStockRequest request) {
        return stockWebService.search(request);
    }
}
