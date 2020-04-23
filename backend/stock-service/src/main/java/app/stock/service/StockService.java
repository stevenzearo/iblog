package app.stock.service;

import app.stock.api.stock.AddStockRequest;
import app.stock.api.stock.SearchStockRequest;
import app.stock.api.stock.SearchStockResponse;
import app.stock.dao.jpa.StockDao;
import app.stock.domain.Stock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author steve
 */
@Service
public class StockService {
    @Autowired
    StockDao stockDao;

    public Long addStock(AddStockRequest request) {
        Stock stock = buildStock(request);
        return stockDao.insert(stock);
    }

    private Stock buildStock(AddStockRequest request) {
        Stock stock = new Stock();
        stock.code = request.code;
        stock.blockCode = request.blockCode;
        stock.name = request.name;
        stock.latest = request.latest;
        stock.increased = request.increased;
        stock.increasedRate = request.increasedRate;
        stock.open = request.open;
        stock.close = request.close;
        stock.high = request.high;
        stock.low = request.low;
        stock.volume = request.volume;
        stock.volumeRate = request.volumeRate;
        stock.amount = request.amount;
        return stock;
    }

    public SearchStockResponse search(SearchStockRequest request) {
        List<Stock> stocks = stockDao.searchByStockNameFuzzily(request.name);
        SearchStockResponse response = new SearchStockResponse();
        response.stocks = stocks.stream().map(stock -> {
            SearchStockResponse.Stock stockView = new SearchStockResponse.Stock();
            stockView.code = stock.code;
            stockView.blockCode = stock.blockCode;
            stockView.name = stock.name;
            stockView.latest = stock.latest;
            stockView.increased = stock.increased;
            stockView.increasedRate = stock.increasedRate;
            stockView.open = stock.open;
            stockView.close = stock.close;
            stockView.high = stock.high;
            stockView.low = stock.low;
            stockView.volume = stock.volume;
            stockView.volumeRate = stock.volumeRate;
            stockView.amount = stock.amount;
            return stockView;
        }).collect(Collectors.toList());
        return response;
    }
}
