package app.stock.web;

import app.stock.api.StockParserWebService;
import app.stock.api.parser.ParseStockPriceRequest;
import app.stock.api.parser.ParseStockPriceResponse;
import app.stock.service.StockParserService;
import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.jboss.logging.Logger;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author steve
 */
@Component
public class StockParserWebServiceImpl implements StockParserWebService {
    private static Logger logger = LoggerFactory.logger(StockWebServiceImpl.class);
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
