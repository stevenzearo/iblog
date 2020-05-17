package app.stock.service;

import app.stock.api.parser.ParseStockPriceRequest;
import app.stock.api.parser.ParseStockPriceResponse;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author steve
 */
@Service
public class StockParserService {
    public ParseStockPriceResponse parseStock(String stockCode, ParseStockPriceRequest request) {
        Connection connection = getConnection(request);
        try {
            connection.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    Connection getConnection(ParseStockPriceRequest request) {
        Connection connection = Jsoup.connect(request.url);
        if (request.timeout != null) {
            connection.timeout(request.timeout);
        }
        if (request.userAgent != null) {
            connection.userAgent(request.userAgent);
        }
        if (request.cookieMap != null && !request.cookieMap.isEmpty()) {
            connection.cookies(request.cookieMap);
        }
        if (request.headerParamMap != null && !request.headerParamMap.isEmpty()) {
            connection.headers(request.headerParamMap);
        }
        setParserMethodAndBody(request, connection);
        return connection;
    }

    private void setParserMethodAndBody(ParseStockPriceRequest request, Connection connection) {
        switch (request.method) {
            case GET: {
                connection.method(Connection.Method.GET);
            }
            case PUT: {
                connection.method(Connection.Method.PUT);
            }
            case POST: {
                connection.method(Connection.Method.POST);
            }
            if (request.body != null) {
                connection.requestBody(request.body);
            }
            if (request.dataMap != null && !request.dataMap.isEmpty()) {
                connection.data(request.dataMap);
            }
        }
    }
}
