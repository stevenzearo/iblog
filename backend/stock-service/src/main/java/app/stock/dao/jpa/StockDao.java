package app.stock.dao.jpa;

import app.stock.domain.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author steve
 */
public interface StockDao extends JpaRepository<Stock, Long> {
    Long insert(Stock stock);

    @Query(value = "select * from stocks where name like ?", nativeQuery = true)
    List<Stock> searchByStockNameFuzzily(String stockName);
}
