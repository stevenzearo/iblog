package app.stock.api.parser;

import java.util.Map;

/**
 * @author steve
 */
public class ParseStockPriceRequest {
    public String url;
    public ParserRequestMethod method;
    public Integer timeout;
    public String userAgent;
    public Map<String, String> cookieMap;
    public Map<String, String> headerParamMap;
    public Map<String, String> dataMap;
    public String body;
}
