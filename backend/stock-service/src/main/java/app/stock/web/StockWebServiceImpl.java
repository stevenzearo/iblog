package app.stock.web;

import app.stock.api.StockWebService;
import app.stock.api.stock.AddStockRequest;
import app.stock.api.stock.SearchStockRequest;
import app.stock.api.stock.SearchStockResponse;
import app.stock.service.StockService;
import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author steve
 */
@Component
public class StockWebServiceImpl implements StockWebService {
    private static Logger logger = LoggerFactory.logger(StockWebServiceImpl.class);
    @Autowired
    StockService stockService;

    @Override
    public Long addStock(AddStockRequest request) {
        Long id = stockService.addStock(request);
        logger.warn(String.format("stockId:%s", id));
        return id;
    }

    @Override
    public SearchStockResponse search(SearchStockRequest request) {
        return null;
    }
}
