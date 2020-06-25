package app.stock.web;

import app.stock.api.StockWebService;
import app.stock.api.stock.AddStockRequest;
import app.stock.api.stock.GetStockResponse;
import app.stock.api.stock.SearchStockRequest;
import app.stock.api.stock.SearchStockResponse;
import app.stock.service.StockService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author steve
 */
@Component
public class StockWebServiceImpl implements StockWebService {
    private static Logger logger = LoggerFactory.getLogger(StockWebServiceImpl.class);

    @Autowired
    StockService stockService;

    @Override
    public String test() {
        return "hello, world!";
    }

    @Override
    public Long addStock(AddStockRequest request) throws Exception {
        Long id = stockService.create(request);
        logger.warn(String.format("stockId:%s", id));
        return id;
    }

    @Override
    public GetStockResponse getStock(Long id) {
        logger.warn(String.format("stockId:%s", id));
        return stockService.getStock(id);
    }

    @Override
    public SearchStockResponse search(SearchStockRequest request) {
        return stockService.search(request);
    }
}
