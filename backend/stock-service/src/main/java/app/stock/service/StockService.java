package app.stock.service;

import app.stock.api.stock.AddStockRequest;
import app.stock.api.stock.GetStockResponse;
import app.stock.api.stock.SearchStockRequest;
import app.stock.api.stock.SearchStockResponse;
import app.stock.dao.jpa.StockDao;
import app.stock.domain.Stock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author steve
 */
@Service
public class StockService {
    @Autowired
    StockDao stockDao;

    public Long create(AddStockRequest request) throws Exception {
        Stock stockInDB = stockDao.getFirstByCode(request.code);
        if (stockInDB != null) {
            return -1L;
        }
        Stock stock = buildStock(request);
        return stockDao.save(stock).id;
    }

    public GetStockResponse getStock(Long id) {
        Stock stock = stockDao.getById(id);
        return buildGetStockResponse(stock);
    }

    public SearchStockResponse search(SearchStockRequest request) {
        List<Stock> stocks = stockDao.searchByNameLike(String.format("%%%s%%", request.name));
        SearchStockResponse response = new SearchStockResponse();
        response.stocks = stocks.stream().map(this::stockView).collect(Collectors.toList());
        return response;
    }

    private GetStockResponse buildGetStockResponse(Stock stock) {
        GetStockResponse response = new GetStockResponse();
        response.code = stock.code;
        response.marketId = stock.marketId;
        response.name = stock.name;
        response.createTime = stock.createTime;
        response.updatedTime = stock.updatedTime;
        return response;
    }

    private Stock buildStock(AddStockRequest request) {
        Stock stock = new Stock();
        stock.code = request.code;
        stock.marketId = request.marketId;
        stock.name = request.name;
        stock.createTime = ZonedDateTime.now();
        stock.createBy = request.createdBy;
        return stock;
    }

    private SearchStockResponse.Stock stockView(Stock stock) {
        SearchStockResponse.Stock stockView = new SearchStockResponse.Stock();
        stockView.id = stock.id;
        stockView.code = stock.code;
        stockView.marketId = stock.marketId;
        stockView.name = stock.name;
        stockView.createTime = stock.createTime;
        stockView.updatedTime = stock.updatedTime;
        return stockView;
    }
}
