package app.stock.api;

import app.stock.api.parser.ParseStockPriceRequest;
import app.stock.api.parser.ParseStockPriceResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author steve
 */
@RestController
@FeignClient("stock-parser-web-service")
public interface StockParserWebService {
    @RequestMapping(value = "/parser/stock/test", method = RequestMethod.GET)
    String test(); // todo remove, for test

    @RequestMapping(value = "/parser/stock/{code}", method = RequestMethod.PUT)
    ParseStockPriceResponse parseSStock(@PathVariable("code") String stockCode, @RequestBody ParseStockPriceRequest request);
}
