package app.stock.web;

import app.stock.api.StockParserWebService;
import app.stock.api.parser.ParseStockPriceRequest;
import app.stock.api.parser.ParseStockPriceResponse;
import app.stock.service.StockParserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author steve
 */
@Component
public class StockParserWebServiceImpl implements StockParserWebService {
    private static Logger logger = LoggerFactory.getLogger(BlockWebServiceImpl.class);
    @Autowired
    StockParserService stockParserService;

    @Override
    public String test() {
        return "hello, world!";
    }

    @Override
    public ParseStockPriceResponse parseSStock(String stockCode, ParseStockPriceRequest request) {
        return null;
    }
}
