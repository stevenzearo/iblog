package app.stock.dao.jpa;

import app.stock.domain.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author steve
 */
@Repository
public interface StockDao extends JpaRepository<Stock, Long> {
	Stock save(Stock stock);

	Stock getById(Long id);

	@Query(value = "select * from stocks where name like ?", nativeQuery = true)
	List<Stock> searchByNameLike(String stockName);
}
