package app.stock.dao.jpa;

import app.stock.domain.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author steve
 */
public interface StockDao extends JpaRepository<Stock, Long> {
    List<Stock> searchByStockNameFuzzily(String stockName);
}
